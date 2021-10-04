package seleniumUtil.driversManagment.chromeOptions.preferences;

import org.openqa.selenium.chrome.ChromeOptions;
import seleniumUtil.driversManagment.chromeOptions.ChromeCommonSettings;
import seleniumUtil.driversManagment.chromeOptions.IChromeSettings;

import java.util.Map;

public class ChromeAutoDownloadOnSettings implements IChromeSettings {

    //*********Methods*********
    @Override
    public ChromeOptions getOptions() {
        ChromeCommonSettings chromeCommonSettings = new ChromeCommonSettings();
        Map<String, Object> preferences = chromeCommonSettings.getCommonOptions();
        return chromeCommonSettings.getChromeSettings(preferences);
    }


}




