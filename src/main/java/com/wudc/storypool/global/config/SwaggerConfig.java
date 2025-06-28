package com.wudc.storypool.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI storyPoolOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("StoryPool API").version("v1")
                        .description("Local development API documentation for StoryPool backend"));
    }
}
