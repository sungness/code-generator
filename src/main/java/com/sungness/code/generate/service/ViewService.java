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
 * View 层JSP文件生成器实现类
 * Created by wanghongwei on 4/7/16.
 */
//@Service
public class ViewService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(ViewService.class);

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
        log.debug("Begin generate JSP view File...");
        String listContent = freemarkEngine.mergeTemplateIntoString("list.jsp.ftl", model);
        String listFileName = genConfig.buildJspFileName("list.jsp");
        FileTools.writeStringToFile(listFileName, listContent);

        String editContent = freemarkEngine.mergeTemplateIntoString("edit.jsp.ftl", model);
        String editFileName = genConfig.buildJspFileName("edit.jsp");
        FileTools.writeStringToFile(editFileName, editContent);

        String formContent = freemarkEngine.mergeTemplateIntoString("editForm.jsp.ftl", model);
        String formFileName = genConfig.buildJspFileName("editForm.jsp");
        FileTools.writeStringToFile(formFileName, formContent);

        String detailContent = freemarkEngine.mergeTemplateIntoString("detail.jsp.ftl", model);
        String detailFileName = genConfig.buildJspFileName("detail.jsp");
        FileTools.writeStringToFile(detailFileName, detailContent);
        log.debug("End generate JSP view File.");
    }
}