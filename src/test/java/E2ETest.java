import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
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
 * 8. Navigate to Tickets & Validate Mandatory Field Rules
 */

public class E2ETest extends BaseTest{

    String formName = ConfigReader.get("formName") + "_" + System.currentTimeMillis();
    String projectId = ConfigReader.get("projectId");

    @Test(description = "1. Create Form with Mandatory & Default List Settings")
    public void test01_createFormWithMandatoryField() {
        // Fetch test data dynamically

        String fieldName = ConfigReader.get("fieldName");

        // 1. Single-line login & navigate to Forms list
        FormsPage formsPage = loginAndNavigateToForms();

        // Step 2: Launch Form Builder
        FormBuilderPage formBuilder = formsPage.clickCreateForm();
        ;

        // Step 3: Build Form & Enable Mandatory Setting
        formBuilder.enterFormName(formName)
                .addListField() // Set "Trade" as the Default List for the List field
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
        @Test(dependsOnMethods = "test01_createFormWithMandatoryField", description = "2. Link Created Form to Target Project")
        public void test02_linkFormToProject() {
            // Step 7: Assign Form to Project & Click Apply
            DashboardPage dashboardPage = new DashboardPage(driver);
            ProjectsPage projectsPage = dashboardPage.clickProjects();

            projectsPage.searchAndSelectProject(projectId)
                    .clickFormTab()
                    .clickAssignFormButton()
                    .assignFormByName(formName)
                    .clickApplyButton();
        }

        @Test(dependsOnMethods = "test02_linkFormToProject", description = "3. Validate Ticket Mandatory Field Enforcement")
        public void test03_validateTicketMandatoryEnforcement() {

        // Step 8: Navigate to Tickets & Validate Mandatory Field Rules
        DashboardPage dashboardPage = new DashboardPage(driver);
        TicketsPage ticketsPage = dashboardPage.clickTickets();

        // Attempt to submit while mandatory field is empty
        ticketsPage.clickCreateTicketBtn().selectTicket(formName).clickSaveExpectingError();


        // Dual Assertions: Inline Error + Toast Banner
        Assert.assertTrue(
                ticketsPage.isInlineErrorMessageDisplayed(),
                "Inline validation error 'You have not filled in all the required fields.' did not appear below the field!"
        );

        Assert.assertTrue(
                ticketsPage.isToastErrorDisplayed(),
                "Error alert 'You have not filled in all the required fields.' did not appear at bottom-right!"
        );

        // Fill mandatory field and complete ticket creation
        ticketsPage.fillShortTextField("Short Text")
                .fillLocationAndClickSaveSuccessfully();


    }



}
