package property_objects;

import lombok.Data;

@Data
public class ApiUrlsProperties implements Properties {
    private String createNewProjectUrl;
    private String createNewSuiteUrl;
}
