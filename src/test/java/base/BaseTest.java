package base;

import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.slf4j.Logger;
import org.testng.annotations.BeforeSuite;
import driver.DriverManager;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;


public class BaseTest {


    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        log.info("Start browser");
        DriverManager.initDriver(ConfigReader.get("browser"));
        DriverManager.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        log.info("Quit browser");
        DriverManager.quitDriver();
    }

    @BeforeSuite
    public void cleanAllureResults() throws IOException {
        Path allureDir = Paths.get("target/allure-results");
        if (Files.exists(allureDir)) {
            Files.walk(allureDir).sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }


}
