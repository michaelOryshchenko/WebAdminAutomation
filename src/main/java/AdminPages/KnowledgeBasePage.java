package AdminPages;

import org.openqa.selenium.By;
import seleniumUtil.BasePage;
import utils.logger.MyLogger;

public class KnowledgeBasePage extends BasePage {

    //*********Constructor*********
    public KnowledgeBasePage() {
    }

    //*********Web Elements*********
    public final static By activeTab = By.xpath("//a[@class='Wiki selected header-content' and text()='Knowledge Base']");

    //*********Page Methods(public)*********
    public void verifyKnowledgePage() {
        MyLogger.log("Verify Knowledge Base Page is loaded");
        fwVisibility(activeTab);
    }

}
