package tests.logout;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUpLogout() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        homePage = new HomePage(driver);
    }

    @Test
    public void logoutSuccess() {
        if (homePage.isLogoutButtonActive()) {
            try {
                homePage.logout();
                Assert.assertTrue(homePage.isLogoutSuccess());
            } catch (Exception e) {
                log.error("Cannot log out because: ", e);
            }
        }
    }
}
