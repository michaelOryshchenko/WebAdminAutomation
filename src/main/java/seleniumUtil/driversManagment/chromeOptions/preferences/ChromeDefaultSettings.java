package seleniumUtil.driversManagment.chromeOptions.preferences;

import org.openqa.selenium.chrome.ChromeOptions;
import seleniumUtil.driversManagment.chromeOptions.ChromeCommonSettings;
import seleniumUtil.driversManagment.chromeOptions.IChromeSettings;

import java.util.Map;

public class ChromeDefaultSettings implements IChromeSettings {

    //*********Methods*********

    @Override
    public ChromeOptions getOptions() {
        ChromeCommonSettings chromeCommonSettings = new ChromeCommonSettings();
        Map<String, Object> preferences = chromeCommonSettings.getCommonOptions();
        setAutoDownloadDisabledOption(preferences);
        return chromeCommonSettings.getChromeSettings(preferences);
    }

    private void setAutoDownloadDisabledOption(Map<String, Object> preferences) {
        preferences.put("profile.default_content_setting_values.automatic_downloads", 1);
    }


}
