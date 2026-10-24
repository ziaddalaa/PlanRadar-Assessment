import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.DashboardPage;
import pages.FormsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setUp() {
    String baseUrl = ConfigReader.get("baseUrl");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Navigate to the base URL
        driver.get("https://www.planradar.com/");
    }

    /**
     * Reusable Helper Flow: Handles Cookies, Login, and Navigation to Forms page.
     * @return FormsPage instance ready for test execution.
     */
    protected FormsPage loginAndNavigateToForms() {
        // Fetch test data dynamically
        String email = ConfigReader.get("userEmail");
        String password = ConfigReader.get("userPassword");

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        LoginPage loginPage = homePage.clickHeaderLoginButton();
        loginPage.enterEmail(email);
        loginPage.clickLoginButton();
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFormsAndLists();
        FormsPage formsPage = dashboardPage.clickForms();
        return formsPage;
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
