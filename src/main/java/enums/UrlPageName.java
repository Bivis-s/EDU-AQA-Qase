package enums;

import lombok.Getter;

@Getter
public enum UrlPageName {
    HOME("home"),
    LOGIN("login"),
    PROJECTS("projects");

    private final String value;

    UrlPageName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}