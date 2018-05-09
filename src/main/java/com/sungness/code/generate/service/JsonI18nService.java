package com.sungness.code.generate.service;

import com.sungness.code.generate.config.GenConfig;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.code.generate.util.FileTools;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json 国际化文件
 * Created by Chwing on 2018/5/9.
 */
@Service
public class JsonI18nService implements GeneratorInterface {

    private static final Logger log = LoggerFactory.getLogger(JsonI18nService.class);

    @Autowired
    private GenConfig genConfig;

    @Override
    public void execute(InformationSchemaTable table, List<InformationSchemaColumn> columnList) {
        Map<String, Map<String, Object>> cnJsonMap = new HashMap<>();
        Map<String, Object> cnFieldMap = new HashMap<>();
        Map<String, Map<String, Object>> enJsonMap = new HashMap<>();
        Map<String, Object> enFieldMap = new HashMap<>();
        columnList.forEach(column -> {
            cnFieldMap.put(column.getCamelCaseName(), column.getClearComment());
            enFieldMap.put(column.getCamelCaseName(), column.getEnComment());
        });
        cnJsonMap.put(table.getCamelCaseName(), cnFieldMap);
        enJsonMap.put(table.getCamelCaseName(), enFieldMap);

        String cnFileName = genConfig.buildJsonI18nFile(table.getCamelCaseName() + ".json", "zh-cn");
        String enFileName = genConfig.buildJsonI18nFile(table.getCamelCaseName() + ".json", "en");

        FileTools.writeStringToFile(cnFileName, GsonUtils.toJson(cnJsonMap));
        FileTools.writeStringToFile(enFileName, GsonUtils.toJson(enJsonMap));
    }
}
