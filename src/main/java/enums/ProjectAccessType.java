package enums;

public enum ProjectAccessType implements EnumValue {
    PRIVATE("Private"),
    PUBLIC("Public");

    private final String value;

    ProjectAccessType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
