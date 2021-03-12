package enums.create_case.select_options;

public enum CaseSeverityOption implements SelectOption {
    NOT_SET("Not set"),
    BLOCKER("Blocker"),
    CRITICAL("Critical"),
    NORMAL("Normal"),
    MINOR("Minor"),
    TRIVIAL("Trivial");

    private final String value;

    CaseSeverityOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
