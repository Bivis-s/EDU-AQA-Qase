package property_objects;

import enums.create_case.CreateCaseField;
import enums.create_case.CreateCaseSelect;
import enums.create_case.select_options.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CaseProperties {
    private Map<CreateCaseField, String> textFieldMap;
    private Map<CreateCaseSelect, SelectOption> selectMap;

    public CaseProperties() {
        textFieldMap = new HashMap<>();
        selectMap = new HashMap<>();
    }

    public void addTextField(CreateCaseField name, String text) {
        textFieldMap.put(name, text);
    }

    public void addSelect(CreateCaseSelect name, SelectOption option) {
        selectMap.put(name, option);
    }
}
