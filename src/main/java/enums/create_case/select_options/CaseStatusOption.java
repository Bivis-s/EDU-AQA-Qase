package enums.create_case.select_options;

public enum CaseStatusOption implements SelectOption {
    ACTUAL("Actual"),
    DRAFT("Draft"),
    DEPRECATED("Deprecated");

    private final String value;

    CaseStatusOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
