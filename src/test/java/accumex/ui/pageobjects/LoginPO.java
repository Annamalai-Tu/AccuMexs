package accumex.ui.pageobjects;

import framework.init.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public class LoginPO extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'username']")
    public WebElement txtUsername;

    @FindBy(xpath = "//input[@name = 'password']")
    public WebElement txtPassword;

    @FindBy(xpath = "//span[contains(text() , 'Sign in')]")
    public WebElement btnLogin;

    @FindBy(xpath = "//span[contains(text() , 'Proceed to Sign')]")
    public WebElement proceedSignIn;

    @FindBy(xpath = "//div[normalize-space(text()) = 'This user is already logged In']")
    public WebElement warning;

    @FindBy(xpath = "//span[text()='Member Registration']")
    public WebElement memberRegistrationTab;

    public LoginPO(WebDriver driver) {
        super(driver);
    }

    public void loginAs(String username, String password) {
        debugLog("Executing Login...");
        testStepsLog("Enter Login Credentials");

        testInfoLog("Enter Email", username);
        type(driver, txtUsername, username, true);

        pause(1);

        testInfoLog("Enter Password", password);
        type(driver, txtPassword, password, true);

        testStepsLog("Click on Login Button");
        clickOn(driver, btnLogin);

        try {
            testStepsLog("Checking for login warning...");

            if (isElementDisplayed(proceedSignIn)) {
                testStepsLog("Warning appeared: User already logged in.");
                pause(2);

                // Click 'Proceed Sign In' button using JavaScript
                clickOn(driver, proceedSignIn);
                debugLog("Clicked 'Proceed Sign In' button.");

                // Wait until the next expected page or element appears instead of pausing for 10 seconds
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.invisibilityOf(warning)); // Example condition
            }

        } catch (Exception e) {
            testStepsLog("No login warning appeared.");
        }


    }
}
