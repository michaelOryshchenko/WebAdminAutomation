package seleniumUtil;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import seleniumUtil.driversManagment.DriverSingleton;
import seleniumUtil.driversManagment.WaitSingleton;
import utils.logger.MyLogger;
import utils.readProperties.ReadPropertiesFiles;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BasePage {

    //*********Declarations*********
    private static final int TIMEOUT = Integer.parseInt(ReadPropertiesFiles.getValue("fluentWaitTimeout"));
    private static final int POLLING = Integer.parseInt(ReadPropertiesFiles.getValue("fluentWaitPolling"));
    private static FluentWait<WebDriver> fluentWait;

    //*********Waits*********

    public void waitUntilClickable(By elementBy) {
        MyLogger.logFormat("Start wait until element: %s clickable", elementBy);
        retry(() -> (WaitSingleton.getInstance().until(ExpectedConditions.elementToBeClickable(elementBy)) != null));
    }

    public void fwVisibility(By elementBy) {
        MyLogger.logFormat("Start fluent wait for visibility of element: %s", elementBy);
        getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public FluentWait<WebDriver> getFluentWait() {
        if (fluentWait == null) {
            fluentWait = new FluentWait<>(DriverSingleton.getInstance())
                    .withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofMillis(POLLING))
                    .ignoreAll(configureFluentExceptions());
        }
        return fluentWait;
    }

    private List<Class<? extends Exception>> configureFluentExceptions() {
        final List<Class<? extends Exception>> allExceptions = new ArrayList<>();
        allExceptions.add(NoSuchElementException.class);
        allExceptions.add(ElementNotInteractableException.class);
        allExceptions.add(ElementClickInterceptedException.class);
        allExceptions.add(StaleElementReferenceException.class);
        return allExceptions;
    }

    private void retry(Supplier<Boolean> waitMethod) {
        final String maxRetriesCount = ReadPropertiesFiles.getValue("basePageMaxRetries");
        final int maxRetries = Integer.parseInt(maxRetriesCount);
        for (int i = 0; i < maxRetries; i++) {
            MyLogger.logFormat("Retry iteration is: %s", i);
            if (tryCatchWaitMethod(waitMethod)) {
                break;
            }
            Assertions.assertNotEquals(maxRetries - 1, i, "Element not found. Retried " +
                    i + "times with method: " + waitMethod.get().toString());
        }
    }

    private boolean tryCatchWaitMethod(Supplier<Boolean> waitMethod) {
        try {
            if (waitMethod.get()) {
                return true;
            }
        } catch (Exception e) {
            MyLogger.logFormat("Wait failed, exception: %s", e.toString());
        }
        return false;
    }

    //*********Driver Methods*********

    public void get(String url) {
        MyLogger.logFormat("Browse to URL: %s", url);
        DriverSingleton.getInstance().get(url);
    }

    public void clickElement(By elementBy) {
        MyLogger.logFormat("Click on elementBy: %s", elementBy);
        try {
            waitUntilClickable(elementBy);
            DriverSingleton.getInstance().findElement(elementBy).click();
        } catch (StaleElementReferenceException stale) {
            MyLogger.logFormat("First click on elementBy: %s didn't work due to Stale element error, trying again", elementBy);
            DriverSingleton.getInstance().findElement(elementBy).click();
        }
    }

    public WebElement findElement(By elementBy) {
        return DriverSingleton.getInstance().findElement(elementBy);
    }

    public By getElement(String text, Object ...args) {
        final Object[] arguments = Arrays.stream(args).toArray();
        return By.xpath(String.format(text, arguments));
    }

    public void setText(By element, String text) {
        MyLogger.logFormat("Set text to input: %s", text);
        waitUntilClickable(element);
        WebElement input = findElement(element);
        input.click();
        input.sendKeys(text);
    }

    public By getXpath(String str1, String str2) {
        str1 = str1.replace("?", str2);
        return By.xpath(str1);
    }

    public void switchToIframe(By element) {
        MyLogger.log("Switch to Iframe");
        WebElement iframeElement = DriverSingleton.getInstance().findElement(element);
        DriverSingleton.getInstance().switchTo().frame(iframeElement);
//        DriverSingleton.getInstance().quit();
    }

    public void sleep(int mls) {
        MyLogger.logFormat("Sleep %d", mls);
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}