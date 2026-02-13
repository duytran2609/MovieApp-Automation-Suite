package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailsPage;
import pages.MoviePage;

public class HeaderComponent extends BasePage {

    // ===== LOCATORS =====
    private By txtLogo = By.tagName("h1");
    private By txtHome = By.linkText("Home");
    private By txtMovie = By.linkText("Phim");
    private By txtUsername = By.cssSelector(".username");
    private By btnLogout = By.cssSelector(".logout-btn");
    private By btnLogin = By.cssSelector("a[data-discover='true']");

    // ===== PAGE OBJECTS =====
    private HomePage homePage;
    private MoviePage moviePage;
    private LoginPage loginPage;
    private MovieDetailsPage movieDetailsPage;

    // ===== ACTIONS =====
    public HomePage navigateToHomePage() {
        click(txtHome);
        return new HomePage(driver);
    }

    public MoviePage navigateToMoviePage() {
        click(txtMovie);
        return new MoviePage();
    }

    public void logout() {
        try {
            click(btnLogout);
        } catch (Exception e) {
            log.error("Logout fail because: ", e);
        }
    }

    // ===== GETTERS / STATES =====
    public boolean isLogoDisplay() {
        return isDisplayed(txtLogo);
    }

    public boolean isHomeActive() {
        return isDisplayed(txtHome) && isEnabled(txtHome);
    }

    public boolean isMovieActive() {
        return isDisplayed(txtMovie) && isEnabled(txtMovie);
    }

    public boolean isWelcomeUsernameTextDisplay() {
        return isDisplayed(txtUsername);
    }

    public String getWelcomeUsernameText() {
        return getText(txtUsername);
    }

    public boolean isLogoutActive() {
        return isDisplayed(btnLogout) && isEnabled(btnLogout);
    }

    // ===== HIGH-LEVEL / BUSINESS METHODS =====
    public boolean isNavigateToHomePageSuccess() {
        homePage = new HomePage(driver);
        boolean isHomePageUrl = driver.getCurrentUrl()
                .toLowerCase()
                .endsWith("vercel.app");

        return homePage.isHomePageTitleDisplay() && isHomePageUrl;
    }

    public boolean isNavigateToMoviePageSuccess() {
        moviePage = new MoviePage();
        boolean isMoviePageUrl = driver.getCurrentUrl()
                .toLowerCase()
                .endsWith("movies");

        return isMoviePageUrl && moviePage.isMoviePageTitleDisplay();
    }

    public boolean isLogoutSuccess() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(btnLogout))
                && wait.until(ExpectedConditions.invisibilityOfElementLocated(txtUsername))
                && isDisplayed(btnLogin);
    }
}
