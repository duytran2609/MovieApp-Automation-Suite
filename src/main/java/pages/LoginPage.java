package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // ===== LOCATORS =====
    private By inputEmail = By.id("email");
    private By inputPassword = By.id("password");
    private By btnLogin = By.cssSelector(".login-btn");
    private By formLogin = By.cssSelector(".login-box");
    private By txtForgetPass = By.xpath("//*[@id=\"root\"]/div/div/div/form/div[3]/a");
    private By txtRegister = By.xpath("//*[@id=\"root\"]/div/div/div/div/p/a");

    // ===== PAGE OBJECTS =====
    private HomePage homePage;

    // ===== ACTIONS =====
    public HomePage login(String email, String password) {
        try {
            type(inputEmail, email);
            type(inputPassword, password);
            click(btnLogin);
        } catch(Exception e) {
            log.error("Login fail because: ", e);
        }
        return new HomePage(driver);
    }

    public ForgetPasswordPage forgetPassword() {
        try {
            click(txtForgetPass);
        } catch(Exception e) {
            log.error("Cannot navigate to forget password page");
        }
        return new ForgetPasswordPage();
    }

    public RegisterPage register() {
        try {
            click(txtRegister);
        } catch(Exception e) {
            log.error("Cannot navigate to register page");
        }
        return new RegisterPage();
    }

    // ===== GETTERS / STATES =====
    public boolean isLoginFormDisplayed() {
        return isDisplayed(formLogin);
    }

    public boolean isEmailInputDisplayed() {
        return isDisplayed(inputEmail);
    }

    public boolean isPasswordInputDisplayed() {
        return isDisplayed(inputPassword);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(btnLogin);
    }

    // ===== HIGH-LEVEL / BUSINESS METHODS =====
    public boolean isLoginSuccess() {
        return wait.until(
                ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/")
        );
    }

    public boolean isLoginFailed() {
        return wait.until(
                ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/login")
        );
    }
}
