package enums.create_case.select_options;

public enum CaseAutomationStatusOption implements SelectOption {
    NOT_AUTOMATED("Not automated"),
    TO_BE_AUTOMATED("To be automated"),
    AUTOMATED("Automated");

    private final String value;

    CaseAutomationStatusOption(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
