package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NotebookLaptopPage;

import java.util.List;
import java.util.Random;

public class TestTask1 {
    private ChromeDriver driver;
    private NotebookLaptopPage laptop;
    private Actions actions;
    private Integer itemsNumber;


    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        laptop = new NotebookLaptopPage();
        driver.get(NotebookLaptopPage.NotebookURL);
        driver.manage().window().maximize();
        try {
            driver.findElement(laptop.closeCookie).click();
        } catch (org.openqa.selenium.ElementNotVisibleException e){}
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void testRandomFilter() {
        List<WebElement> filtersList = driver.findElements(laptop.filters);
        filtersList.removeIf(fl -> fl.getText().equals("Prijs")||fl.getText().equals("Sortering"));

        int randomFilterNumber = new Random().nextInt(filtersList.size());
        WebElement randomFilter = filtersList.get(randomFilterNumber);
        System.out.println(randomFilter.getText());
        randomFilter.click();


        int availableOptionsNumber = randomFilter.findElements(laptop.dropDownOptions).size();

        int randomNumber = new Random().nextInt(availableOptionsNumber) + 1;
        WebElement randomOption = randomFilter.findElement(laptop
                .getDropDownSelector(randomNumber));
        actions.moveToElement(randomOption).build().perform();

        itemsNumber = Integer.parseInt(randomOption.getText().replaceAll("[^0-9]+", ""));
        System.out.println("expected number of products: "+itemsNumber);
        actions.moveToElement(randomFilter.findElement(laptop.getDropDownCheckBoxSelector(randomNumber)))
                .click().build().perform();
        WebElement sButton = randomFilter.findElement(laptop.sluitenButton);

        actions.moveToElement(sButton).click(sButton).build().perform();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(laptop.title).isDisplayed();
            }
        });
        if (itemsNumber == 1){
            Assert.assertTrue(driver.findElement(laptop.productCode).isDisplayed());
        } else {
            int actualNumber = Integer.parseInt(driver.findElement(laptop.title)
                    .getText().replaceAll("[^0-9]+", ""));
            System.out.println(actualNumber + "   =    " + itemsNumber);
            Assert.assertTrue(actualNumber == itemsNumber);
        }
    }
}
