package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    private MovieDetailsPage movieDetailsPage;

    // Header
    By txtLogo = By.tagName("h1");

    By btnLogout = By.cssSelector(".logout-btn");
    By btnLogin = By.cssSelector("a[data-discover='true']");


    By crdMovie = By.xpath("//*[@id=\"root\"]/div/section[3]/div/div[2]/a[1]");
    By imgMoviePoster = By.cssSelector(".movie-poster");
    By txtMovieTitle = By.cssSelector(".movie-title");
    By txtMovieYear = By.cssSelector(".movie-meta");


    public HomePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(crdMovie));
    }

    public void logout() {
        try {
            click(btnLogout);
        } catch (Exception e) {
            log.error("Logout fail because: ", e);
        }
    }

    public boolean isLogoutButtonActive() {
        return isDisplayed(btnLogout) && isEnabled(btnLogout);
    }

    public boolean isLogoutSuccess() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(btnLogout))
                && isDisplayed(btnLogin);
    }

    private List<WebElement> getMovies() {
        wait.until(ExpectedConditions.presenceOfElementLocated(crdMovie));
        return driver.findElements(crdMovie);
    }

    public int getNumberOfMovies() {
        return getMovies().size();
    }

    private WebElement getFirstMovie() {
        return getMovies().get(0);
    }

    public boolean isMovieCardDisplay() {
        return getFirstMovie().isDisplayed();
    }

    public boolean isMoviePosterDisplay() {
        return getFirstMovie().findElement(imgMoviePoster).isDisplayed();
    }

    public boolean isMovieTitleDisplay() {
        return getFirstMovie().findElement(txtMovieTitle).isDisplayed();
    }

    public boolean isMovieYearDisplay() {
        return getFirstMovie().findElement(txtMovieYear).isDisplayed();
    }

    public MovieDetailsPage navigateToMovieDetailsPage() {
        click(crdMovie);
        return new MovieDetailsPage();
    }

    public boolean isNavigateToMovieDetailsPage() {
        String hrefPart = driver.findElement(crdMovie).getAttribute("href");
        return wait.until(ExpectedConditions.urlContains(hrefPart));
    }

}
