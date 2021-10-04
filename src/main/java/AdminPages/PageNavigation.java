package AdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import seleniumUtil.BasePage;
import seleniumUtil.driversManagment.DriverSingleton;
import utils.ActionResult;
import utils.logger.MyLogger;
import utils.readProperties.ReadPropertiesFiles;

import java.util.ArrayList;

public class PageNavigation extends BasePage {

    //*********Constructor*********
    public PageNavigation() {
    }

    //*********Web Elements*********
    private final static By header = By.xpath("//h1[text()='Login']");

    //*********Page Methods*********
    public ActionResult loadPage(String urlKey) {
        final String url = ReadPropertiesFiles.getValue(urlKey);
        MyLogger.logFormat("Browse: %s", url);
        return fullBrowseToPage(url);
    }

    public void switchToTab(int i) {
        MyLogger.logFormat("Switch to tab %d", i);
        sleep(1000);
        ArrayList<String> newTab = new ArrayList<String>(DriverSingleton.getInstance().getWindowHandles());
        DriverSingleton.getInstance().switchTo().window(newTab.get(i));
    }

    private ActionResult fullBrowseToPage(String url) {
        try {
            get(url);
            waitUntilClickable(header);
            return ActionResult.SuccessResponse;
        } catch (Exception e) {
            return new ActionResult(e.getMessage());
        }
    }

}
