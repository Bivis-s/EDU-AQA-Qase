package enums.create_case.select_options;

public enum CaseBehaviorOption implements SelectOption {
    NOT_SET("Not set"),
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    DESTRUCTIVE("Destructive");

    private final String value;

    CaseBehaviorOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
