package seleniumUtil.driversManagment;
//https://www.geeksforgeeks.org/singleton-class-java/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import seleniumUtil.driversManagment.chromeOptions.IChromeSettings;
import seleniumUtil.driversManagment.chromeOptions.preferences.ChromeAutoDownloadOnSettings;
import seleniumUtil.driversManagment.chromeOptions.preferences.ChromeDefaultSettings;
import utils.ActionResult;
import utils.logger.MyLogger;
import utils.readProperties.ReadPropertiesFiles;

import java.io.File;
import java.util.ArrayList;

public class DriverSingleton {
    private static WebDriver driver = null;
    private static final String spt = File.separator;

    /*This protects that no one will be able to create instance for this class*/
    private DriverSingleton() {
    }

    /*creating the driver instance*/
    public static WebDriver getInstance() {
        if (driver == null) {
            MyLogger.log("Starting webDriver singleton");
            initDriver();
        }
        return driver;
    }

    /* driver with specific configuration*/
    public static WebDriver getInstance(String optionsKey) {
        if (driver == null) {
            MyLogger.log("Starting webDriver singleton with custom settings: " + optionsKey);
            IChromeSettings chromeSettings;
            final String options = ReadPropertiesFiles.getValue(optionsKey);
            switch (options) {
                case "chromeAutoDownloadOptions":
                    chromeSettings = new ChromeAutoDownloadOnSettings();
                    break;
                default:
                    chromeSettings = new ChromeDefaultSettings();
            }
            initDriver(chromeSettings.getOptions());
        }
        return driver;
    }

    /* initialize the driver with default settings*/
    private static void initDriver() {
        final ChromeDefaultSettings chromeSettings = new ChromeDefaultSettings();
        startDriver(chromeSettings.getOptions());
    }

    /* initialize the driver with specific settings*/
    private static void initDriver(ChromeOptions options) {
        startDriver(options);
    }

    private static void startDriver(ChromeOptions options) {
        String safeDriverPath = "." + spt + "src" + spt + "main" + spt + "resources" + spt + "drivers" + spt + ReadPropertiesFiles.getValue("driver");
        System.setProperty("webdriver.chrome.driver", safeDriverPath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        MyLogger.logFormat("Browser resolution is: %s", driver.manage().window().getSize());
    }

    public static ActionResult quitBrowser() {
        try {
            MyLogger.log("Quit webDriver");
            driver.quit();
            return ActionResult.SuccessResponse;
        } catch (Exception e) {
            return new ActionResult(e.getMessage());
        }
    }

}
