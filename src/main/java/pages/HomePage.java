package pages;

import base.BasePage;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    // ===== LOCATORS =====
    private By txtWelcomeHomePage = By.tagName("h1");
    private By txtExplore = By.linkText("Khám phá ngay");
    private By txtViewAll = By.linkText("Xem tất cả →");

    private By crdMovie = By.xpath("//*[@id=\"root\"]/div/section[3]/div/div[2]/a[1]");
    private By imgMoviePoster = By.cssSelector(".movie-poster");
    private By txtMovieTitle = By.cssSelector(".movie-title");
    private By txtMovieYear = By.cssSelector(".movie-meta");

    // ===== COMPONENTS / PAGES =====
    public HeaderComponent headerComponent;
    public MovieDetailsPage movieDetailsPage;
    public MoviePage moviePage;

    // ===== CONSTRUCTOR =====
    public HomePage(WebDriver driver) {
        headerComponent = new HeaderComponent();
        wait.until(ExpectedConditions.presenceOfElementLocated(crdMovie));
    }

    // ===== ACTIONS =====
    public MoviePage navigateToMoviePageByExploreText() {
        click(txtExplore);
        return new MoviePage();
    }

    public MoviePage navigateToMoviePageByViewAllText() {
        click(txtViewAll);
        return new MoviePage();
    }

    public MovieDetailsPage navigateToMovieDetailsPage() {
        click(crdMovie);
        return new MovieDetailsPage();
    }

    // ===== GETTERS / STATES =====
    public boolean isHomePageTitleDisplay() {
        return isDisplayed(txtWelcomeHomePage);
    }

    public boolean isExploreTextActive() {
        return isDisplayed(txtExplore) && isEnabled(txtExplore);
    }

    public boolean isViewAllActive() {
        return isDisplayed(txtViewAll) && isEnabled(txtViewAll);
    }

    public List<WebElement> getMovies() {
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

    // ===== HIGH-LEVEL / BUSINESS METHODS =====
    public boolean isNavigateToMoviePage() {
        moviePage = new MoviePage();
        boolean isMoviePageUrl = driver.getCurrentUrl()
                .toLowerCase()
                .endsWith("movies");

        return isMoviePageUrl && moviePage.isMoviePageTitleDisplay();
    }

    public boolean isNavigateToMovieDetailsPage() {
        String hrefPart = driver.findElement(crdMovie).getAttribute("href");
        return wait.until(ExpectedConditions.urlContains(hrefPart));
    }
}
