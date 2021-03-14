package enums.create_case;

import enums.EnumValue;

public enum CreateCaseField implements EnumValue {
    TITLE("Title"),
    DESCRIPTION("Description"),
    PRE_CONDITIONS("Pre-conditions"),
    POST_CONDITIONS("Post-conditions");

    private final String value;

    CreateCaseField(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
