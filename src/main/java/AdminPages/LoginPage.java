package AdminPages;

import org.openqa.selenium.By;
import seleniumUtil.BasePage;
import utils.logger.MyLogger;
import utils.readProperties.ReadPropertiesFiles;

public class LoginPage extends BasePage {

    //*********Constructor*********
    public LoginPage() {
    }

    //*********Web Elements*********
    private final static By userName = By.xpath("//input[@id='username']");
    private final static By password = By.xpath("//input[@id='password']");
    private final static By loginBtn = By.xpath("//button[@type='submit']");

    //*********Page Methods(public)*********
    public void login(String nameKey, String passwordKey) {
        setUserName(nameKey);
        setPassword(passwordKey);
        pressLoginBtn();
    }

    //*********Page Methods(private)*********
    private void setUserName(String nameKey) {
        final String name = ReadPropertiesFiles.getValue(nameKey);
        MyLogger.logFormat("Set user name: %s", name);
        setText(userName, name);
    }

    private void setPassword(String passwordKey) {
        final String pass = ReadPropertiesFiles.getValue(passwordKey);
        MyLogger.logFormat("Set password: %s", pass);
        setText(password, pass);
    }

    private void pressLoginBtn() {
        MyLogger.log("Press 'Login' button");
        clickElement(loginBtn);
    }
}
