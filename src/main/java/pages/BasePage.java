package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

//DRY Don't Repeat Yourself
public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)) //Maximum wait time
                .pollingEvery(Duration.ofSeconds(1)) //Check every 1s
                .ignoring(NoSuchElementException.class); // Ignore temporary missing elements
    }

    // Waits for an element to be clickable and performs a click.
    protected void click(By locator) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Waits for an input field to be visible, clears existing text, and types new text.
    protected void type(By locator, String text) {
        WebElement element = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE); // To clear the state
        element.sendKeys(text);
    }

    // Waits for an element to be visible and returns its text.
    protected String getText(By locator) {
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Checks if an element is displayed on the page.
    protected boolean isDisplayed(By locator) {
        try {
            return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // For the displayed banners/ cookie banner
    protected void clickIfDisplayed(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.elementToBeClickable(locator))
                    .click();
        } catch (Exception e) {
            // Cookie banner/popup didn't show up within 1 second; safely proceed
        }
    }
}
