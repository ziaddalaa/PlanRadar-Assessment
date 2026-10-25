package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsPage extends BasePage {

    private By createFormButton = By.cssSelector("span[data-testid=\"Create a new form\"]");

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    // Verifies that the Forms management page is successfully loaded after closing the Form Builder.
    public boolean isFormPageLoaded() {
        return isDisplayed(createFormButton);
    }

    // Clicks the Create Form button to open the Form Builder interface
    public FormBuilderPage clickCreateForm() {
        click(createFormButton);
        return new FormBuilderPage(driver);
    }

}
