package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest { // Luôn kế thừa class BaseTest

    private LoginPage loginPage; // Nhớ tạo object page

    // Mở trang
    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
    }

    // Khởi tạo bộ data
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"abc", "123", "Invalid email or password"},
                {"", "xanhlacay1", "Email can't be null"},
                {"trandangduy13@gmail.com", "", "Password can't be null"},
        };
    }

    // Khởi tạo các test cases
    // Test flow logic
    @Test(priority = 1, groups = {"function"})
    public void loginWithValidAccount() {
        loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        Assert.assertTrue(loginPage.isLoginSuccess(), "Đăng nhập thất bại");
    }

    @Test(priority = 2, groups = {"function"}, dataProvider = "invalidLoginData")
    public void loginWithInvalidAccount(String email, String password, String errorMessage) {
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.isLoginFailed(), errorMessage);
    }

    @Test(priority = 3, groups = {"UI"})
    public void verifyPageUI() {
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form doesn't appear");
        Assert.assertTrue(loginPage.isEmailInputDisplayed(), "Email input doesn't appear");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input doesn't appear");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button doesn't work");
    }
}
