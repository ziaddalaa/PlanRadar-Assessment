package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormBuilderPage extends BasePage{

    private final By formNameContainer = By.cssSelector("[data-testid=\"form-text-input-root\"]");
    private final By formNameInput = By.cssSelector("[data-testid=\"Setup_Forms_FormName\"]");
    private final By setFormName = By.cssSelector("[data-testid=\"Setup_Forms_FormName_Save\"]");
    private final By formDescriptionInput = By.cssSelector("[data-testid=\"form-text-input-root\"]");
    private final By setFormDescription = By.cssSelector("[data-testid=\"Setup_Forms_FormDescription_Save\"]");
    private final By closeEditorButton = By.cssSelector("[data-testid=\"setup_forms_close_button\"]");
    private final By confirmCloseFormButton = By.cssSelector("[data-testid=\"Close form\"]");

    // Locators for Field Palette Items (Left Column)
    private By shortTextField = By.cssSelector("[data-testid=\"ticket_type_form_field_Short_Text_add\"]");
    private By listField = By.cssSelector("[data-testid=\"ticket_type_form_field_List_add\"]");
    private By dateField = By.cssSelector("[data-testid=\"ticket_type_form_field_Date_add\"]");

    // Locators for Bottom Actions
    private By saveButton = By.cssSelector("[data-testid=\"Save\"]");
    private By toastMessage = By.cssSelector("[data-testid='toast-message']");

    // Locators for Field Settings Panel (Right side)
    private final By shortTextFieldCard = By.cssSelector("[data-testid=\"form-field-tf5fa98cfee293850f\"]");
    private final By mandatoryToggle = By.cssSelector("[data-testid=\"Setup_Forms_Short Text_Mandatory\"]");
    private final By mandatoryFlag = By.cssSelector("[data-id*='Mandatory'] label > span");

    // Locators for Preview Form Modal
    private final By previewFormButton = By.cssSelector("[data-testid=\"form-details-preview-button\"]");
    private final By ticketPreviewTitle = By.cssSelector(".create-ticket-header > p");
    private final By closePreviewModalButton = By.cssSelector("[data-testid=\"tickets_details_closenewticket\"]");

    public FormBuilderPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Enters form name, commits changes via checkmark, and explicitly blurs focus out of the title container.
     */
    public FormBuilderPage enterFormName(String formName) {
        // Declare input at method scope so it's accessible across all try blocks
        WebElement input = fluentWait.until(ExpectedConditions.presenceOfElementLocated(formNameInput));

        // Step 1: Click input to activate edit mode
        Actions actions = new Actions(driver);
        actions.moveToElement(input).click().perform();

        // Step 2: Clear and type new form name
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        input.sendKeys(formName);

        // Step 3: Click the checkmark button to commit title
        try {
            click(setFormName);
        } catch (Exception e) {
            input.sendKeys(Keys.ENTER);
        }

        // Step 4: Force focus OUT of the container (Blur effect)
        try {
            input.sendKeys(Keys.TAB);
            clickIfDisplayed(formDescriptionInput);
        } catch (Exception ignored) {}

        return this;
    }

    // Enters the description of the form.
    public FormBuilderPage enterFormDescription(String formDescription) {
        type(formDescriptionInput, formDescription);
        click(setFormDescription);
        return this;
    }

    // Clicks the Short Text field in the Field Palette to add it to the form.
    public FormBuilderPage addShortTextField() {
        click(shortTextField);
        return this;
    }

    // Clicks the List field in the Field Palette to add it to the form.
    public FormBuilderPage addListField() {
        click(listField);
        return this;
    }

    // Clicks the Date field in the Field Palette to add it to the form.
    public FormBuilderPage addDateField() {
        click(dateField);
        return this;
    }

    // Clicks the Save button to save the form.
    public FormBuilderPage clickSaveButton() {
        click(saveButton);
        return this;
    }
    // Verifies that the success toast message is displayed on screen
    public boolean isSuccessToastDisplayed() {
        return isDisplayed(toastMessage);
    }

    // Captures the exact text content of the toast message
    public String getToastMessage() {
        return getText(toastMessage);
    }


    // Clicks the Close button to exit the Form Builder and return to the Forms list.
    public FormsPage clickClose() {
        click(closeEditorButton);
        clickIfDisplayed(confirmCloseFormButton); // Handle potential modal popup
        return new FormsPage(driver);
    }

    //Need Enhancements
//    // Clicks the Short Text field card to open its field settings
//    public FormBuilderPage selectShortTextField() {
//        click(shortTextFieldCard);
//        return this;
//    }

    // Enables the mandatory field toggle in the field settings panel.
    public FormBuilderPage enableMandatorySetting() {

        WebElement input = fluentWait.until(ExpectedConditions.presenceOfElementLocated(mandatoryToggle));
        WebElement flag = driver.findElement(mandatoryFlag);
        boolean isAlreadyEnabled = input.isSelected() || flag.getAttribute("class").contains("bg-actionable-primary-enabled-bg");

        if(!isAlreadyEnabled){
            try {
                input.click();
            }
            catch (Exception e){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
            }
        }
        return this;
    }

    // Clicks the 'Preview form' button at the top right of the builder
    public FormBuilderPage clickPreviewForm() {
        click(previewFormButton);
        return this;
    }

    // Verifies if the 'Ticket Preview' modal overlay is open
    public boolean isPreviewModalDisplayed() {
        return isDisplayed(ticketPreviewTitle);
    }

    /**
     * Verifies if a field paragraph tag contains '* FieldName' in the Preview modal.
     *
     * @param fieldName Label name of the field (e.g. "Short Text", "Title")
     * @return true if the mandatory paragraph element is displayed
     */
    public boolean isFieldMarkedMandatoryInPreview(String fieldName) {
        By mandatoryLabel = By.xpath(
                "//div[@role='dialog']//p[contains(text(), '* " + fieldName + "') or contains(text(), '*" + fieldName + "')]"
        );
        return isDisplayed(mandatoryLabel);
    }

    // Closes the Ticket Preview modal.
    public FormBuilderPage closePreviewModal() {
        click(closePreviewModalButton);
        return this;
    }
}
