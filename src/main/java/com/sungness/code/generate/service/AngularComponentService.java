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
 * Angular Component 生成器实现了
 * Created by Chwing on 2018/3/30.
 */
@Service
public class AngularComponentService extends BaseService {
    private static final Logger log = LoggerFactory.getLogger(AngularComponentService.class);

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
        log.debug("Begin generate Angular view File...");

        generate("component.html.ftl", ".component.html", table, model);
        generate("component.ts.ftl", ".component.ts", table, model);

        generate("edit.component.html.ftl", "-edit.component.html", table, model);
        generate("edit.component.ts.ftl", "-edit.component.ts", table, model);

        generate("detail.component.html.ftl", "-detail.component.html", table, model);
        generate("detail.component.ts.ftl", "-detail.component.ts", table, model);

        generate("service.ts.ftl", ".service.ts", table, model);

        generate("routing.ts.ftl", "-routing.ts", table, model);

        String FileName = genConfig.buildAngularFile(table.getLowerCaseSubName() + ".component.scss");
        FileTools.writeStringToFile(FileName, "");

        log.debug("End generate Angular view File.");
    }

    public void generate(String templateName, String fileNameSuffix,
                      InformationSchemaTable table, Map<String, Object> model) throws IOException, TemplateException {
        String Content = freemarkEngine.mergeTemplateIntoString("view/" + templateName, model);
        String FileName = genConfig.buildAngularFile(table.getLowerCaseSubName() + fileNameSuffix);
        FileTools.writeStringToFile(FileName, Content);
    }

}
