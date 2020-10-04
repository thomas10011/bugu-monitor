package cn.fusionfuture.bugu.user.config;

import cn.fusionfuture.bugu.pojo.constants.AuthConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class SwaggerConfig
 * @description swagger2配置类
 * @date 2020/8/18 12:47 上午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        token.name("Authorization").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .defaultValue(AuthConstants.TOKEN)
                // header中的参数非必填，传空也可以
                .required(false).build();
        // 根据每个方法名也知道当前方法在设置什么参数
        pars.add(token.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.fusionfuture.bugu.user"))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("user-service")
                .description("用户微服务接口管理")
                .termsOfServiceUrl("http://www.fusionfuture.cn")
                .version("1.0")
                .build();
    }
}
