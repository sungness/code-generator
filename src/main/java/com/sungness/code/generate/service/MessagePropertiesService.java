package com.sungness.code.generate.service;

import com.sungness.code.generate.config.GenConfig;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.code.generate.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * MessageSource 属性文件生成器实现类
 * Created by wanghongwei on 4/7/16.
 */
@Service
public class MessagePropertiesService implements GeneratorInterface {

    private static final Logger log = LoggerFactory.getLogger(MessagePropertiesService.class);

    private static final String NEW_LINE = "\r\n";

    @Autowired
    private GenConfig genConfig;

    /**
     * 代码生成器调用主方法
     *
     * @param table      InformationSchemaTable Schema 表对象
     * @param columnList List<InformationSchemaColumn> Schema 列列表对象
     */
    @Override
    public void execute(InformationSchemaTable table,
                        List<InformationSchemaColumn> columnList) {
        try {
            log.debug("Begin generate MessageSource properties File...");
            generateDefault(table, columnList);
            generateChinese(table, columnList);
            log.debug("End generate MessageSource properties File.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDefault(InformationSchemaTable table,
                                 List<InformationSchemaColumn> columnList) throws IOException {
        StringBuilder fieldsContent = new StringBuilder("# ");
        fieldsContent.append(table.getTableName()).append(" fields").append(NEW_LINE);

        StringBuilder fieldTipsContent = new StringBuilder("# ");
        fieldTipsContent.append(table.getTableName()).append(" field tips").append(NEW_LINE);

        StringBuilder validatorTipsContent = new StringBuilder("# ");
        validatorTipsContent.append(table.getTableName()).append(" field validator tips").append(NEW_LINE);

        StringBuilder fieldOrderingContent = new StringBuilder("# ");
        fieldOrderingContent.append(table.getTableName()).append(" field ordering").append(NEW_LINE);

        for (InformationSchemaColumn column : columnList) {
            //companyInfo.id=ID
            fieldsContent.append(String.format("%s.%s=%s",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getEnComment())).append(NEW_LINE);

            //companyInfo.id.tip=数据库中的记录ID。
            if (column.getExtra().contains("auto_increment")) {
                fieldTipsContent.append(String.format("%s.%s.tip=Record number in the database.",
                        table.getCamelCaseName(), column.getCamelCaseName())).append(NEW_LINE);
            } else {
                fieldTipsContent.append(String.format("%s.%s.tip=Enter the %s.",
                        table.getCamelCaseName(), column.getCamelCaseName(),
                        column.getEnComment())).append(NEW_LINE);
            }

            //companyInfo.name.error=公司简称不能为空。
            if (!column.getExtra().contains("auto_increment")) {
                validatorTipsContent.append(String.format("%s.%s.error=%s is required.",
                        table.getCamelCaseName(), column.getCamelCaseName(),
                        column.getEnComment())).append(NEW_LINE);
            }

            //companyInfo.id.asc=ID 升序
            fieldOrderingContent.append(String.format("%s.%s.asc=%s ASC",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getEnComment())).append(NEW_LINE);
            fieldOrderingContent.append(String.format("%s.%s.desc=%s DESC",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getEnComment())).append(NEW_LINE);
        }

        StringBuilder fullContent = new StringBuilder("# ");
        fullContent.append(table.getTableName()).append(" ")
                .append(table.getClearComment()).append(NEW_LINE);
        fullContent.append(fieldsContent).append(NEW_LINE);
        fullContent.append(fieldTipsContent).append(NEW_LINE);
        fullContent.append(validatorTipsContent).append(NEW_LINE);
        fullContent.append(fieldOrderingContent).append(NEW_LINE);
        String fileName = genConfig.buildMessageFileName(table.getCamelCaseName() + ".properties");
        FileTools.writeStringToFile(fileName, fullContent.toString());
    }

    private void generateChinese(InformationSchemaTable table,
                                 List<InformationSchemaColumn> columnList) throws IOException {
        StringBuilder fieldsContent = new StringBuilder("# ");
        fieldsContent.append(table.getTableName()).append(" fields").append(NEW_LINE);

        StringBuilder fieldTipsContent = new StringBuilder("# ");
        fieldTipsContent.append(table.getTableName()).append(" field tips").append(NEW_LINE);

        StringBuilder validatorTipsContent = new StringBuilder("# ");
        validatorTipsContent.append(table.getTableName()).append(" field validator tips").append(NEW_LINE);

        StringBuilder fieldOrderingContent = new StringBuilder("# ");
        fieldOrderingContent.append(table.getTableName()).append(" field ordering").append(NEW_LINE);

        for (InformationSchemaColumn column : columnList) {
            //companyInfo.id=ID
            fieldsContent.append(String.format("%s.%s=%s",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getClearComment())).append(NEW_LINE);

            //companyInfo.id.tip=数据库中的记录ID。
            if (column.getExtra().contains("auto_increment")) {
                fieldTipsContent.append(String.format("%s.%s.tip=数据库中的记录ID。",
                        table.getCamelCaseName(), column.getCamelCaseName())).append(NEW_LINE);
            } else {
                fieldTipsContent.append(String.format("%s.%s.tip=请输入%s。",
                        table.getCamelCaseName(), column.getCamelCaseName(),
                        column.getClearComment())).append(NEW_LINE);
            }

            //companyInfo.name.error=公司简称不能为空。
            if (!column.getExtra().contains("auto_increment")) {
                validatorTipsContent.append(String.format("%s.%s.error=%s不能为空。",
                        table.getCamelCaseName(), column.getCamelCaseName(),
                        column.getClearComment())).append(NEW_LINE);
            }

            //companyInfo.id.asc=ID 升序
            fieldOrderingContent.append(String.format("%s.%s.asc=%s 升序",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getClearComment())).append(NEW_LINE);
            fieldOrderingContent.append(String.format("%s.%s.desc=%s 降序",
                    table.getCamelCaseName(), column.getCamelCaseName(),
                    column.getClearComment())).append(NEW_LINE);
        }

        StringBuilder fullContent = new StringBuilder("# ");
        fullContent.append(table.getTableName()).append(" ")
                .append(table.getClearComment()).append(NEW_LINE);
        fullContent.append(fieldsContent).append(NEW_LINE);
        fullContent.append(fieldTipsContent).append(NEW_LINE);
        fullContent.append(validatorTipsContent).append(NEW_LINE);
        fullContent.append(fieldOrderingContent).append(NEW_LINE);
        String fileName = genConfig.buildMessageFileName(table.getCamelCaseName() + "_zh_CN.properties");
        FileTools.writeStringToFile(fileName, fullContent.toString());
    }
}
