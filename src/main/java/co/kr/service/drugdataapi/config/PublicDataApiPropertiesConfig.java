package co.kr.service.drugdataapi.config;

import co.kr.service.drugdataapi.model.customproperties.PublicDataApiProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicDataApiPropertiesConfig {
    @Bean
    @ConfigurationProperties(prefix = "public.data.api")
    public PublicDataApiProperties publicDataApiProperties() {
        return PublicDataApiProperties.builder().build();
    }
}
