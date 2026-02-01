package pages;

import base.BasePage;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MoviePage extends BasePage {

    public HeaderComponent headerComponent;
    public HomePage homePage;
    public MovieDetailsPage movieDetailsPage;

    private By txtTitleMoviePage = By.tagName("h1");
    private By iptMovie = By.xpath("//*[@id=\"root\"]/div/div/div[1]/input");

    private By crdMovies = By.className("movie-card");
    private By imgMoviePoster = By.cssSelector(".movie-poster");
    private By txtMovieTitle = By.cssSelector(".movie-title");
    private By txtMovieYear = By.cssSelector(".movie-meta");

    private By dropdownMovieCategory = By.cssSelector(".filter-select");
    private By optionDropdown = By.cssSelector(".filter-select");

    private By txtNoMovieFound = By.xpath("//*[@id=\"root\"]/div/div/div[2]/h2");

    public MoviePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public boolean isMoviePageTitleDisplay() {
        return isDisplayed(txtTitleMoviePage);
    }

    private List<WebElement> getMovies() {
        wait.until(ExpectedConditions.presenceOfElementLocated(crdMovies));
        return driver.findElements(crdMovies);
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

    // ===== MOVIE LIST =====
    public void searchMovie(String keyword) {
        type(iptMovie, keyword);
    }

    public List<String> getAllMovieTitles() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(crdMovies),
                ExpectedConditions.presenceOfElementLocated(txtNoMovieFound)
        ));
        return driver.findElements(crdMovies).stream()
                .map(movie -> movie.findElement(txtMovieTitle).getText().toLowerCase())
                .toList();
    }

    public String getNoMovieFoundMessage() {
        return isDisplayed(txtNoMovieFound) ? find(txtNoMovieFound).getText() : "";
    }

    public MovieDetailsPage navigateToMovieDetailsPage() {
        click(crdMovies);
        return new MovieDetailsPage(driver);
    }

    public boolean isNavigateToMovieDetailsPage() {
        String hrefPart = driver.findElement(crdMovies).getAttribute("href");
        return wait.until(ExpectedConditions.urlContains(hrefPart));
    }

    // ===== MOVIE CATEGORY DROPDOWN =====
    public boolean isMovieCategoryDropdownActive() {
        return isDisplayed(dropdownMovieCategory) && isEnabled(dropdownMovieCategory);
    }

    public List<String> getAllCategories() {
        Select dropdown = new Select(driver.findElement(dropdownMovieCategory));
        List<WebElement> options = dropdown.getOptions();
        return options.stream()
                    .map(e -> e.getText().trim())
                    .toList();
    }

    public String getCategoryDropdownOption(String category) {
        String optionSelected = "";
        click(dropdownMovieCategory);
        Select dropdown = new Select(driver.findElement(dropdownMovieCategory));
        List<WebElement> options = dropdown.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equals(category)) {
                option.click();
                optionSelected = option.getText();
            }
        }
        return optionSelected;
    }

//    public void selectCategory() {
//        Select dropdown = new Select(driver.findElement(drpMovieCategory));
//        List<WebElement> options = dropdown.getOptions();
//        for (WebElement option : options) {
//            String value = option.getText().trim();
//        }
//    }

}
