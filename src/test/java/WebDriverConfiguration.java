import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class WebDriverConfiguration {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();

    public static ChromeOptions chromeOptions;
    public static EdgeOptions edgeOptions;
    public static WebDriver getWebDriverThreadLocal() {
        return webDriverThreadLocal.get();
    }

    public static void setWebDriverThreadLocal(WebDriver webDriver) {
        webDriverThreadLocal.set(webDriver);
    }

    @BeforeTest
    @Parameters({"webBrowser"})
    public static void choose_browser(String webBrowser){
        if(webBrowser.equalsIgnoreCase("Chrome")){
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver","D:\\OneDrive - sci.sjp.ac.lk\\3rd Year (Semester 2)\\CSC 365 2.0 Software Quality Assurance\\AS2020305_E.S.WIJAYASEKARA\\src\\main\\resources\\chromedriver.exe");
            setWebDriverThreadLocal(new ChromeDriver(chromeOptions));
        }

        if(webBrowser.equalsIgnoreCase("Edge")){
            edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.Edge.driver", "D:\\OneDrive - sci.sjp.ac.lk\\3rd Year (Semester 2)\\CSC 365 2.0 Software Quality Assurance\\AS2020305_E.S.WIJAYASEKARA\\src\\main\\resources\\msedgedriver.exe");
            setWebDriverThreadLocal(new EdgeDriver(edgeOptions));
        }

    }
}
