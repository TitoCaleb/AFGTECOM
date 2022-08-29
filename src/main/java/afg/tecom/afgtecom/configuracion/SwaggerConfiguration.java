package afg.tecom.afgtecom.configuracion;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pe.csti.capa.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .forCodeGeneration(true);
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Proyecto AFG tecnologia y comercio e.i.r.l.",
                "Aplicacion Backend",
                "Sistema Backend version 1.0",
                "Derechos reservados",
                new Contact("Caleb Castro", "calebcastro2604@gmail.com", "calebcastro2604@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application-description}") String appDesciption,
            @Value("${application-version}") String appVersion) {
        io.swagger.v3.oas.models.info.Contact contact = new io.swagger.v3.oas.models.info.Contact();
        contact.setEmail("calebcastro2604@gmail.com");
        contact.setName("Caleb Castro");
        return new OpenAPI()
                .info(new Info()
                        .title("Proyecto AFG tecnologia y comercio e.i.r.l.")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
