package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{

    private final By formsAndListsMenuItem = By.cssSelector("[data-testid=\"navigation_formsandlists\"]");
    private final By formsSubMenuItem = By.cssSelector("[data-testid=\"navigation_forms\"]");
    private final By timezoneModalCancelButton = By.cssSelector("[data-testid=\"Cancel\"]");
    private final By projectsSubMenuItem = By.cssSelector("[data-testid=\"navigation_projects\"]");
    private final By ticketsSubMenuItem = By.cssSelector("[data-testid=\"navigation_tickets\"]");

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

    public ProjectsPage clickProjects(){
        click(projectsSubMenuItem);
        return new ProjectsPage(driver);
    }

    public TicketsPage clickTickets(){
        click(ticketsSubMenuItem);
        return new TicketsPage(driver);
    }

}
