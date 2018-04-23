package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestTask5 {

    private String inputID;
    private Boolean expectedResult;
    private ProductPresenceValidator pv;

    @Before
    public void initialize() {
        pv = new ProductPresenceValidator();
    }

    public TestTask5(String inputID, Boolean expectedResult) {
        this.inputID = inputID;
        this.expectedResult = expectedResult;
    }


    @Parameterized.Parameters
    public static Collection IDList() {
        return Arrays.asList(new Object[][] {
                { "J153289", true },
                { "MQ3D2ZD/A", true },
                { "L36852-H2436-M101", true },
                { "1WZ03EA#ABH", true },
                { "875839-425", true },
                { "C5J91A#B19", true },
                { "FM32SD45B/10", true },
                { "204446-101", true },
                { "GV-N710D3-1GL", true },
                { "02G-P4-6150-KR", true }
        });
    }

    @Test
    public void testProductExists() {
        System.out.println("Parameterized ID is : " + inputID);
        Assert.assertEquals(expectedResult,
                pv.validate(inputID));
    }

}

class ProductPresenceValidator {
    public Boolean validate(String id) {
        Boolean result = false;
        ChromeDriver driver = new ChromeDriver();
        driver.get(BasePage.BaseURL);
        BasePage page = new BasePage();
        WebElement searchField = driver.findElement(page.searchField);
        searchField.click();
        searchField.sendKeys(id);
        WebElement zoekButton = driver.findElement(page.zoekButton);
        zoekButton.click();
        try {
            WebElement pcode = driver.findElement(page.productCode);
            if (pcode.getText().contains(String.format("Productcode: %s", id))) {
                result = true;
            }
        } catch (Exception e){
            System.out.println("No item with ID: "+id+". Or multiple results for the same ID");
        }

        driver.quit();
        return result;
    }
}