package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By emailInput = By.cssSelector("input[data-testid=\"Login_Input_Email\"]");
    private By loginButton = By.cssSelector("span[data-testid=\"Login\"]");
    private By passwordInput = By.cssSelector("input[data-testid=\"Login_Input_Password\"]");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public LoginPage clickLoginButton() {
        click(loginButton);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

}
