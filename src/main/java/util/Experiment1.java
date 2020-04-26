package util;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Experiment1 {
    public static void main(String[] args) throws IOException {
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

        for (Example e : v) {
            System.out.println(e.getLatitude());
        }
    }
}
