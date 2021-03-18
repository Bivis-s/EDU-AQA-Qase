package wrappers;

import lombok.Data;
import property_objects.Properties;

@Data
public class PropertiesWrapper<T extends Properties> {
    private T properties;
}
