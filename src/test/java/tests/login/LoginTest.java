package tests.login;

import base.BaseTest;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import driver.DriverManager;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpLoginTest() {
        log.info("=== Setup LoginTest ===");
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        log.info("Init LoginPage");
        loginPage = new LoginPage();
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"abc", "123", "Invalid email or password"},
                {"", "xanhlacay1", "Email can't be null"},
                {"trandangduy13@gmail.com", "", "Password can't be null"},
        };
    }

    // ===== UI TEST =====
    @Test(groups = {"UI"})
    public void testPageUI() {
        log.info("Start TC_Login_001 - Verify Login Page UI");
        SoftAssert softAssert = new SoftAssert();
        log.info("Verify login form displayed");
        softAssert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form doesn't appear");
        log.info("Verify email input displayed");
        softAssert.assertTrue(loginPage.isEmailInputDisplayed(), "Email input doesn't appear");
        log.info("Verify password input displayed");
        softAssert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input doesn't appear");
        log.info("Verify login button displayed");
        softAssert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button doesn't work");
        softAssert.assertAll();
        log.info("End TC_Login_001 - PASSED");
    }

    // ===== VALID LOGIN =====
    @Test(priority = 1, groups = {"function"})
    @Description("Test login với tài khoản hợp lệ")
    public void shouldLoginSuccessfullyWhenCredentialsAreValid() {
        log.info("Start TC_Login_002 - Login Successfully With Valid Credentials");
        log.info("Login with valid account");
        loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        log.info("Verify login success");
        Assert.assertTrue(loginPage.isLoginSuccess(), "Đăng nhập thất bại");
        log.info("End TC_Login_002 - PASSED");
    }

    // ===== INVALID LOGIN =====
    @Test(priority = 2, groups = {"function"}, dataProvider = "invalidLoginData")
    @Description("Test login với tài khoản không hợp lệ")
    public void shouldFailToLoginWhenCredentialsAreInvalid(String email, String password, String errorMessage) {
        log.info("Start TC_Login_003 - Login Fail With Invalid Credentials | email=[{}], password=[{}]", email, password);
        log.info("Perform login");
        loginPage.login(email, password);
        log.info("Verify login failed");
        Assert.assertFalse(loginPage.isLoginFailed(), errorMessage);
        log.info("End TC_Login_003 - COMPLETED");
    }
}
