package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgetPasswordPage extends BasePage {

    // ===== LOCATORS =====
    private By inputEmail = By.id("email");
    private By btnSubmit = By.className("forgot-password-btn");
    private By txtBackLogin = By.xpath("//*[@id=\"root\"]/div/div/div/div/p");

    // ===== ACTIONS =====
    public void inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail)).clear();
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void clickSendButton() {
        driver.findElement(btnSubmit).click();
    }

    // ===== GETTERS / STATES =====

    // ===== HIGH-LEVEL / BUSINESS METHODS =====
    public boolean isSendSuccess() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(btnSubmit));
    }
}
