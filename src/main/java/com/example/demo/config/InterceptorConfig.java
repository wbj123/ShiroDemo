package com.example.demo.config;

import com.example.demo.config.filter.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: LotteryLuckSmallApp
 * @description: InterceptorConfig
 * @author: Mr.Wang
 * @create: 2018-05-25 17:14
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    //加上这个后  解决了在拦截器中 无法注入（注入为null）的情况
    @Bean
    public MyInterceptor MyInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里可以添加多个拦截器
        registry.addInterceptor(MyInterceptor())
                .addPathPatterns("/**");
//                .addPathPatterns("/*/*")
//                .addPathPatterns("/*/*/*");
        super.addInterceptors(registry);
    }

}
