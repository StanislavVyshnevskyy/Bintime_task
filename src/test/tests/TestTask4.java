package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.resourses.Data;

import java.io.IOException;
import java.util.HashMap;

public class TestTask4 extends APITestBase{
    String testURL = "http://restcountries.eu/rest/v1/";
    HashMap r;
    Data dataUkr;

    @Test
    public void testUkraine(){
        try {
            r = get(testURL);
        } catch (IOException e) {
            System.out.println("IOException occurred");
            e.printStackTrace();
        }

        Assert.assertEquals("200", r.get("statusCode").toString());
        Assert.assertTrue(r.get("type").toString().toLowerCase().contains("application/json"));

        dataUkr = Data.getByName(getDataArr(),"Ukraine");
        Assert.assertTrue(dataUkr.area > 500000.0);
        System.out.println("Name: "+dataUkr.name);
        System.out.println("Capital: "+dataUkr.capital);
        System.out.println("Region: "+dataUkr.region);
        System.out.println("Population: "+dataUkr.population);
        System.out.print("Borders: ");
        for (String b : dataUkr.borders ){
            System.out.print(b+" ");
        }
        System.out.println();
    }
}
