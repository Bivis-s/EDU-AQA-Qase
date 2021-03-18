package property_objects;

import lombok.Data;

@Data
public class SuiteProperties {
    private String name;
    private String parentSuiteName;
    private String description;
    private String precondition;
}
