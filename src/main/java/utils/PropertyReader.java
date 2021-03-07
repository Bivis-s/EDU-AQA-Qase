package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Log4j2
public class PropertyReader {
    private final Properties properties = new Properties();

    public PropertyReader(String path) throws IOException {
        try {
            properties.load(new FileReader(new File(path)));
        } catch (IOException e) {
            log.error("Property file '" + path + "' is not found");
            throw e;
        }
    }

    public String getString(String key) {
        return properties.getProperty(key).toLowerCase();
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public Map<String, String> toMap() {
        Map<String, String> propertiesMap = new HashMap<>();
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String key : propertyNames) {
            propertiesMap.put(key, getString(key));
        }
        return propertiesMap;
    }

    @Override
    public String toString() {
        return StringUtils.join(toMap());
    }
}
