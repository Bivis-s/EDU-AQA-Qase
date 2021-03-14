package pageobjects.app;

import org.openqa.selenium.WebDriver;

public class DeleteProjectPage extends LoadableAppPage<DeleteProjectPage> {
    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForPageLoaded() {
    }

    @Override
    protected String getPageUrl() {
        return null;
    }
}
