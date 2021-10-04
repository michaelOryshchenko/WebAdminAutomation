package AdminPages;

import org.openqa.selenium.By;
import seleniumUtil.BasePage;
import utils.logger.MyLogger;

public class HamburgerMenuPage extends BasePage {

    //*********Constructor*********
    public HamburgerMenuPage() {
    }

    //*********Web Elements*********
    private final static By menu = By.xpath("//menu-btn");
    private final static By documentation = By.xpath("//li[text()='Documentation']");

    //*********Page Methods(public)*********
    public void openMenu() {
        MyLogger.log("Open Hamburger Menu");
        clickElement(menu);
    }

    public void openDocumentation() {
        MyLogger.log("Open Documentation");
        clickElement(documentation);
    }

}
