package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.runner.RunWith;
import seleniumUtil.driversManagment.DriverSingleton;
import utils.ActionResult;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/reports/htmlReports/cucumberReport.html",
                "json:target/reports/jsonReports/cucumberReport.json"},
//        tags = "@test",
        features = "C:\\git\\Autotest_6_5\\WebAdmin\\Features\\Documentation.feature",
        glue = "stepDefinitions",
        monochrome = true,
        publish = true
)
public class TestRunner {
    @AfterClass
    public static void closeBrowser() {
        ActionResult closeBrowser = DriverSingleton.quitBrowser();
        Assert.assertTrue(closeBrowser.errorMessage, closeBrowser.isSuccess);
    }
}
