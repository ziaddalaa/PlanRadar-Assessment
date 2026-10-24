package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectsPage extends BasePage{

    // Projects List Locators
    private final By searchProjectsInput = By.cssSelector("[data-testid=\"search_projects\"]");

    // Form Tab & Assignment Locators
    private final By formsTab = By.cssSelector("[data-testid=\"Projects_Toolbar_FormsTab\"]");
    private final By assignFormButton = By.cssSelector("[data-testid=\"ticket-type-assign-form-button\"]");
    private final By searchFormInput = By.cssSelector("[data-testid=\"Forms_search\"]");
    private final By addFormButton = By.cssSelector("[data-testid=\"project-assign-form-plus-button\"]");
    private final By applyButton = By.cssSelector("[data-testid=\"ticket-type-configure-form-apply-button\"]");


    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Types the Project ID into the search input to filter the table,
     * then clicks the resulting row.
     */
    public ProjectsPage searchAndSelectProject(String projectId){
        WebElement searchBox = fluentWait.until(ExpectedConditions.elementToBeClickable(searchProjectsInput));
        searchBox.clear();
        searchBox.sendKeys(projectId);

        // Target row where the FIRST gridcell (ID column) strictly equals the projectId
        By targetRow = By.xpath(
                "//div[@role='row' and ./div[@role='gridcell'][1][.//span[normalize-space()='" + projectId + "']]]"
        );

        WebElement rowElement = fluentWait.until(ExpectedConditions.elementToBeClickable(targetRow));

        try {
            rowElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rowElement);
        }

        return this;
    }

    public ProjectsPage clickFormTab(){
        click(formsTab);
        return this;
    }

    public ProjectsPage clickAssignFormButton(){
        click(assignFormButton);
        return this;
    }

    public ProjectsPage assignFormByName(String formName){

        // 1. Wait for the popover search box to become visible
        try {
            WebElement formSearchBox = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(searchFormInput));
            formSearchBox.clear();
            formSearchBox.sendKeys(formName);
        }
        catch (TimeoutException e) {
            // Fallback if search input isn't present in the popover
        }

        // 2. Click the target form from the popover list
        WebElement addForm = fluentWait.until(ExpectedConditions.elementToBeClickable(addFormButton));

        try {
            addForm.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addForm);
        }

        return this;
    }

    // Clicks the "Apply" button at the bottom-right to save project form assignments.
    public ProjectsPage clickApplyButton() {
        WebElement applyBtn = fluentWait.until(ExpectedConditions.presenceOfElementLocated(applyButton));

        try {
            applyBtn.click();
        } catch (Exception e) {
            // Bypasses any remaining visual DOM overlays
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyBtn);
        }
        return this;
    }

}
