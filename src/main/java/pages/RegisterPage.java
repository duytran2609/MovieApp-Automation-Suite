package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    // ===== LOCATORS =====
    private By inputUsername = By.id("username");
    private By inputEmail = By.id("email");
    private By inputPassword = By.id("password");
    private By btnRegister = By.className("register-btn");
    private By txtLogin = By.xpath("//*[@id=\"root\"]/div/div/div/div/p/a");
    private By msgError = By.className("error-message");

    // ===== PAGE OBJECTS =====
    private LoginPage loginPage;

    // ===== ACTIONS =====
    public void inputUsername(String username) {
        log.trace("Input username: [{}]", username);
        type(inputUsername, username);
    }

    public void inputEmail(String email) {
        log.trace("Input email: [{}]", email);
        type(inputEmail, email);
    }

    public void inputPassword(String password) {
        log.trace("Input password: [{}]", password);
        type(inputPassword, password);
    }

    public void clickRegisterButton() {
        click(btnRegister);
    }

    public void clickLoginLinkText() {
        click(txtLogin);
    }

    // ===== GETTERS / STATES =====
    public boolean isUsernameValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findVisible(inputUsername);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", e);
    }

    public boolean isEmailValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findVisible(inputEmail);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", e);
    }

    public boolean isPasswordValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findVisible(inputPassword);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", e);
    }

    public boolean isExistingDataErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(msgError)
        ).isDisplayed();
    }

    public String getExistingDataErrorMessage() {
        return findVisible(msgError).getText();
    }

    // ===== HIGH-LEVEL / BUSINESS =====
    public boolean isRegisterSuccess() {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(btnRegister)
        );
    }

    public boolean isBackToLoginSuccess() {
        return wait.until(
                ExpectedConditions.urlContains("login")
        );
    }
}
