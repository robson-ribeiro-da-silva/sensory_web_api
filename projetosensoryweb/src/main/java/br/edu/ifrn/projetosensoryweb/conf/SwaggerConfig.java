package br.edu.ifrn.projetosensoryweb.conf;

/*
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.edu.ifrn.projetosensoryweb"))
                //.paths(PathSelectors.ant("/api/avaliador/*"))
                .paths(PathSelectors.any())
                .build()
                //.useDefaultResponseMessages(false)
                //.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Documentação da API do SENSORYIFRN")*/
	            /*.description("Um exemplo de aplicação Spring Boot REST API") ///////comentado msm
	            .version("1.0.0")
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	            .contact(new Contact("Wladimilson", "https://treinaweb.com.br", "contato@treinaweb.com.br"))*/
	           // .build();
	//}
	
	/*
	private List<ResponseMessage> responseMessageForGET() ///////comentado msm
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("500 message")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(403)
	            .message("Forbidden!")
	            .build());
	    }};
	}
	*/

//}

