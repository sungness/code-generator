package com.sungness.core.engine;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Freemark模板引擎类
 * @author wanghongwei
 * @since v2.0 2014-03026
 */
public class FreemarkEngine {
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String mergeTemplateIntoString(String templateName, Object model)
            throws IOException, TemplateException {
        Template template = this.configuration.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

    public String parseByStringTemplate(Object obj, String templateSource)
            throws TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        StringTemplateLoader loader = new StringTemplateLoader();
        cfg.setTemplateLoader(loader);
        cfg.setClassicCompatible(true);
        loader.putTemplate("freemaker", templateSource);
        Template template = cfg.getTemplate("freemaker");
        StringWriter writer = new StringWriter();
        template.process(obj, writer);
        return writer.toString();
    }
}
