package com.example.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux
import java.time.YearMonth

@Configuration
@EnableSwagger2WebFlux
@ConditionalOnClass(Docket::class)
@ConditionalOnMissingBean(Docket::class)
class SwaggerConfiguration  {

    @Value("\${SERVER_CONTEXT_PATH:/}")
    private val contextPath: String? = null

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .pathMapping(contextPath)
            .apiInfo(getApiInfo())
            .directModelSubstitute(YearMonth::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Documentação da minha API")
            .description("spring-reactive-kotlin-gradle")
            .version("1.0.0")
            .build()
    }
}