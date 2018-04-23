package tests;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pages.resourses.Data;

import java.io.IOException;
import java.util.HashMap;

public class APITestBase {

    private OkHttpClient client = new OkHttpClient();
    private HashMap responseMap = new HashMap<String,String>();
    private Data[] dataArr;

    protected HashMap get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        responseMap.put("body", response.body().string());
        responseMap.put("statusCode", response.code());
        responseMap.put("type", response.header("Content-Type"));

        dataArr = new Gson().fromJson(responseMap.get("body").toString(), Data[].class);

        return responseMap;
    }

    protected Data[] getDataArr(){
        return dataArr;
    }

}
