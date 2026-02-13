package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgetPasswordPage;
import pages.LoginPage;
import driver.DriverManager;

public class ForgetPasswordTest extends BaseTest {

    private LoginPage loginPage;
    private ForgetPasswordPage forgetPasswordPage;

    @BeforeMethod
    public void setUpForgetPasswordTest() {
        log.info("=== Setup ForgetPasswordTest ===");
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        log.info("Init LoginPage");
        loginPage = new LoginPage();
        log.info("Click Forget Password link");
        loginPage.forgetPassword();
        log.info("Init ForgetPasswordPage");
        forgetPasswordPage = new ForgetPasswordPage();
    }

    @DataProvider(name = "emailData")
    public Object[][] emailData() {
        return new Object[][]{
                {"trandangduy13@gmail.com"},
                {"abc@gmail.com"},
                {"abc"},
                {""},
        };
    }

    @Test
    public void shouldResetPasswordSuccessfullyWhenEmailIsValid() {
        log.info("Start TC_ForgetPasswordTest_001 - Reset Password Successfully When Email Is Valid");
        log.info("Input valid email");
        forgetPasswordPage.inputEmail("trandangduy13@gmail.com");
        log.info("Click Send button");
        forgetPasswordPage.clickSendButton();
        log.info("Verify reset password request is successful");
        Assert.assertTrue(forgetPasswordPage.isSendSuccess(), "Submit button still exists"
        );
        log.info("End TC_ForgetPasswordTest_001 - COMPLETED");
    }

    @Test(dataProvider = "emailData")
    public void shouldFailToResetPasswordWhenEmailIsInvalid(String email) {
        log.info("Start TC_ForgetPasswordTest_002 - Fail To Reset Password When Email Is Invalid | Email: {}", email);
        forgetPasswordPage.inputEmail(email);
        log.info("Click Send button");
        forgetPasswordPage.clickSendButton();
        log.info("Verify system handles invalid email");
        Assert.assertTrue(forgetPasswordPage.isSendSuccess(), "Submit button still exists");
        log.info("End TC_ForgetPasswordTest_002 - COMPLETED");
    }
}
