package seleniumUtil.driversManagment;

import org.openqa.selenium.JavascriptExecutor;
import utils.ActionResult;
import utils.logger.MyLogger;

public class JSExecutorSingleton {
    //This protect that no one will be able to create instance for this class
    private static JavascriptExecutor js = null;

    private JSExecutorSingleton() {
    }

    /**
     * initialize the JS
     */
    //creating the JS instance
    public static JavascriptExecutor getInstance() {
        if (js == null) {
            MyLogger.log("Starting JS singleton");
            initJSExecutor();
        }
        return js;
    }

    private static ActionResult initJSExecutor() {

        try {
            js = (JavascriptExecutor) DriverSingleton.getInstance();
            return ActionResult.SuccessResponse;
        } catch (Exception e) {
            return new ActionResult(e.getMessage());
        }
    }


}
