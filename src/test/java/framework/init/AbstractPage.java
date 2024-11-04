package framework.init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */


public abstract class AbstractPage extends WebDriverInit {

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, EXPLICIT_WAIT);
        PageFactory.initElements(finder, this);
    }

}
