package property_objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CaseStepProperties {
    private String action;
    private String inputData;
    private String expectedResult;
}
