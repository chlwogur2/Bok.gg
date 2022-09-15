package choi.bok.gg.global.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// ObjectMapper 는 생성비용이 비싸다고해서 Bean 으로 등록하여 관리하고 있다.
@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
