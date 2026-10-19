package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    private final By loginButton = By.className("login-btn");
    private final By acceptCookiesButton = By.cssSelector("button[data-testid=\"actionButton-accept\"]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage acceptCookies() {
        clickIfDisplayed(acceptCookiesButton);
        return this;
    }

    // Clicks the Login button in the top navigation header to redirect to the login page.
    public LoginPage clickHeaderLoginButton() {
        click(loginButton);
        return new LoginPage(driver);
}


}
