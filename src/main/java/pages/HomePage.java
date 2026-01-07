package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private MoviePage moviePage;

    By btnLogout = By.cssSelector(".logout-btn");
    By btnLogin = By.cssSelector("a[data-discover='true']");
    By crdMovie = By.cssSelector("a[href='/movies/tt0462538']");
    By innerMovie = By.linkText("Phim");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogout)).click();
    }

    public MoviePage navigateMoviePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(innerMovie)).click();
        return new MoviePage(driver);
    }

    public boolean isLogoutSuccess() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).isDisplayed();

    }

    public boolean isLogoutEnable() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogout)).isEnabled();
    }

}
