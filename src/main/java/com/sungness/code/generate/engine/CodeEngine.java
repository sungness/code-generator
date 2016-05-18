package com.sungness.code.generate.engine;

import com.sungness.code.generate.config.parser.CodeXMLParser;
import com.sungness.code.generate.dao.InformationSchemaDao;
import com.sungness.code.generate.db.IDbHelper;
import com.sungness.code.generate.exception.CodegenException;
import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.ConfigModel;
import com.sungness.code.generate.model.File2;
import com.sungness.code.generate.model.GenAll;
import com.sungness.code.generate.model.TableModel;
import com.sungness.code.generate.model.element.*;
import com.sungness.code.generate.model.schema.TableSchema;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.code.generate.util.FileHelper;
import com.sungness.code.generate.util.StringUtil;
import com.sungness.code.generate.xml.DomUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/***
 * 代码生成器主程序
 * 加载、解析配置文件，根据配置生成相应的目标文件。
 *
 */
@Service
public class CodeEngine {

    private static final Logger log = LoggerFactory.getLogger(CodeEngine.class);

    @Autowired
    private CodeXMLParser codeXMLParser;

//    @Autowired
//    private Properties codeProperties;

//    @Autowired
//    private Properties dbProps;

    @Autowired
    private InformationSchemaDao informationSchemaDao;

    private Configuration freemarkerConfig;

