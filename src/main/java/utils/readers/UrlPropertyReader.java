package utils.readers;

import enums.UrlPageName;
import lombok.extern.log4j.Log4j2;
import property_objects.UrlProperties;

@Log4j2
public class UrlPropertyReader extends PropertyReader {
    private static final String URLS_PROPERTIES_FILE_PATH = "src/test/resources/properties/urls.properties";

    public UrlPropertyReader() {
        super(URLS_PROPERTIES_FILE_PATH);
    }

    public String getDomain(String pageName) {
        return getString(pageName + "_domain");
    }

    private String getPageUrl(String pageName) {
        return getDomain(pageName) + getString(pageName + "_path");
    }

    public UrlProperties getPageUrl(UrlPageName page) {
        String pageName = page.getValue();
        UrlProperties properties = new UrlProperties();
        properties.setUrl(getPageUrl(pageName));
        return properties;
    }
}
