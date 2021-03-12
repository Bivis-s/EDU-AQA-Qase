package enums.create_case.select_options;

public enum CasePriorityOption implements SelectOption {
    NOT_SET("Not set"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String value;

    CasePriorityOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
