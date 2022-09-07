package frontend.Erasmus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
@EnableWebMvc
public class ThymeleafConfig {

    //Konfiguracija thymeleaf templejta

    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        return templateResolver;
    }
    @Bean
    public TemplateEngine templateEngine() {
        var templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;
    }

}
