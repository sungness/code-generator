package com.sungness.code.generate.service;

import com.sungness.code.generate.config.GenConfig;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.core.engine.FreemarkEngine;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码文件生成器实现类基类
 * Created by wanghongwei on 4/7/16.
 */
public abstract class BaseService implements GeneratorInterface {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected FreemarkEngine freemarkEngine;

    @Autowired
    protected GenConfig genConfig;

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
            String genDate = DateFormatUtils.format(new Date(), "M/d/yy");
            Map<String, Object> model = new HashMap<>();
            model.put("genDate",genDate);
            model.put("table", table);
            model.put("columnList",columnList);
            model.put("viewPath", genConfig.getViewPath());
            model.put("searchColumnName", genConfig.getSearchColumnName());
            model.put("modulePkg", genConfig.getModulePkg());
            model.put("modulePath", genConfig.getModulePath());
            generate(table, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 代码生成
     * @param table InformationSchemaTable Schema 表对象
     * @param model Map<String, Object> Freemark 数据对象集
     * @throws IOException
     * @throws TemplateException
     */
    public abstract void generate(
            InformationSchemaTable table, Map<String, Object> model)
            throws IOException, TemplateException;
}
