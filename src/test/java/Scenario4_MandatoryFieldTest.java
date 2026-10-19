import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormBuilderPage;
import pages.FormsPage;

public class Scenario4_MandatoryFieldTest extends BaseTest {
    @Test
    public void testMandatoryFieldInPreview(){
        // 1. Single-line login & navigate to Forms list
        FormsPage formsPage = loginAndNavigateToForms();

        // 2. Open Form Builder
        formsPage.clickCreateForm();
        FormBuilderPage formBuilderPage = new FormBuilderPage(driver);

        // 3. Configure form with a Mandatory Short Text field
        formBuilderPage.enterFormName("Mandatory Validation Form")
                .addShortTextField()
                .enableMandatorySetting()
                .clickSaveButton();

        // 4. Open Ticket Preview modal
        formBuilderPage.clickPreviewForm();

        // 5. Assertion: Verify 'Short Text' has mandatory '*' indicator in Preview mode
        Assert.assertTrue(formBuilderPage.isPreviewModalDisplayed());
        Assert.assertTrue(
                formBuilderPage.isFieldMarkedMandatoryInPreview("Short Text"),
                "Short Text field was not marked as mandatory with '*' in Preview mode!"
        );

        // 6. Close Preview modal & Save form and verify toast message
        formBuilderPage.closePreviewModal()
                .clickSaveButton();
        Assert.assertTrue(formBuilderPage.isSuccessToastDisplayed(), "Success toast missing after save!");

        // 7. Close editor and verify return to Forms list
        formsPage = formBuilderPage.clickClose();
        Assert.assertTrue(formsPage.isFormPageLoaded(), "Failed to return to Forms management page!");



    }
}
