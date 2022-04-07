package tests;

import com.elip.pageobjects.WebCalc;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;


public class WebCalcTest {
    WebDriver driver;
    WebCalc webCalc;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"+","2,3","5"},{"-","10,2","8"},{"sin","30","0.5"}
        };
    }


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://web2.0calc.com/");
        driver.manage().window().maximize();

        webCalc = PageFactory.initElements(driver, WebCalc.class);
    }





    @Test(dataProvider = "test-data")
    public void CalculateFormulaTest(String operator,String operands,String expected) throws InterruptedException {


        String res=webCalc.calculate(operator,operands);
        Assert.assertEquals(res,expected);
        webCalc.ClearPanel();

    }


    @Test(dependsOnMethods = { "CalculateFormulaTest" })
    public void validateCurrentHistoryTest()  {
        List<WebElement> historyItems;
        webCalc.openHistoryFrame();
        historyItems=webCalc.getHistoryItems();
        Assert.assertEquals(historyItems.get(0).getText(),"sin(30");
        Assert.assertEquals(historyItems.get(1).getText(),"10-2");
        Assert.assertEquals(historyItems.get(2).getText(),"2+3");

        webCalc.closeHistoryFrame();


    }


    @AfterMethod
    public void CleanUp2() {
        webCalc.ClearPanel();


    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
