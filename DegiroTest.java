import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DegiroTest {

    public WebDriver driver;

    @Test
    public void DegiroClick() {
        //driver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\A515-56-518y\\Desktop\\driver\\chromedriver.exe");

        //Chrome Driver inicializing
        driver = new ChromeDriver();

        //Open url
        driver.get("https://www.degiro.co.uk/fees/calculator");
        driver.manage().window().maximize();

        //Delete all cookies
        //driver.manage().deleteAllCookies();

        //initialise driver explicit driver wait
        WebDriverWait driverWait = new WebDriverWait(driver, 150);

        //Find accept button
        By selector = By.id("CybotCookiebotDialogBodyButtonAccept");

        // dialog
        By selector1 = By.id("CybotCookiebotDialog");

        //Wait dialog to load
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector1));
        //Wait  accept button  to be clickable
        driverWait.until(ExpectedConditions.elementToBeClickable(selector));
        //click on field
        driver.findElement(selector).click();

        //Click product type
        WebElement productType = driver.findElement(By.id("productType"));

        productType.click();

        //Choose  ETFs
        WebElement productTypeEtfs = driver.findElement(By.xpath("//*[@id='productType']/option[2]"));

        productTypeEtfs.click();

        //Click amount and add value
        //There is explicit and implicit waits  - because is not working every time
        String addValue = "50";

        By amount = By.id("amountPerTransactions");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(amount));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.elementToBeClickable(amount)).click();
        driver.findElement(amount).sendKeys(addValue);

        //Click Transactions and add value
        String trans = "70";

        By transactions = By.id("transactionsAYear");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(transactions)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(transactions).sendKeys(trans);

        //Click add field
        By addButton = By.id("submitdata");

        driverWait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        //Add average value
        String averageValue = "17";

        By average = By.id("averageAmountInvestedDuringYear");

        driverWait.until(ExpectedConditions.elementToBeClickable(average)).sendKeys(averageValue);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        //Get Text of product cost
        WebElement productCost = driver.findElement(By.xpath("//*[@id='cost-calc-body']//div[5]/div[@class='pull-right']/p"));
        String valueOfCost = productCost.getText();

        System.out.println(valueOfCost);

        //Get value of total cost
        WebElement totalCost = driver.findElement(By.xpath("//div[@class='pull-right green roboto-med-20']/p"));
        String valueOfTotalCost = totalCost.getText();
        String deleteFirstSymbol = valueOfTotalCost.substring(1);
        //String to float
        System.out.println(valueOfTotalCost);

        float iTest = Float.parseFloat(deleteFirstSymbol);

        //Check value
        if (iTest <= 15) {

            System.out.println("Lower than 15");
            // Try to add element
           /* JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('cost-calc-body').setAttribute('style', 'Low amount')");*/

        } else {

            System.out.println("Big amount");
        }
        
        driver.quit();

    }

}


