package util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Experiment1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String in = "src/main/java/json/vehicle1.json";
        FileReader fis = new FileReader(in);
        //InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(fis);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        String json = sb.toString();
        Gson gson = new Gson();
        Example[] v = gson.fromJson(json, Example[].class);
        System.out.println(json);
        System.out.println(v[0].getLatitude());
        System.out.println(v[1].getLatitude());

        JsonArray arr = new JsonArray();
        HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();
        for(int i = 0; i < v.length; i++)
        {
            JsonObject d = new JsonObject();
            d.addProperty("latitude", v[i].getLatitude());
            d.addProperty("longitude", v[i].getLongitude());
            map.put("json" + i, d );
            arr.add(map.get("json" + i));
        }
        System.out.println(arr.toString());

        for (int i = 0; i < arr.size(); i++) {
            JsonObject j = (JsonObject) arr.get(i);
            System.out.println("load" + j);
        }
//        JsonArray arr = new JsonArray();
//        for (int i = 0; i < v.length; i++)
//        {
//            arr.("", String.valueOf(v[i].getLatitude()));
//        }
//        System.out.println(arr);
        /*
        while(true) {
            for (Example e : v) {
                System.out.println("latitude: " + e.getLatitude());
                System.out.println("longitude: " + e.getLongitude());
                System.out.println("fuel volume: " + e.getFuelVolume());
                System.out.println("speed: " + e.getSpeed());
                System.out.println("engine temperature: " + e.getEngineTemperature());
                System.out.println("coolant temperature: " + e.getCoolantTemperature());
                System.out.println("rpm: " + e.getRpm());
                System.out.println("tyre pressure: " + e.getTyrePressure());
            }


        } */
//        Experiment1 e = new Experiment1();
//        //JsonObject o = e.createData(v);
//        JsonArray o = e.createData(v);
//        System.out.println(o);
    }

    /*
    public JsonElement serialize(Example src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("latitude", src.getLatitude());
    }*/
//    public JsonArray createData(Example[] e) throws InterruptedException {
//        JsonArray arr = new JsonArray();
//        arr = e;
//        return arr;
//    }
}
