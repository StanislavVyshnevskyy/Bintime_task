package pages.resourses;

import java.util.Arrays;
import java.util.stream.Stream;

public class Data {
    public String capital, region, population, name, alpha3Code;
    public String[] borders;
    public double area;

    public static Data getByName(Data[] d, String name){
        Stream<Data> dataStream = Arrays.stream(d);
        return dataStream.filter(s -> s.name.equals(name)).toArray(Data[]::new)[0];
    }
}
