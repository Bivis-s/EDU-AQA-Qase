package property_objects;

import lombok.Data;

@Data
public class CaseStepProperties {
    private String action;
    private String inputData;
    private String expectedResult;
}
