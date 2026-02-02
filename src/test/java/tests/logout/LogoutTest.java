package tests.logout;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;

public class LogoutTest extends BaseTest {

    public HeaderComponent headerComponent;
    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;

    @BeforeMethod
    public void setUpLogoutTest() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        headerComponent = new HeaderComponent(driver);
    }

    @Test
    public void testLogoutFromHomePage() {
        if (homePage.headerComponent.isLogoutActive()) {
            try {
                homePage.headerComponent.logout();
                Assert.assertTrue(homePage.headerComponent.isLogoutSuccess());
            } catch (Exception e) {
                log.error("Cannot log out because: ", e);
            }
        }
    }

    @Test
    public void testLogoutFromMoviePage() {
        homePage.headerComponent.navigateToMoviePage();
        moviePage = new MoviePage(driver);
        if (moviePage.headerComponent.isLogoutActive()) {
            try {
                moviePage.headerComponent.logout();
                Assert.assertTrue(moviePage.headerComponent.isLogoutSuccess());
            } catch (Exception e) {
                log.error("Cannot log out because: ", e);
            }
        }
    }


}
