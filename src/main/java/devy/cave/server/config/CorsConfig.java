package devy.cave.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS 관련 설정
 *
 * CORS 관련 설정전에 Security 설정에서 CORS 관련 설정을 사용하도록 설정하여야 한다.
 * 설정하지 않으면 설정을 시도하지만 적용은 되지 않는다.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 하단의 allowdMethods 설정에서 OPTIONS 값이 포함될 경우 client에서 cors 오류 발생함
        registry.addMapping("/api/user/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true);
    }
}