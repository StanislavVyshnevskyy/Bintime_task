package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.resourses.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class TestTask3 extends APITestBase {
    String testURL = "http://restcountries.eu/rest/v1/";
    HashMap r;
    Data dataLat, dataEst;

    @Test
    public void testLatvia(){
        try {
            r = get(testURL);
        } catch (IOException e) {
            System.out.println("IOException occurred");
            e.printStackTrace();
        }

        Assert.assertEquals("200", r.get("statusCode").toString());
        Assert.assertTrue(r.get("type").toString().toLowerCase().contains("application/json"));

        dataLat = Data.getByName(getDataArr(),"Latvia");
        dataEst = Data.getByName(getDataArr(),"Estonia");
        Assert.assertTrue(Arrays.asList(dataLat.borders).contains(dataEst.alpha3Code));
    }
}
