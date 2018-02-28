package online.dinghuiye.mantisshrimp.config;

import online.dinghuiye.mantisshrimp.consts.MsParam;
import online.dinghuiye.mantisshrimp.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Strangeen on 2018/02/19
 */
@Configuration
//@ConditionalOnExpression("'${ms.devMode}'!='true'")
public class MyWebMVCConfig extends WebMvcConfigurerAdapter {

//    @Value("${ms.devMode}")
//    private String devMode;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        if (!"true".equals(MsParam.dev_mode))
            registry.addInterceptor(new LoginInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/login.ms", "/doLogin.ms");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(getDateFormatter());
    }

    @Bean
    public DateFormatter getDateFormatter() {
        return new DateFormatter();
    }
}
