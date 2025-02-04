package devices;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.wiotp.sdk.codecs.JsonCodec;
import com.ibm.wiotp.sdk.device.DeviceClient;
import com.ibm.wiotp.sdk.device.config.DeviceConfig;
import com.ibm.wiotp.sdk.device.config.DeviceConfigAuth;
import com.ibm.wiotp.sdk.device.config.DeviceConfigIdentity;
import com.ibm.wiotp.sdk.device.config.DeviceConfigOptions;
import com.ibm.wiotp.sdk.device.config.DeviceConfigOptionsMqtt;
import model.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Scanner;

public class Vehicle2 implements Runnable
{
    private boolean quit = false;
    public DeviceClient client;

    public Vehicle2() throws Exception {
        DeviceConfig config;
        DeviceConfigIdentity identity = new DeviceConfigIdentity("sra4nc", "vehicles", "vehicle_2");
        DeviceConfigAuth auth = new DeviceConfigAuth("JBW4bCHcjNz151vMm(");
        DeviceConfigOptionsMqtt deviceMQTTOptions = new DeviceConfigOptionsMqtt(8883, "tcp", null, true, 60, 60);

        DeviceConfigOptions options = new DeviceConfigOptions();
        options.domain = "internetofthings.ibmcloud.com";
        options.mqtt = deviceMQTTOptions;
        config = new DeviceConfig(identity, auth, options);
        this.client = new DeviceClient(config);
        this.client.registerCodec(new JsonCodec());

    }

    public JsonArray createData() throws IOException {
        String in = "src/main/java/json/vehicle2.json";
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
        Vehicle[] v = gson.fromJson(json, Vehicle[].class);

        JsonArray arr = new JsonArray();
        HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();
        for(int i = 0; i < v.length; i++)
        {
            JsonObject d = new JsonObject();
            d.addProperty("id", 2);
            d.addProperty("name", "Robert Clarke");
            d.addProperty("registration_number", "sv20grb");
            d.addProperty("latitude", v[i].getLatitude());
            d.addProperty("longitude", v[i].getLongitude());
            d.addProperty("fuel_volume", v[i].getFuelVolume());
            d.addProperty("speed", v[i].getSpeed());
            d.addProperty("engine_temperature", v[i].getEngineTemperature());
            d.addProperty("coolant_temperature", v[i].getCoolantTemperature());
            d.addProperty("rpm", v[i].getRpm());
            d.addProperty("tyre_pressure", v[i].getTyrePressure());
            d.addProperty("timestamp", String.valueOf(new Timestamp(System.currentTimeMillis())));
            map.put("json" + i, d );
            arr.add(map.get("json" + i));
        }
        System.out.println(arr.toString());
        return arr;
    }

    public void quit() {
        this.quit = true;
    }

    public void run() {
        try {
            client.connect();
            System.out.println("Start publishing event every second...");
            // Send a dataset every 1 second, until we are told to quit
            while (!quit) {
                JsonArray arr = createData();
                //System.out.println(arr);
                for (int i = 0; i < arr.size(); i++) {
                    JsonObject j = (JsonObject) arr.get(i);
                    client.publishEvent("test", j, 0);
                    Thread.sleep(5000);
                }
            }

            // Once told to stop, cleanly disconnect from the service
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // Start the device thread
        Vehicle2 d = new Vehicle2();
        Thread t1 = new Thread(d);
        t1.start();

        System.out.println("Connected successfully - Your device ID is " + d.client.getConfig().getDeviceId());
        System.out.println(" * Organization: " + d.client.getConfig().getOrgId());
        System.out.println("Device type " + d.client.getConfig().getTypeId());
        System.out.println("connect options" + d.client.getConfig().getMqttConnectOptions());
        System.out.println("url" + d.client.getConfig().getMqttServerURI());
        System.out.println("(Press <enter> to disconnect)");

        // Wait for <enter>
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();

        System.out.println("Closing connection to the IBM IoT Platform");
        // Let the device thread know it can terminate
        d.quit();
    }
}
