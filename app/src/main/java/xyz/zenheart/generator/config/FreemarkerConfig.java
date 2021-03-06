package xyz.zenheart.generator.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import xyz.zenheart.generator.utils.FtlUtils;
import xyz.zenheart.generator.utils.PathUtils;

/**
 * <p>项目名称: generator </p>
 * <p>描述: freemarker配置类 </p>
 * <p>创建时间: 2021/9/16 </p>

 *
 * @author CKM
 * @version v1.0
 */
@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {

    @Bean("freemarkerConfiguration")
    public Configuration freemarkerConfiguration() {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        cfg.setClassForTemplateLoading(FtlUtils.class, PathUtils.FTL);

        cfg.setDefaultEncoding("UTF-8");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }
}
