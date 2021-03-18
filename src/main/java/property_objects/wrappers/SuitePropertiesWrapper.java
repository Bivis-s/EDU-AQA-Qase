package property_objects.wrappers;

import lombok.Data;
import property_objects.SuiteProperties;

@Data
public class SuitePropertiesWrapper {
    SuiteProperties suiteProperties;

    public String getSuiteName() {
        return suiteProperties.getName();
    }
}
