package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class BasePage {
    // base URL
    public static final String BaseURL = "https://www.centralpoint.nl/";

    //Locator for "closeCookieAlert"
    public By closeCookie = By.id("closeCookieAlert");
    // Locator for filters div
    public By filtersDiv = By.id("filters");
    // Locator for list of all filters in filtersDiv
    public By filters = By.cssSelector("#filters .mobileSwitchFiltersOff .filterHead");
    // Locator for list of all options in dropdown
    public By dropDownOptions = By.xpath("../div[@class='dropdown']//ul/li");
    // Locator for title with search results
    public By title = By.xpath("//div[@class='title']/h1");
    // Locator for "Sluiten" button
    public By sluitenButton = By.xpath("../div[@class='dropdown']//div[@class='filterFooter']/button");
    // Locator for min price field
    public By minPriceField = By.id("priceRangeLow");
    // Locator for max price field
    public By maxPriceField = By.id("priceRangehigh");
    // Locator for "Zoeken" button
    public By zoekenButton = By.cssSelector("#filters .dropdown .filterFooter button");
    // Locator for price of products on the page
    public By priceList = By.xpath("//div[@class='price priceExcl']");
    // Locator for "Volgende" button
    public By volgendeButton = By.xpath("//*[contains(text(),'Volgende ')]");
    // Locator for title with productCode
    public By productCode = By.xpath("//*[contains(text(),'Productcode:')]");
    // Locator for search field
    public By searchField = By.xpath("//input[@name='search']");
    // Locator for "Zoek" button
    public By zoekButton = By.xpath("//button[text()='Zoek']");

    // Method returns Selector for item from list of dropDown options for filters
    public By getDropDownSelector(int number){
        return By.xpath(String.format("../div[@class='dropdown']//ul/li[%s]/label/span", number));
    }
    public By getDropDownCheckBoxSelector(int number){
        return By.xpath(String.format("../div[@class='dropdown']//ul/li[%s]/input", number));
    }

    public void closeCookieIfItPresent(WebDriver driver) {
        if (driver.findElement(closeCookie).isDisplayed()) { driver.findElement(closeCookie).click(); }
    }
}
