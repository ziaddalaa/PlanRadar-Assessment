import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.FormsPage;
import pages.HomePage;
import pages.LoginPage;

public class FormBuilderTest extends BaseTest{

    @Test
    public void testNavigateToFormPage(){

        // 1. Accept cookies & navigate to Login
        HomePage homePage = new HomePage(driver);
//        homePage.acceptCookies();
        LoginPage loginPage = homePage.clickHeaderLoginButton();

        // 2. Perform Login
        loginPage.enterEmail("ziaddalaa00@gmail.com");
        loginPage.clickLoginButton();
        loginPage.enterPassword("Asdfg2001");
        loginPage.clickLoginButton();

        // 3. Dashboard -> Navigate to Forms and Lists
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFormsAndLists();
        FormsPage formsPage = dashboardPage.clickForms();

        // 4. Verify page load
        Assert.assertTrue(formsPage.isFormPageLoaded(), "Forms management page failed to load!");
    }
}
