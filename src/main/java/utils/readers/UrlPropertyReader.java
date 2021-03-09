package utils.readers;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import property_objects.UrlProperties;

import java.io.IOException;

@Log4j2
public class UrlPropertyReader extends PropertyReader {
    private static final String URLS_PROPERTIES_FILE_PATH = "src/test/resources/properties/urls.properties";

    public UrlPropertyReader() throws IOException {
        super(URLS_PROPERTIES_FILE_PATH);
    }

    public String getDomain() {
        return getString("domain");
    }

    private String getPageUrl(String pageName) {
        try {
            return getDomain() + getString(pageName + "_path");
        } catch (Error e) {
            String errorMessage = "Cannot get page url '" + pageName + "'";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    public UrlProperties getPageUrl(Page page) {
        String pageName = page.getValue();
        try {
            UrlProperties properties = new UrlProperties();
            properties.setUrl(getDomain() + getString(pageName + "_path"));
            return properties;
        } catch (Error e) {
            String errorMessage = "Cannot get page url '" + pageName + "'";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    @Getter
    public enum Page {
        HOME("home"),
        LOGIN("login"),
        PROJECTS("projects");

        private final String value;

        Page(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
