package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.MonitorenPage;

import java.util.List;


public class TestTask2 {
    private ChromeDriver driver;
    private MonitorenPage monitor;
    private Actions actions;
    private WebElement prijsFilter;
    private WebElement minPriceField, maxPriceField, zButton;

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        monitor = new MonitorenPage();
        driver.get(MonitorenPage.MonitorenURL);
        driver.manage().window().maximize();
        try {
            driver.findElement(monitor.closeCookie).click();
        } catch (org.openqa.selenium.ElementNotVisibleException e){}
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testPrijsFilter(){

        List<WebElement> filtersList = driver.findElements(monitor.filters);
        filtersList.removeIf(fl -> !fl.getText().equals("Prijs"));
        System.out.println(filtersList.size());
        prijsFilter = filtersList.get(0);
        prijsFilter.click();

        minPriceField = driver.findElement(monitor.minPriceField);
        maxPriceField = driver.findElement(monitor.maxPriceField);

        minPriceField.click();
        minPriceField.sendKeys(Keys.CONTROL+"a");
        minPriceField.sendKeys(Keys.BACK_SPACE);
        minPriceField.sendKeys("1000");

        maxPriceField.click();
        maxPriceField.sendKeys(Keys.CONTROL+"a");
        maxPriceField.sendKeys(Keys.BACK_SPACE);
        maxPriceField.sendKeys("5000");

        zButton = driver.findElement(monitor.zoekenButton);
        zButton.click();

        List<WebElement> priseList = driver.findElements(monitor.priceList);

        Boolean hasNext = true;
        while (hasNext) {
            hasNext = false;
            for (WebElement el:priseList){
                System.out.println(el.getText());
                Assert.assertTrue(Integer.parseInt(el.getText().replaceAll("[^0-9]+", "")) >= 1000);
            }
            try {
                driver.findElement(monitor.volgendeButton).click();
                hasNext = true;
                priseList = driver.findElements(monitor.priceList);
            } catch (Exception e) {
                System.out.println("No more results");
            }
        }

    }

}
