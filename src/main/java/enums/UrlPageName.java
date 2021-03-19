package enums;

import lombok.Getter;

@Getter
public enum UrlPageName implements EnumValue {
    HOME("home"),
    LOGIN("login"),
    PROJECTS("projects"),
    PROJECT("project"),
    CREATE_NEW_CASE("create_test_case");

    private final String value;

    UrlPageName(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
