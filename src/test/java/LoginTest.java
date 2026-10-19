import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginFlow(){

        // Step 1: Initialize Home Page
        HomePage homePage = new HomePage(driver);

        // Step 2: Accept cookie banner if displayed
        homePage.acceptCookies();

        // Step 3: Click Login button on Home Page and transition to LoginPage
        LoginPage loginPage = homePage.clickHeaderLoginButton();

        // Step 4: Enter credentials on the Login page
        loginPage.enterEmail("ziaddalaa00@gmail.com");
        loginPage.clickLoginButton();
        loginPage.enterPassword("Asdfg2001");

    }
}
