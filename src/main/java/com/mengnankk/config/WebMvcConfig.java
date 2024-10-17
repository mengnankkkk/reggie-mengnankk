package com.mengnankk.config;

import com.mengnankk.comon.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * <p>Project: rikky-takeaway - WebMvcConfig 用于静态资源映射
 * <p>Powered by Riverify On 12-14-2022 23:52:20
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 设置静态资源映射
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        long startTime = System.currentTimeMillis();
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        log.info("静态资源映射完毕 [耗时: {} ms]", System.currentTimeMillis() - startTime);
    }

    /**
     * 扩展MVC消息转换器，解决long类型数据在JSON中精度丢失的问题。
     *
     * @param converters 消息转换器列表
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("开始扩展消息转换器...");
        // 创建自定义的Jackson消息转换器
        MappingJackson2HttpMessageConverter customConverter = new MappingJackson2HttpMessageConverter();
        customConverter.setObjectMapper(new JacksonObjectMapper());

        // 将自定义的转换器添加到转换器列表的第一个位置
        converters.add(0, customConverter);

        log.info("自定义消息转换器已成功添加到消息转换器列表中");
    }
}

