import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;

public class LandingPage extends WebDriverConfiguration {

    private static ThreadLocal<String> nameOfItem = new ThreadLocal<>();
    private static ThreadLocal<String> priceOfItem = new ThreadLocal<>();

    public static String getNameOfItem() {
        return nameOfItem.get();
    }

    public static void setNameOfItem(String string) {
        nameOfItem.set(string);
    }

    public static String getPriceOfItem() {
        return priceOfItem.get();
    }

    public static void setPriceOfItem(String string) {
        priceOfItem.set(string);
    }

    //Select the category “Cell Phones & Accessories” from the category drop-down menu
    @Test(priority = 3)
    @Parameters({"productName", "productCategory"})
    void chooseCategory(String productName, String productCategory) {
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        webDriver.findElement(By.id("gh-ac")).sendKeys(productName);
        WebElement categoryDropdownList = webDriver.findElement(By.xpath("//*[@id=\"gh-cat\"]"));
        Select chooseCategory = new Select(categoryDropdownList);
        chooseCategory.selectByVisibleText(productCategory);
        webDriver.findElement(By.id("gh-btn")).click();
    }

    //Verify the correct category selected by asserting the “Cell Phones & Accessories” words.
    @Test(priority = 4)
    public void assertCategory(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        WebElement categoryDropdownMenu = webDriver.findElement(By.id("gh-cat"));
        Select select = new Select(categoryDropdownMenu);
        String selectedCategory = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedCategory, "Cell Phones & Accessories", "Validation Failed:\n\t Product category did not matched");
    }

    //Enter your search keyword as “Mobile Phone”, then click the search button.
    //Choose the First results
    @Test(priority = 5)
    @Parameters({"getNumberOfResults"})
    void assertionResults(int getNumberOfResults) {
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        int count = 0;
        boolean isFirstItemSelected = false;

        System.out.println("Product List\n=====================================================");
        for (int i = 0; i < getNumberOfResults; i++) {
            String iteratedProductNumberi = webDriver.findElement(By.xpath(String.format("//div[@id='srp-river-results']/ul/li[%d]", (i + 1)))).getText();

            if (iteratedProductNumberi.contains("Mobile Phone")) {
                count++;
                String[] lines = iteratedProductNumberi.split("\\r?\\n");
                String productName = lines[0], productPrice = lines[3];
                System.out.println("Product Number\t:\t"+(i+1));
                System.out.println("Product Name\t:\t"+productName+"\nProduct Price\t:\t"+productPrice);

                if (!isFirstItemSelected) {
                    String itemID = webDriver.findElement(By.xpath(String.format("//div[@id='srp-river-results']/ul/li[%d]", (i + 1)))).getAttribute("id");
                    String xpathExpression_2 = String.format("//*[@id=\"%s\"]/div/div[1]/div/a/div/img", itemID);
                    WebElement first_filtered_item_image = webDriver.findElement(By.xpath(xpathExpression_2));
                    first_filtered_item_image.click();
                    setNameOfItem(productName);
                    setPriceOfItem(productPrice);
                    isFirstItemSelected = true;
                }
            }
        }
        Assert.assertTrue(count > 0, "Some search results does not contain 'Mobile Phone' keyword");
    }
}
