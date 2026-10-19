package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{

    private final By formsAndListsMenuItem = By.cssSelector("[data-testid=\"navigation_formsandlists\"]");
    private final By formsSubMenuItem = By.cssSelector("[data-testid=\"navigation_forms\"]");
    private final By timezoneModalCancelButton = By.cssSelector("[data-testid=\"Cancel\"]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage dismissTimezoneModal() {
        click(timezoneModalCancelButton);
        return this;
    }

    public DashboardPage clickFormsAndLists(){
        dismissTimezoneModal();
        click(formsAndListsMenuItem);
        return this;
    }

    public FormsPage clickForms(){
        click(formsSubMenuItem);
        return new FormsPage(driver);
    }

}
