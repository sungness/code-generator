package com.sungness.code.generate.service;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.code.generate.util.FileTools;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Service 层Java文件生成器实现类
 * Created by wanghongwei on 4/7/16.
 */
@Service
public class ServiceService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(ServiceService.class);

    /**
     * 代码生成
     *
     * @param table InformationSchemaTable Schema 表对象
     * @param model Map<String, Object> Freemark 数据对象集
     * @throws IOException
     * @throws TemplateException
     */
    @Override
    public void generate(InformationSchemaTable table, Map<String, Object> model)
            throws IOException, TemplateException {
        log.debug("Begin generate Service java File...");
        String content = freemarkEngine.mergeTemplateIntoString("service.ftl", model);
        String fileName = genConfig.buildServiceFileName(table.getUpperCaseName() + "Service.java");
        FileTools.writeStringToFile(fileName, content);
        log.debug("End generate Service java File.");
    }
}