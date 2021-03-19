package property_objects;

import enums.create_case.CreateCaseField;
import enums.create_case.CreateCaseSelect;
import enums.create_case.select_options.SelectOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CaseProperties {
    private Map<CreateCaseField, String> textFieldMap;
    private Map<CreateCaseSelect, SelectOption> selectMap;
    private List<CaseStepProperties> steps;

    {
        textFieldMap = new HashMap<>();
        selectMap = new HashMap<>();
        steps = new ArrayList<>();
    }

    public void addTextField(CreateCaseField name, String text) {
        textFieldMap.put(name, text);
    }

    public void addSelect(CreateCaseSelect name, SelectOption option) {
        selectMap.put(name, option);
    }

    public void addStep(CaseStepProperties step) {
        steps.add(step);
    }
}
