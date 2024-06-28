import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class DirectToLandingPage {

    //1. Navigate to eBay. ("https://www.ebay.com/")
    @Test(priority = 1)
    @Parameters({"siteURL"})
    void directedURL(String siteURL) {
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        webDriver.get(siteURL);
        webDriver.manage().window().maximize();
    }

    //2. Verify the correct page has loaded by checking the URL or a unique page element.
    @Test(priority = 2)
    void checkPageTitle() {
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        String pageTitle,expectedPageTitle;
        pageTitle = webDriver.getTitle();
        expectedPageTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        Assert.assertEquals(pageTitle, expectedPageTitle, "Verification Failed :\n\tPage Titles did not matched as expected title");
    }
}
