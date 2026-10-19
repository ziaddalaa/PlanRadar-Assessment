import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormBuilderPage;
import pages.FormsPage;
import pages.HomePage;
import utils.ConfigReader;

/**
 * Full End-to-End Test Suite:
 * 1. Home Page & Cookie Handling
 * 2. User Authentication (Login)
 * 3. Navigation to Forms Module
 * 4. Form Creation & Canvas Interactions
 * 5. Field Configuration (Mandatory Rule)
 * 6. Live Ticket Preview Assertion
 * 7. Form Persistence & Modal Handling
 */

public class E2ETest extends BaseTest{

    @Test
    public void testCompleteFormBuilderWorkFlow(){
        // Fetch test data dynamically
        String formName = ConfigReader.get("formName");
        String fieldName = ConfigReader.get("fieldName");

        // 1. Single-line login & navigate to Forms list
        FormsPage formsPage = loginAndNavigateToForms();

        // Step 2: Launch Form Builder
        formsPage.clickCreateForm();
        FormBuilderPage formBuilder = new FormBuilderPage(driver);

        // Step 3: Build Form & Enable Mandatory Setting
        formBuilder.enterFormName(formName)
                .addListField()
                .addDateField()
                .addShortTextField()
                .enableMandatorySetting()
                .clickSaveButton();

        // Step 4: Validate in Ticket Preview Modal
        formBuilder.clickPreviewForm();
        Assert.assertTrue(formBuilder.isPreviewModalDisplayed(), "Preview modal failed to display!");
        Assert.assertTrue(
                formBuilder.isFieldMarkedMandatoryInPreview(fieldName),
                "Mandatory indicator '* Short Text' was missing in Preview mode!"
        );
        formBuilder.closePreviewModal();

        // Step 5: Save Form & Assert Toast Message
        formBuilder.clickSaveButton();
        Assert.assertTrue(formBuilder.isSuccessToastDisplayed(), "Success toast notification did not appear!");

        // Step 6: Close Form Builder & Confirm Modal Exit
        formsPage = formBuilder.clickClose();
        Assert.assertTrue(formsPage.isFormPageLoaded(), "Failed to return to Forms list page!");

    }



}
