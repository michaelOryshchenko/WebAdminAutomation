package seleniumUtil.driversManagment;

import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionResult;
import utils.logger.MyLogger;
import utils.readProperties.ReadPropertiesFiles;

import java.time.Duration;

public class WaitSingleton {
    private static WebDriverWait wait = null;
    private static final String waitTimeStr = ReadPropertiesFiles.getValue("waitSingletonTimeSec");
    private static final Duration NUMBER_OF_SECONDS =  Duration.ofSeconds(Long.parseLong(waitTimeStr));
    //This protect that no one will be able to create instance for this class
    private WaitSingleton() {
    }

    /**
     * initialize the Wait
     */

    //creating the driver instance
    public static WebDriverWait getInstance() {
        if (wait == null) {
            MyLogger.log("Starting wait singleton");
            initWait();
        }
        return wait;
    }

    private static ActionResult initWait() {
        try {
            wait = new WebDriverWait(DriverSingleton.getInstance(), NUMBER_OF_SECONDS);
            return ActionResult.SuccessResponse;
        } catch (Exception e) {
            return new ActionResult(e.getMessage());
        }
    }

}
