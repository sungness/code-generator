package com.sungness.code.generate.engine;

import com.sungness.code.generate.exception.CodegenException;
import com.sungness.code.generate.model.ConfigModel;
import com.sungness.code.generate.util.FileHelper;
import com.sungness.code.generate.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class CodeDelete {
    private static String xmlPath;

    public static void setXmlPath(String paramString) {
        xmlPath = paramString;
    }

    public boolean validXmlBySchema(String paramString) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File file = new File(paramString);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(file);
        } catch (SAXException localSAXException) {
            localSAXException.printStackTrace();
        }
        Validator validator = schema.newValidator();
        StreamSource streamSource = new StreamSource(
                FileHelper.getInputStream(xmlPath));
        try {
            validator.validate(streamSource);
            return true;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    public ConfigModel loadXml(String xmlFileName) throws Exception {
        String xsdFileName = new File("").getAbsolutePath() + "/generator-config.xsd";
        if (!validXmlBySchema(xsdFileName)) {
            throw new CodegenException("XML文件通过XSD文件校验失败");
        }
        ConfigModel configModel = new ConfigModel();
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(xmlFileName));
        } catch (DocumentException e) {
            throw new CodegenException("读取XML出错!", e);
        }

        Element root = doc.getRootElement();
        Properties localProperties = new Properties();
        String curPath = new File("").getAbsolutePath();
        BufferedInputStream localBufferedInputStream = new BufferedInputStream(
                new FileInputStream(curPath + "\\" + "db.properties"));
        localProperties.load(localBufferedInputStream);
        String charset = localProperties.getProperty("charset");
        String system = localProperties.getProperty("system");
        configModel.setCharset(charset);


        Object localObject4;
        Object localObject5;
        String str7;
        String str8;
        Object localObject6;
        Object localObject7;
        String str9;
        String str10;

