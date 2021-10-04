package seleniumUtil.driversManagment.chromeOptions;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class ChromeCommonSettings {

    //*********Decelerations*********
    private final static String spt = File.separator;
    private final static String home = System.getProperty("user.home");
    private final static String downloadFilepath = home + spt + "Downloads" + spt;

    //*********Methods*********
    public ChromeOptions getChromeSettings(Map<String, Object> preferences) {
        final ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
        options.setExperimentalOption("prefs", preferences);
        return options;
    }

    public Map<String, Object> getCommonOptions() {
        final Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", downloadFilepath);
        return preferences;
    }


}
