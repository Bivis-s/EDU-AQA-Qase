package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log4j2
public class PropertyReader {
    private final Properties properties = new Properties();

    public PropertyReader(String path) throws IOException {
        try {
            log.debug("Load property file, path: '" + path + "'");
            properties.load(new FileReader(new File(path)));
        } catch (IOException e) {
            log.error("Property file '" + path + "' is not found");
            throw e;
        }
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public Map<String, String> getMap() {
        Map<String, String> propertiesMap = new HashMap<>();
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String key : propertyNames) {
            propertiesMap.put(key, getString(key));
        }
        return propertiesMap;
    }

    private Map<String, String> getSubMapByKeys(Set<String> keys) {
        Map<String, String> map = getMap();
        Map<String, String> subMap = new HashMap<>();
        for (String key : keys) {
            subMap.put(key, map.get(key));
        }
        return subMap;
    }

    public Map<String, String> getMapWithKeysLike(Predicate<String> predicate) {
        Set<String> keys = getMap()
                .keySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toSet());
        return getSubMapByKeys(keys);
    }

    @Override
    public String toString() {
        return StringUtils.join(getMap());
    }
}
