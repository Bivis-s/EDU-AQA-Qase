package pageobjects;

import enums.UrlPageName;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import setups.CustomLoadableComponent;
import utils.readers.UrlPropertyReader;

@Log4j2
public abstract class BasePage<T extends CustomLoadableComponent<T>> extends CustomLoadableComponent<T> {
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    protected abstract String getPageUrl();

    @Override
    protected T load() {
        try {
            return openPage();
        } catch (Error e) {
            return refreshPage();
        }
    }

    public T openPage() {
        String pageUrl = getPageUrl();
        log.info("Open page with URL '" + pageUrl + "'");
        getDriver().get(pageUrl);
        return get();
    }

    @SuppressWarnings("unchecked")
    protected T refreshPage() {
        log.info("Refresh " + this.getClass().getSimpleName() + " page, URL: '" + getDriver().getCurrentUrl() + "'");
        getDriver().navigate().refresh();
        return (T) this;
    }

    protected String getUrlFromProperty(UrlPageName page) {
        log.trace("Get url from property, page '" + page + "'");
        return new UrlPropertyReader().getPageUrl(page).getUrl();
    }
}
