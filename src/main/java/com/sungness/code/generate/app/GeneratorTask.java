package com.sungness.code.generate.app;

import com.sungness.code.generate.config.GenConfig;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.code.generate.service.GeneratorInterface;
import com.sungness.code.generate.service.InformationSchemaColumnService;
import com.sungness.code.generate.service.InformationSchemaTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 代码生成任务类
 *  启动后任务自动链接目标数据库，生成指定表的相关程序代码。
 * Created by wanghongwei on 4/7/16.
 */
public class GeneratorTask {
    private static final Logger log = LoggerFactory.getLogger(GeneratorTask.class);

    @Autowired
    private InformationSchemaTableService tableService;

    @Autowired
    private InformationSchemaColumnService columnService;

    private Map<String, GeneratorInterface> generatorMap;

    @Autowired
    public void setGeneratorMap(Map<String, GeneratorInterface> generatorMap) {
        this.generatorMap = generatorMap;
    }

    @Autowired
    private GenConfig genConfig;

    public void start() {
        List<InformationSchemaTable> tableList =
                tableService.getList(genConfig.getDbName(), genConfig.getTableName());
        for (InformationSchemaTable schemaTable: tableList) {
            List<InformationSchemaColumn> columnList =
                    columnService.getList(schemaTable.getTableSchema(), schemaTable.getTableName());
            for (GeneratorInterface generator: generatorMap.values()) {
                generator.execute(schemaTable, columnList);
            }
        }
    }
}
