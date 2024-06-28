import java.time.Duration;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class ProductCheckoutPage extends WebDriverConfiguration {

    @Test(priority = 19)
    void checkPageTitle(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        String pageTitle = webDriver.getTitle();
        String loadPageTitle = "Checkout | eBay";
        Assert.assertEquals(pageTitle, loadPageTitle, "Validation Failed:\n\t Product titles did not matched");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    void quitWebBrowser(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        webDriver.quit();
    }
}
