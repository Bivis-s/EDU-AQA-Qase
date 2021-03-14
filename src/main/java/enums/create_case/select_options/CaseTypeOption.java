package enums.create_case.select_options;

public enum CaseTypeOption implements SelectOption {
    OTHER("Other"),
    FUNCTIONAL("Functional"),
    SMOKE("Smoke"),
    REGRESSION("Regression"),
    SECURITY("Security"),
    USABILITY("Usability"),
    PERFORMANCE("Performance"),
    ACCEPTANCE("Acceptance"),
    COMPATIBILITY("Compatibility"),
    INTEGRATION("Integration"),
    EXPLORATORY("Exploratory");

    private final String value;

    CaseTypeOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