//        Iterator tableIter = root.elementIterator("table");
//        while (tableIter.hasNext()) {
//            Element tableElement = (Element)tableIter.next();
//            String tableName = tableElement.attributeValue("tableName");
//            TableElement table = new TableElement(tableName);
//            Iterator variableIter = tableElement.elementIterator("variable");
//            while (variableIter.hasNext()) {
//                Element variableElement = (Element) variableIter.next();
//                String varName = variableElement.attributeValue("name");
//                String varValue =variableElement.attributeValue("value");
//                table.getVariable().put(varName, varValue);
//                if (StringUtil.isNotEmpty(table.getVariable().get("class"))) {
//                    String classVarName = StringUtil.toFirstLowerCase(table.getVariable().get("class"));
//                    table.getVariable().put("classVar", classVarName);
//                }
//            }
//            table.getVariable().put("tabname", tableName);
//            Iterator subtableIter = tableElement.elementIterator("subtable");
//            while (subtableIter.hasNext()) {
//                Element subtableElement = (Element)subtableIter.next();
//                String subtablename = subtableElement.attributeValue("tablename");
//                String foreignKey = subtableElement.attributeValue("foreignKey");
//                Map<String, String> varsMap = new HashMap<>();
//                Iterator subtableVarIter = subtableElement.elementIterator("variable");
//                while (subtableVarIter.hasNext()) {
//                    Element subtableVar = (Element)subtableVarIter.next();
//                    String subVarName = subtableVar.attributeValue("name");
//                    String subVarValue = subtableVar.attributeValue("value");
//                    varsMap.put(subVarName, subVarValue);
//                }
//                if (StringUtil.isNotEmpty(varsMap.get("class"))) {
//                    String subtableClassVarName = StringUtil.toFirstLowerCase(varsMap.get("class"));
//                    varsMap.put("classVar", subtableClassVarName);
//                }
//                varsMap.put("tabname", subtablename);
//                table.addSubTable(subtablename, foreignKey, varsMap);
//            }
//            configModel.getTables().add(table);
//        }
//
//        Element filesElement = root.element("files");
//        FilesElement files = new FilesElement();
//        configModel.setFilesElement(files);
//        files.setBaseDir(filesElement.attributeValue("baseDir"));
//        Iterator fileIter = filesElement.elementIterator("file");
//        while (fileIter.hasNext()) {
//            Element fileElement = (Element)fileIter.next();
//            String refTemplate = fileElement.attributeValue("refTemplate");
//            String filename = fileElement.attributeValue("filename");
//            String targetDir = StringUtil.replaceVariable(fileElement.attributeValue("dir"), system);
//            String sub = fileElement.attributeValue("sub");
//            String override = fileElement.attributeValue("override");
//            boolean isOverride = false;
//            if (StringUtil.isNotEmpty(override) && override.equals("true")) {
//                isOverride = true;
//            }
//            str9 = fileElement.attributeValue("append");
//            if ((str9 != null) && (str9.toLowerCase().equals("true"))) {
//                str10 = fileElement.attributeValue("insertTag");
//                String str11 = fileElement.attributeValue("startTag");
//                String str12 = fileElement.attributeValue("endTag");
//                if (str10 == null)
//                    str10 = "<!--insertbefore-->";
//                if (StringUtil.isEmpty(str11))
//                    str11 = "";
//                if (StringUtil.isEmpty(str12))
//                    str12 = "";
//                if (sub != null && (sub.equalsIgnoreCase("true")))
//                    files.addFile(null, filename,
//                            targetDir, true, true, str10, str11, str12, isOverride);
//                else
//                    files.addFile(null, filename,
//                            targetDir, false, true, str10, str11, str12, isOverride);
//            } else if (sub != null && sub.equalsIgnoreCase("true")) {
//                files.addFile(null, filename, targetDir,
//                        true, false, "", "", "", isOverride);
//            } else {
//                files.addFile(null, filename, targetDir,
//                        false, false, "", "", "", isOverride);
//            }
//        }
        return configModel;
    }

    public void deleteFileByConfig(ConfigModel configModel)
            throws Exception {
//        List<TableElement> tableList = configModel.getTables();
//        FilesElement files = configModel.getFilesElement();
//        String baseDir = files.getBaseDir();
//        String charset = configModel.getCharset();
//        for (TableElement table: tableList) {
//            Map<String, String> variableMap = table.getVariable();
//            List<FileElement> file1List = files.getFiles();
//            for (FileElement file1: file1List) {
//                String fileName = file1.getFilename();
//                String dir = file1.getDir();
//                String replacedFileName = StringUtil.replaceVariable(fileName, variableMap);
//                String replacedDir = StringUtil.replaceVariable(dir, variableMap);
//                String dirPath = baseDir + "/" + replacedDir;
//                String startTag = StringUtil.replaceVariable(file1.getStartTag(), variableMap);
//                String endTag = StringUtil.replaceVariable(file1.getEndTag(), variableMap);
//                boolean bool1 = file1.getAppend();
//                if (bool1) {
//                    deleteAppendFile(dirPath, replacedFileName, charset, startTag, endTag);
//                } else {
//                    deleteFile(dirPath, replacedFileName);
//                }
//            }
//
//            List<SubTable> subTableList = table.getSubtable();
//            if (subTableList != null && subTableList.size() >0) {
//                for (SubTable subTable: subTableList) {
//                    Map<String, String> varsMap = subTable.getVars();
//                    for (FileElement file1: file1List) {
//                        String fileName = file1.getFilename();
//                        String dir = file1.getDir();
//                        if (fileName.contains("{")) {
//                            String mark = fileName.substring(fileName.indexOf(123) + 1,
//                                    fileName.indexOf(125));
//                            fileName = varsMap.get(mark)
//                                    + fileName.substring(fileName.indexOf(125) + 1);
//                        }
//                        if (dir.contains("{")) {
//                            String mark = dir.substring(dir.indexOf(123) + 1,
//                                    dir.indexOf(125));
//                            dir = dir.substring(0, dir.indexOf(123)) + varsMap.get(mark);
//                        }
//
//                        String targetPath = baseDir + "/" + dir;
//                        boolean isSub = file1.isSub();
//                        boolean isAppend = file1.getAppend();
//                        if (isSub) {
//                            if (isAppend) {
//                                deleteAppendFile(targetPath, fileName, charset, file1.getStartTag(),
//                                        file1.getEndTag());
//                            } else {
//                                deleteFile(targetPath, fileName);
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    private void deleteFile(String paramString1, String paramString2) {
        String str = StringUtil.combilePath(paramString1, paramString2);
        File localFile = new File(str);
        if (localFile.exists()) {
            localFile.delete();
            System.out.println("删除了文件" + paramString2);
        } else {
            System.out.println(paramString2 + "该文件不存在");
        }
        if (!FileHelper.isExistFile(paramString1)) {
            localFile = new File(paramString1);
            localFile.delete();
        }
    }

    private void deleteAppendFile(String paramString1, String paramString2,
            String paramString3, String paramString4, String paramString5)
            throws Exception {
        String str1 = StringUtil.combilePath(paramString1, paramString2);
        File localFile = new File(str1);
        if (localFile.exists()) {
            int i = 0;
            String str2 = FileHelper.readFile(str1, paramString3);
            if (str2.indexOf(paramString4) != -1) {
                str2 = str2.substring(0, str2.indexOf(paramString4)).trim()
                        + "\r\n"
                        + str2.substring(
                                str2.indexOf(paramString5)
                                        + paramString5.length()).trim();
                localFile.delete();
                localFile = new File(str1);
                FileHelper.writeFile(localFile, paramString3, str2.trim());
                System.out.println("删除了内容" + paramString4 + "-----"
                        + paramString5);
            }
        }
    }

    public void execute() throws Exception {
        String str = new File("").getAbsolutePath();
        if ((xmlPath == null) || (xmlPath == ""))
            setXmlPath(str + "/" + "generator-config.xml");
        System.out.println("execute:" + xmlPath);
        if (xmlPath == null) {
            throw new CodegenException("未指定XML路径!");
        }
        ConfigModel configModel = loadXml(xmlPath);
        deleteFileByConfig(configModel);
    }

    public static void main(String[] paramArrayOfString) throws Exception {
        CodeDelete codeDelete = new CodeDelete();
        setXmlPath("mscodegenconfig.xml");
        codeDelete.execute();
    }
}
