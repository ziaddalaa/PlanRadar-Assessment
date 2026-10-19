import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class Scenario1_CreateFormTest extends BaseTest{

    @Test
    public void testCreateFormWithMultipleFieldTypes() {
        // 1. Login & Navigate to Forms
        // 1. One-line precondition execution
        FormsPage formsPage = loginAndNavigateToForms();

        // 2. Open Form Builder
        formsPage.clickCreateForm();
        FormBuilderPage formBuilderPage = new FormBuilderPage(driver);

        // 3. Build Form with diverse field types
        formBuilderPage.enterFormName("Quality Inspection Form")
                .addShortTextField()
                .addListField()
                .addDateField();

        // 4. Save Form
        formBuilderPage.clickSaveButton();

        // Assertion: Verify toast notification text
        Assert.assertTrue(formBuilderPage.isSuccessToastDisplayed(), "Success toast was not displayed!");
        Assert.assertEquals(formBuilderPage.getToastMessage(), "Data successfully created", "Toast text mismatch!");

        // 6. Close editor and return to Forms list
        FormsPage savedFormsPage = formBuilderPage.clickClose();

        // 7. Assertion 2: Verify redirected back to Forms List
        Assert.assertTrue(savedFormsPage.isFormPageLoaded(), "Did not successfully return to Forms list page!");
    }
}
