package pages;

import base.BasePage;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoviePage extends BasePage {

    // ===== LOCATORS =====
    private By txtTitleMoviePage = By.tagName("h1");
    private By iptMovie = By.xpath("//*[@id=\"root\"]/div/div/div[1]/input");

    private By crdMovies = By.className("movie-card");
    private By imgMoviePoster = By.cssSelector(".movie-poster");
    private By txtMovieTitle = By.cssSelector(".movie-title");
    private By txtMovieYear = By.cssSelector(".movie-meta");

    private By dropdownMovieType = By.cssSelector(".filter-select");
    private By btnClearFilter = By.cssSelector(".filter-reset");
    private By txtNoMovieFound = By.xpath("//*[@id=\"root\"]/div/div/div[2]/h2");

    private By btnPreviousPage = By.cssSelector(".pagination-prev");
    private By btnPageNumber = By.cssSelector(".pagination-number ");
    private By btnPageSelected = By.cssSelector(".pagination-number.active");
    private By btnNextPage = By.cssSelector(".pagination-next");
    private By iptPage = By.cssSelector(".pagination-goto-input");
    private By btnGo = By.cssSelector(".pagination-goto-btn");

    // ===== COMPONENTS / PAGES =====
    public HeaderComponent headerComponent;
    public HomePage homePage;
    public MovieDetailsPage movieDetailsPage;

    // ===== ACTIONS =====
    public void searchMovie(String keyword) {
        type(iptMovie, keyword);
    }

    public MovieDetailsPage navigateToMovieDetailsPage() {
        click(crdMovies);
        return new MovieDetailsPage();
    }

    public void selectType(String type) {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        dropdown.selectByContainsVisibleText(type);
    }

    public String getTypeDropdownOption(String type) {
        String optionSelected = "";
        click(dropdownMovieType);
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        List<WebElement> options = dropdown.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equals(type)) {
                option.click();
                optionSelected = option.getText();
            }
        }
        return optionSelected;
    }


    public void clickClearFilterButton() {
        click(btnClearFilter);
    }

    public void navigateToAnotherPage(String page) {
        By pageBtn = By.xpath("//button[contains(@class,'pagination-number') and text()='" + page + "']");
        WebElement oldPage = wait.until(ExpectedConditions.elementToBeClickable(pageBtn));
        oldPage.click();
        wait.until(ExpectedConditions.stalenessOf(oldPage));
        wait.until(ExpectedConditions.attributeContains(pageBtn, "class", "active"));
    }

    public void navigateToLastPage() {
        By pages = By.cssSelector(".pagination-number ");
        List<WebElement> allPages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pages));
        WebElement oldActive = driver.findElement(btnPageSelected);
        allPages.get(allPages.size()-1).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
    }

    public void navigateToPreviousPage() {
        WebElement oldActive = driver.findElement(btnPageSelected);
        wait.until(ExpectedConditions.elementToBeClickable(btnPreviousPage)).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
    }

    public void navigateToNextPage() {
        WebElement oldActive = driver.findElement(btnPageSelected);
        wait.until(ExpectedConditions.elementToBeClickable(btnNextPage)).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
    }

    public void inputPageToNavigate(String page) {
        type(iptPage, page);
        click(btnGo);
    }

    // ===== GETTERS / STATES =====
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

    public boolean isMovieTypeDropdownActive() {
        return isDisplayed(dropdownMovieType) && isEnabled(dropdownMovieType);
    }

    public boolean isClearFilterButtonActive() {
        return isDisplayed(btnClearFilter) && isEnabled(btnClearFilter);
    }

    public boolean isPreviousPageButtonDisplay() {
        return isDisplayed(btnPreviousPage);
    }

    public boolean isPreviousPageButtonActive() {
        return isEnabled(btnPreviousPage);
    }

    public boolean isNextPageButtonDisplay() {
        return isDisplayed(btnNextPage);
    }

    public boolean isNextPagePageButtonActive() {
        return isEnabled(btnNextPage);
    }

    public List<WebElement> pageNumberList() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(btnPageNumber));
    }

    public String getPageSelected() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElement(btnPageSelected).getText());
    }

    public String getInputText() {
        return findVisible(iptMovie).getText();
    }

    public String getNoMovieFoundMessage() {
        return isDisplayed(txtNoMovieFound) ? findVisible(txtNoMovieFound).getText() : "";
    }

    public List<String> getAllMovieTitles() {
        waitForMoviesReload();
        if (driver.findElements(txtNoMovieFound).size() > 0) {
            return List.of();
        }
        return driver.findElements(crdMovies).stream()
                .map(m -> m.findElement(txtMovieTitle).getText().trim().toLowerCase())
                .toList();
    }

    public String getFirstType() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        return dropdown.getFirstSelectedOption().getText();
    }

    public List<String> getAllTypes() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        return dropdown.getOptions().stream()
                .map(e -> e.getText().trim())
                .toList();
    }

    public String getMovieInput() {
        return findVisible(iptMovie).getText();
    }

    public String getMovieTypeSelected() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        return dropdown.getFirstSelectedOption().getText();
    }

    public String getInputPageText() {
        return driver.findElement(iptPage).getAttribute("value");
    }

    // ===== HIGH-LEVEL / BUSINESS =====
    public void waitForMoviesReload() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(crdMovies),
                ExpectedConditions.presenceOfElementLocated(txtNoMovieFound)
        ));
    }

    public void waitForMovieReload() {
        wait.until(ExpectedConditions.stalenessOf(
                driver.findElement(crdMovies)
        ));
    }

    public boolean isNavigateToMovieDetailsPage() {
        String hrefPart = driver.findElement(crdMovies).getAttribute("href");
        return wait.until(ExpectedConditions.urlContains(hrefPart));
    }
}
