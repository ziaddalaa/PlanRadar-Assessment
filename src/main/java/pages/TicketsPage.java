package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketsPage extends BasePage{

    // Tickets Page Locators
    private final By createTicketBtn = By.cssSelector("[data-testid=\"create-action-btn\"]");
    private final By ticketFormSelector = By.xpath("//div[@data-testid='ticket-form-selector']//div[contains(@class, '-control')]");
    private final By saveTicketBtn = By.cssSelector("[data-testid=\"tickets_details_savenewticket\"]");
    private final By shortTextInput = By.cssSelector("[data-testid=\"ticket_form_field_Short_Text\"]");

    // Select location Locators
    private final By locationSelector = By.cssSelector("[data-id=\"tickets_details_location_selector\"]");
    private final By frontViewLocation = By.cssSelector("[data-testid=\"plan-tree-selector-node-eqpwlnkl\"]");

    // Validation Locators
    private final By inlineErrorMsg = By.className("invalid-hint");
    private final By toastErrorMsg = By.cssSelector("[data-testid=\"toast-message\"]");




    public TicketsPage(WebDriver driver) {
        super(driver);
    }

    public TicketsPage clickCreateTicketBtn() {
        click(createTicketBtn);
        return this;
    }

    public TicketsPage selectTicket(String formName){
        // 1. Click the Form dropdown container
        click(ticketFormSelector);

        // 2. Click the matching form option from the dropdown list
        By ticketOption  = By.xpath("//div[text()='" + formName + "']");
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(ticketOption)).click();
        }
        catch (StaleElementReferenceException e){
            // Re-find fresh DOM element and retry click if React re-rendered the node
            driver.findElement(ticketOption).click();
        }
        catch (Exception e){
                // Fallback for JS click if DOM interception occurs
                WebElement element = driver.findElement(ticketOption);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        return this;
    }


    // Clicks Save with mandatory fields blank to trigger validation blocking.
    public TicketsPage clickSaveExpectingError() {
        click(saveTicketBtn);
        return this;
    }


    // Asserts the inline error message displayed directly below the mandatory field.
    public boolean isInlineErrorMessageDisplayed() {
        return isDisplayed(inlineErrorMsg);
    }

    //Asserts the pop-up banner displayed in the bottom-right corner.
    public boolean isToastErrorDisplayed() {
        return isDisplayed(toastErrorMsg);
    }


    //Fills in a text field by its label name (e.g., "Short Text").
    public TicketsPage fillShortTextField(String textValue) {
        type(shortTextInput,textValue);
        return this;
    }

    public TicketsPage fillLocationAndClickSaveSuccessfully() {
        click(locationSelector);
        click(frontViewLocation);
        click(saveTicketBtn);
        return this;
    }
}