    /**
     * 代码生成启动主方法
     * @throws Exception
     */
    public void start() throws Exception {
        try {
            CodeElement codeElement = loadCodeXML();

            //创建Freemarker配置文件
            freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            freemarkerConfig.setDirectoryForTemplateLoading(new File(codeElement.getTemplatesPath()));

            for (DatabaseElement databaseElement: codeElement.getDatabases()) {
                createCode(databaseElement, codeElement);
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 加载代码配置文件，并返回 CodeElement 对象
     * @return CodeElement 代码配置文件对象
     * @throws Exception
     */
    private CodeElement loadCodeXML() throws Exception {
        String xsdFileName = "";//codeProperties.getProperty("codeXSDFileName");
        String xmlFileName = "";//codeProperties.getProperty("codeXMLFileName");
        if (!DomUtil.validByXSD(xsdFileName, xmlFileName)) {
            throw new ConfigException("XML文件 Schema 校验失败:" + xmlFileName);
        }
        return codeXMLParser.parse(DomUtil.loadFromFile(xmlFileName));
    }

    /**
     * 创建指定数据库中的表对应的代码文件
     * @param databaseElement DatabaseElement 配置文件中的数据库节点对象
     * @param codeElement CodeElement 代码配置文件对象
     * @throws ConfigException
     */
    private void createCode(DatabaseElement databaseElement,
                            CodeElement codeElement)
            throws ConfigException {
        if (StringUtils.isBlank(databaseElement.getName())) {
            throw new ConfigException("配置文档中database节点的name属性不能为空");
        }
        List<TableModel> tableModels = new ArrayList<>();
        List<TableElement> tableElements = databaseElement.getTables();
        if (tableElements == null || tableElements.isEmpty()) {
            // 创建该库下所有表的代码
            List<InformationSchemaTable> isTableList =
                    informationSchemaDao.getTables(databaseElement.getName());
            if (isTableList != null) {
                for (InformationSchemaTable isTable: isTableList) {
                    if (!databaseElement.exclude(isTable.getTableName())) {
                        TableModel tableModel = new TableModel();
                        tableModel.setDatabaseElement(databaseElement);
                        tableModel.setInformationSchemaTable(isTable);
                        tableModel.setInformationSchemaColumns(
                                informationSchemaDao.getColumns(
                                        databaseElement.getName(),
                                        isTable.getTableName()));
                        tableModels.add(tableModel);
                    }
                }
            }
        } else {
            // 只创建指定表的代码
            for (TableElement tableElement: tableElements) {
                if (!databaseElement.exclude(tableElement.getName())) {
                    TableModel tableModel = new TableModel();
                    tableModel.setDatabaseElement(databaseElement);
                    tableModel.setTableElement(tableElement);
                    tableModel.setInformationSchemaTable(
                            informationSchemaDao.getTable(
                                    databaseElement.getName(),
                                    tableElement.getName()));
                    tableModel.setInformationSchemaColumns(
                            informationSchemaDao.getColumns(
                                    databaseElement.getName(),
                                    tableElement.getName()));
                    tableModels.add(tableModel);
                }
            }
        }
        for (TableModel tableModel: tableModels) {
            createCode(tableModel, codeElement);
        }
    }

    /**
     * 创建指定表对应的代码文件
     * @param tableModel TableModel 表模型对象
     * @param codeElement CodeElement 代码配置文件对象
     */
    private void createCode(TableModel tableModel, CodeElement codeElement)
        throws ConfigException {
        TargetFilesElement targetFilesElement = codeElement.getTargetFiles();
        for (TargetFileElement targetFileElement: targetFilesElement.getTargetFiles()) {
            TemplateElement templateElement =
                    codeElement.getTemplateById(targetFileElement.getFtlid());
            if (templateElement == null) {
                throw new ConfigException("配置文件中无指定id的模板：" + targetFileElement.getFtlid());
            }

        }
    }

    private void genTableByConfig(IDbHelper idbHelper, ConfigModel configModel,
            Configuration configuration)
            throws CodegenException {
//        try {
//
//            FilesElement files = configModel.getFilesElement();
//            String baseDir = files.getBaseDir();
//            List<FileElement> file1List = files.getFiles();
//            if ((file1List == null) || (file1List.size() == 0)) {
//                System.out.println("没有指定生成的文件!");
//                return;
//            }
//            System.out.println("*********开始生成**********");
//            for (TableSchema tableSchema : tableSchemaList) {
//                String tableName = tableSchema.getTableName();
//                Map<String, String> variableMap = tableSchema.getVariables();
//                boolean subBoolean = tableSchema.getSub();
//
//                for (FileElement file1: file1List) {
//                    String fileName = file1.getFilename();
//                    String startTag = file1.getStartTag();
//                    String endTag = file1.getEndTag();
//
//                    if (subBoolean && !file1.isSub()) {
//                        continue;
//                    }
//                    startTag = StringUtil.replaceVariable(startTag, tableName);
//                    endTag = StringUtil.replaceVariable(endTag, tableName);
//                    String dir = file1.getDir();
//                    fileName = StringUtil.replaceVariable(fileName, variableMap);
//                    dir = StringUtil.replaceVariable(dir, variableMap);
//                    dir = StringUtil.combilePath(baseDir, dir);
//                    Map<String, Object> fileMap = new HashMap<>();
//                    fileMap.put("model", tableSchema);
//                    fileMap.put("vars", configModel.getVariables());
//                    fileMap.put("date", new Date());
//                    if (file1.getAppend()) {
//                        appendFile(dir, fileName, file1.getTemplate(),
//                                configModel.getCharset(),
//                                configuration, fileMap,
//                                file1.getInsertTag(), startTag, endTag);
//                    } else if (file1.isOverride()) {
//                        genFile(dir, fileName, file1.getTemplate(),
//                                configModel.getCharset(),
//                                configuration, fileMap);
//                    } else {
//                        File newFile = new File(dir + "\\" + fileName);
//                        if (!newFile.exists())
//                            genFile(dir, fileName, file1.getTemplate(),
//                                    configModel.getCharset(),
//                                    configuration, fileMap);
//                    }
//                    System.out.println(fileName + " 生成成功!");
//                }
//            }
//            System.out.println("*********所有文件生成成功!**********");
//        } catch (IOException localIOException) {
//            throw new CodegenException(localIOException);
//        } catch (TemplateException localTemplateException) {
//            throw new CodegenException("freemarker执行出错!",
//                    localTemplateException);
//        }
    }

    private void getAllTable(IDbHelper idbHelper, ConfigModel configModel,
                             Configuration configuration)
            throws CodegenException, IOException, TemplateException {
        GenAll genAll = configModel.getGenAll();
        if (genAll == null) {
            return;
        }
        List<String> tableList;
        if (genAll.getTableNames() == null) {
            tableList = idbHelper.getAllTable();
        } else {
            tableList = new ArrayList<>();
            String[] nameArray = genAll.getTableNames().replaceAll(" ", "").split(",");
            for (String name: nameArray) {
                if (!name.isEmpty()) {
                    tableList.add(name);
                }
            }
        }

        if (genAll.getFile().size() == 0) {
            return;
        }
        System.out.println("----------------生成多表开始------------------");
        for (File2 file2: genAll.getFile()) {
            if ("MultiFile".equals(file2.getGenMode())) {
                for (String tableName: tableList) {
                    TableSchema tableSchema = idbHelper.getByTable(tableName);
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("model", tableSchema);
                    paramMap.put("date", new Date());
                    genFile(file2.getDir(),
                            tableName + "." + file2.getExtName(),
                            file2.getTemplate(),
                            configModel.getCharset(),
                            configuration, paramMap);
                    System.out.println(tableName + "." + file2.getExtName() + " 生成成功!");
                }
            } else if ("SingleFile".equals(file2.getGenMode())) {
                List<TableSchema> modelList = new ArrayList<>();
                for (String tableName: tableList) {
                    TableSchema tableSchema = idbHelper.getByTable(tableName);
                    modelList.add(tableSchema);
                }
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("models", modelList);
                paramMap.put("date", new Date());
                genFile(file2.getDir(),
                        file2.getFilename(),
                        file2.getTemplate(),
                        configModel.getCharset(),
                        configuration, paramMap);
                System.out.println(file2.getFilename() + " 生成成功!");
            }
        }
        System.out.println("----------------生成多表结束------------------");
    }

    private void genFile(String dir, String fileName,
                         String templateName, String encoding,
                         Configuration configuration, Map paramMap) throws IOException,
            TemplateException {
        File file = new File(dir, fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.createNewFile();
        }
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), encoding);
        Template template = configuration.getTemplate(templateName, encoding);
        template.process(paramMap, writer);
    }

    private void appendFile(String dir, String fileName,
                            String templateName, String charset,
                            Configuration configuration, Map<String, Object> paramMap,
                            String insertTag, String startTag, String endTag)
            throws IOException, TemplateException {

        String str1 = StringUtil.combilePath(dir, fileName);
        File file = new File(dir, fileName);
        StringWriter writer = new StringWriter();
        Template template = configuration.getTemplate(templateName, charset);
        template.process(paramMap, writer);

        String templateContent = writer.toString();
        boolean noReplaceTag = true;
        String oldFileContent = "";
        if (file.exists()) {
            oldFileContent = FileHelper.readFile(str1, charset);
            if (StringUtil.isNotEmpty(startTag)
                    && StringUtil.isNotEmpty(endTag)
                    && StringUtil.isExist(oldFileContent, startTag, endTag)) {
                oldFileContent = StringUtil.replace(oldFileContent, startTag, endTag, templateContent);
                noReplaceTag = false;
            }
        }
        if (noReplaceTag) {
            if (StringUtil.isNotEmpty(startTag) && StringUtil.isNotEmpty(endTag))
                templateContent = startTag.trim() + "\r\n" + templateContent + "\r\n"
                        + endTag.trim();
            if (oldFileContent.contains(insertTag)) {
                String[] arrayOfString = FileHelper.getBySplit(oldFileContent, insertTag);
                oldFileContent = arrayOfString[0] + templateContent + "\r\n" + insertTag
                        + arrayOfString[1];
            } else {
                oldFileContent = oldFileContent + "\r\n" + templateContent;
            }
        }
        FileHelper.writeFile(file, charset, oldFileContent);
    }

    private IDbHelper getDbHelper(ConfigModel configModel)
            throws CodegenException {
        IDbHelper idbHelper;
        String str = configModel.getDatabase().getDbHelperClass();
        try {
            idbHelper = (IDbHelper) Class.forName(str).newInstance();
        } catch (InstantiationException localInstantiationException) {
            throw new CodegenException("指定的dbHelperClass：" + str
                    + "无法实例化，可能该类是一个抽象类、接口、数组类、基本类型或 void, 或者该类没有默认构造方法!",
                    localInstantiationException);
        } catch (IllegalAccessException localIllegalAccessException) {
            throw new CodegenException("指定的dbHelperClass： " + str
                    + "没有默认构造方法或不可访问!", localIllegalAccessException);
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到指定的dbHelperClass:" + str + "!",
                    localClassNotFoundException);
        }
        idbHelper.setUrl(configModel.getDatabase().getUrl(),
                configModel.getDatabase().getUsername(), configModel
                        .getDatabase().getPassword());
        return idbHelper;
    }


}
