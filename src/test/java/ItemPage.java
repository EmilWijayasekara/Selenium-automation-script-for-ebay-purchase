import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.regex.Matcher;
import java.time.Duration;

public class ItemPage {

    private static ThreadLocal<String> itemNameIP = new ThreadLocal<>();
    private static ThreadLocal<String> itemPriceIP = new ThreadLocal<>();
    private static ThreadLocal<String> quantityIP = new ThreadLocal<>();

    //getters and setters
    public static String getItemNameIP() {
        return itemNameIP.get();
    }
    public static void setItemNameIP(String s){
        itemNameIP.set(s);
    }
    public static String getItemPriceIP() {
        return itemPriceIP.get();
    }
    public static void setItemPriceIP(String string){
        itemPriceIP.set(string);
    }
    public static String getQuantityIP() {
        return quantityIP.get();
    }
    public static void setQuantityIP(String string){
        quantityIP.set(string);
    }

    @Test(priority = 6)
    public void viewItemDetails(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        ArrayList<String> itemList = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(itemList.get(1));
        String itemNameString = webDriver.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[1]/div[4]/div/div/div[2]/div/div[1]/div[1]/h1/span")).getText();
        System.out.println(itemNameString);
        setItemNameIP(itemNameString);
        Pattern pattern = Pattern.compile("[()US\\s]");
        Matcher matcher = pattern.matcher(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div[3]/div/div/div[2]/span[2]/span")).getText());
        setItemPriceIP(matcher.replaceAll(""));

        //print details
        System.out.println("Details of Product Page");
        System.out.println("=====================================================");
        System.out.println("Product Title\t:\t"+ getItemNameIP()+"\nProduct Price\t:\t"+getItemPriceIP()+"\n");
    }

    @Test(priority = 7)
    void assertProductTitle(){
        String productTitle = getItemNameIP();
        String expectedProductTitle = LandingPage.getNameOfItem();
        Assert.assertEquals(productTitle, expectedProductTitle, "Validation Failed:\n\t Product names did not matched");
    }

    @Test(priority = 8)
    void assertProductPrice(){
        String actualProductPrice = getItemPriceIP();
        String expectedProductPrice = LandingPage.getPriceOfItem();
        Assert.assertEquals(actualProductPrice, expectedProductPrice, "Validation Failed:\n\t Product prices did not matched");
    }

    @Test(priority = 9)
    @Parameters({"productColor"})
    void chooseProductColor(String productColor){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        WebElement colorDropdownElement = webDriver.findElement(By.id("x-msku__select-box-1000"));
        Select colorSelect = new Select(colorDropdownElement);
        colorSelect.selectByVisibleText(productColor);
    }

    @Test(priority = 10)
    @Parameters({"productQuantity"})
    void productQuantity(String productQuantity){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        WebDriverWait interval = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        WebElement productQuantityTextboxElement = interval.until(ExpectedConditions.elementToBeClickable(By.id("qtyTextBox")));
        productQuantityTextboxElement.clear();
        productQuantityTextboxElement.sendKeys(productQuantity);
        setQuantityIP(productQuantity);
    }

    @Test(priority = 11)
    void addProductToCart(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        WebElement addToCartBTN = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[8]/ul/li[2]/div/a/span/span"));
        addToCartBTN.click();
    }

    @Test(priority = 12)
    void goToCart(){
        WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        WebElement cartBTN = webDriver.findElement(By.xpath("//*[@id=\"gh-cart-n\"]"));
        cartBTN.click();
    }
}
