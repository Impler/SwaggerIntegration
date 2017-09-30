package com.study.swagger.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("impler")
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = new ArrayList<SecurityContext>();
        return contexts;
    }

    private List<? extends SecurityScheme> securitySchemes() {
        List<SecurityScheme> schemas = new ArrayList<SecurityScheme>();
        return schemas;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Impler Apis")
                .description("Impler Apis details")
                .license("copyright©impler.cn")
                .version("1.0")
                .build();
    }

}
