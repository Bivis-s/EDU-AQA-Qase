package pageobjects;

import enums.UrlPageName;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
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
        return refreshPage();
    }

    @SuppressWarnings("unchecked")
    public T openPage(){
        getDriver().get(getPageUrl());
        return (T) this;
    }

    protected void open(String url) {
        getDriver().get(url);
    }

    @SuppressWarnings("unchecked")
    protected T refreshPage() {
        log.info("Refresh " + this.getClass().getSimpleName() + " page, URL: '" + getDriver().getCurrentUrl() + "'");
        getDriver().navigate().refresh();
        return (T) this;
    }

    protected String getUrlFromProperty(UrlPageName page) {
        return new UrlPropertyReader().getPageUrl(page).getUrl();
    }

    @SuppressWarnings("unchecked")
    public T scrollDown() {
        sendKeys(findElementByXpath("//body"), Keys.CONTROL, Keys.END);
        return (T) this;
    }
}
