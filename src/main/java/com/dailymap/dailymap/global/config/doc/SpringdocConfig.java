package com.dailymap.dailymap.global.config.doc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DROP RESTful API Spec")
                        .description("서버에서 제공하는 REST API 스펙을 기술합니다")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Notion for This Project")
                        .url("https://www.notion.so/byeongsoon/DROP-881b5e0084f54f76985ca12b4211a91c"));
    }

}
