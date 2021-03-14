package enums.create_case;

import enums.EnumValue;

public enum CreateCaseSelect implements EnumValue {
    STATUS("Status"),
    SUITE("Suite"),
    SEVERITY("Severity"),
    PRIORITY("Priority"),
    TYPE("Type"),
    MILESTONE("Milestone"),
    BEHAVIOR("Behavior"),
    AUTOMATION_STATUS("Automation status");

    private final String value;

    CreateCaseSelect(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
