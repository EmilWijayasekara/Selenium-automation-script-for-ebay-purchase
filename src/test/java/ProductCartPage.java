import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductCartPage {

    private static ThreadLocal<String> productNameCP = new ThreadLocal<>();
    private static ThreadLocal<String> productPriceCP = new ThreadLocal<>();
    private static ThreadLocal<String> productQuantityCP = new ThreadLocal<>();
    public WebDriver webDriver = WebDriverConfiguration.getWebDriverThreadLocal();

    public static String getProductNameCP() {
        return productNameCP.get();
    }
    public static void setProductNameCP(String string){
        productNameCP.set(string);
    }
    public static String getProductPriceCP() {
        return productPriceCP.get();
    }
    public static void setProductPriceCP(String string){
        productPriceCP.set(string);
    }
    public static String getProductQuantityCP() {
        return productQuantityCP.get();
    }
    public static void setProductQuantityCP(String string){
        productQuantityCP.set(string);
    }

    @Test(priority = 13)
    void getItemDetailsCart() {
        webDriver = WebDriverConfiguration.getWebDriverThreadLocal();
        setProductNameCP(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[2]/div[1]/h3/a")).getText());
        String priceElement = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/div/span/span/span")).getText();
        Pattern pattern = Pattern.compile("[()US\\s]");
        Matcher matcher = pattern.matcher(priceElement);
        setProductPriceCP(matcher.replaceAll(""));
        WebElement productQuantityElement = webDriver.findElement(By.className("quantity"));
        WebElement selectedProductQuantityElement = productQuantityElement.findElement(By.tagName("select"));
        Select dropdownMenu = new Select(selectedProductQuantityElement);
        String selectedProductQuantity = dropdownMenu.getFirstSelectedOption().getText();
        setProductQuantityCP(selectedProductQuantity);
    }

    @Test(priority = 14)
    void assertProductTitle(){
        String productTitle = getProductNameCP();
        String expectedProductTitle = ItemPage.getItemNameIP();
        Assert.assertEquals(productTitle,expectedProductTitle,"Validation Failed:\n\t Product title did not matched");
    }

    @Test(priority = 15)
    void assertProductPrice(){
        String productPrice = getProductPriceCP();
        String expectedProductPrice = ItemPage.getItemPriceIP();
        Assert.assertEquals(productPrice,expectedProductPrice,"Validation Failed:\n\t Product price did not matched");
    }

    @Test(priority = 16)
    void assertProductQuantity(){
        Assert.assertEquals(getProductQuantityCP(), ItemPage.getQuantityIP(), "Validation Failed:\n\t Product quantity did not matched");

        System.out.println("\n\t\t\tShopping Cart\n=====================================================");
        System.out.println("Product Name\t:\t"+getProductNameCP()+"\nProduct Price\t:\t"+getProductPriceCP()+"\nQuantity\t:\t"+getProductQuantityCP());
        System.out.println("=====================================================");
    }

    @Test(priority = 17)
    void navigateChecout(){
        WebElement checkoutButton = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div/div[2]/button"));
        checkoutButton.click();
    }

    @Test(priority = 18)
    void guestAccess(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,Duration.ofSeconds(15));
        WebElement guestBTN = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gxo-btn")));
        guestBTN.click();
    }
}
