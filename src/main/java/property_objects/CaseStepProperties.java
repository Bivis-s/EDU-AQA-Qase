package property_objects;

import lombok.Data;

@Data
public class CaseStepProperties implements Properties {
    private String action;
    private String inputData;
    private String expectedResult;
}
