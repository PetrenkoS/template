import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import util.Browser;
import util.Environment;
import util.PropertyLoad;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait = null;
    public static PropertyLoad propertyLoader = PropertyLoad.getInstance();
    protected static Environment env = Environment.toEnum(propertyLoader.getProperty("env"));
    private static Browser browser = Browser.toEnum(propertyLoader.getProperty("browser"));
    protected static final String URL_DEV = propertyLoader.getProperty("url_dev");
    protected static final String URL_STAGING = propertyLoader.getProperty("url_stg");

    String email = propertyLoader.getProperty("user_email");

    String password = propertyLoader.getProperty("user_password");

    @BeforeSuite(alwaysRun = true)
    public void setUpDriver() {
        if (browser.toString().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void openUrl() {
        if(browser.toString().equals("firefox")){
            driver = new FirefoxDriver();
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            System.out.println("=== Using Chrome browser by default. ===");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("=== Test environment = " + env + " ===");

        if (env == Environment.DEVELOPMENT) {
            driver.get(URL_DEV);
        } else if (env == Environment.STAGING) {
            driver.get(URL_STAGING);
        } else {
            env = null;
            System.out.println("=== No environment set, tests will run on " + URL_DEV + " ===");
            driver.get(URL_DEV);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
     driver.quit();
    }

}
