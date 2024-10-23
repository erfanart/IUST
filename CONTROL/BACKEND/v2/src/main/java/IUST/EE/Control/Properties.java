package IUST.EE.Control;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class Properties {
    public Map<String, Object> DataStore = loadYamlAsMap();

    private Map<String, Object> loadYamlAsMap() {
        YamlMapFactoryBean yaml = new YamlMapFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Map<String, Object> data = yaml.getObject();
        @SuppressWarnings({ "null", "unchecked" })
        Map<String, Object> Data = (Map<String, Object>) data.get("data-store");
        return Data;
    }
}