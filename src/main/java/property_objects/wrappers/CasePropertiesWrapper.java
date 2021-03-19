package property_objects.wrappers;

import enums.create_case.CreateCaseField;
import lombok.Data;
import property_objects.CaseProperties;

@Data
public class CasePropertiesWrapper {
    CaseProperties caseProperties;

    public String getCaseTitle() {
        return caseProperties.getTextFieldMap().get(CreateCaseField.TITLE);
    }
}
