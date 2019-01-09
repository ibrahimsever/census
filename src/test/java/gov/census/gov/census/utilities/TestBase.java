package gov.census.gov.census.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUpClass(){
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setUpMethod(){
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions=new Actions(driver);
        softAssert=new SoftAssert();
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
        softAssert.assertAll();
    }

    public void homepage(){
        driver.get("https://www.census.gov/");
    }

    public void clickLibrary(){
        homepage();
        driver.findElement(By.xpath("//button[@class='prefix-overlay-close prefix-overlay-action-later']")).click();

        driver.findElement(By.xpath("//a[@id='data-uscb-header-nav-item-link-2']")).click();
    }
}
