package accumex.ui.pageobjects;

import framework.init.AbstractPage;
import framework.init.WebDriverInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomepagePo extends AbstractPage {

    @FindBy(xpath = "//span[text()='Member Registration']")
    public WebElement memberRegistrationTab;

    public HomepagePo(WebDriver driver) {
        super(driver);
    }

    public void clickMemberRegistration(){
        clickOn(driver , memberRegistrationTab);
    }
}
