package AdminPages;

import org.openqa.selenium.By;
import seleniumUtil.BasePage;
import utils.logger.MyLogger;

public class DocumentationPage extends BasePage {

    //*********Constructor*********
    public DocumentationPage() {
    }

    //*********Web Elements*********
    public final static String menuItem = "//p/a[text()='?']";
    public final static By iframe = By.xpath("//iframe[@class='micro-app-container']");

    //*********Page Methods(public)*********
    public void openDocumentationMenuItem(String item) {
        switchToIframe(iframe);
        MyLogger.logFormat("Open %s", item);
        By menuItemBy = getXpath(menuItem, item);
        clickElement(menuItemBy);
    }

}
