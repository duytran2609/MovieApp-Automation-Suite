package tests.movie;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;

public class MovieTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;

    @BeforeMethod
    public void setUpChooseMovie() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
    }

    @Test
    public void checkMoviePage() {
        moviePage = homePage.navigateMoviePage();
        Assert.assertTrue(moviePage.isMoviePageDisplay(), "Failed to naviagte to movie page");
    }
}
