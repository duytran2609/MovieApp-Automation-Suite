package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private HomePage homePage;

    // Khai báo locators
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("password");
    private By btnLogin = By.cssSelector(".login-btn");
    private By formLogin = By.cssSelector(".login-box");

    // Khởi tạo constructor có tham số
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Khai báo các hành động
    public HomePage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(email);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(btnLogin).click();
        return new HomePage(driver);
    }

    // Khai báo logic
    public boolean isLoginSuccess() {
        return wait.until(ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/"));
    }

    public boolean isLoginFailed() {
        return wait.until(ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/login"));
    }

    // Khai báo UI component
    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formLogin)).isDisplayed();
    }

    public boolean isEmailInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword)).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).isDisplayed() && wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword)).isEnabled();
    }
}
