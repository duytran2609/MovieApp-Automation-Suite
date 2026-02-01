package tests.movie;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;

import java.lang.management.ManagementPermission;
import java.util.HashMap;
import java.util.Map;

public class GetMovieByCategory extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;
    private HeaderComponent headerComponent;

    @BeforeMethod
    public void setUpGetMovieList() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        moviePage = homePage.headerComponent.navigateToMoviePage();
    }

    @DataProvider(name = "dropdownData")
    public Object[][] categoryData() {
        return new Object[][] {
                {"Tất cả loại"},
                {"Phim lẻ"},
                {"Phim bộ"}
        };
    }

    @Test
    public void testDisplayMovieCategoryDropdown() {
        moviePage = new MoviePage(driver);
        Assert.assertTrue(moviePage.isMovieCategoryDropdownActive(), "Movie category dropdown is not active");
    }

    @Test(dataProvider = "dropdownData")
    public void testSelectedDropdownText(String category) {
        String optionSelected = moviePage.getCategoryDropdownOption(category);
        Assert.assertEquals(optionSelected, category, "Not true");
    }

}

