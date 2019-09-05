package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
@ComponentScan("com.test.controller")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)      //创建Docket的Bean
                .apiInfo(apiInfo())                         //创建该Api的基本信息
                .select()      //返回一个ApiSelectorBuilder示例用来控制哪些接口   暴露给Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("com.test.controller"))   //Swagger会扫描该包下所有Controller定义的Api，并产生文档内容（除了被@ApiIgnore标注的请求）
                .paths(PathSelectors.any())
                .build();
    }

    //创建该Api的基本信息，这些信息会展现在文档页面中
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更过java相关的内容请关注：https://blog.csdn.net/hdn_kb")
                .termsOfServiceUrl("https://blog.csdn.net/hdn_kb")
                .contact("hdn")
                .version("1.0")
                .build();
    }

}
