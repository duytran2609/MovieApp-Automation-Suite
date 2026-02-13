package base;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(Long.parseLong(ConfigReader.get("timeout"))));
    }

    protected WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Action group
    protected void click(By locator) {
        log.debug("Waiting for element clickable: {}", locator);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        log.debug("Click on element [{}] on {}", locator, driver.getCurrentUrl());
        el.click();
    }

    protected void type(By locator, String text) {
        log.debug("Type text into {}", locator);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        el.sendKeys(text);
    }

    // Get group
    protected String getText(By locator) {
        return findVisible(locator).getText();
    }

    protected String getAttribute(By locator, String attribute) {
        return findVisible(locator).getAttribute(attribute);
    }

    protected String getCssValue(By locator, String value) {
        return findVisible(locator).getCssValue(value);
    }

    protected String getTagName(By locator) {
        return findVisible(locator).getTagName();
    }

    // Check group
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isEnabled(By locator) {
        return findVisible(locator).isEnabled();
    }

    protected boolean isSelected(By locator) {
        return findVisible(locator).isSelected();
    }
}
