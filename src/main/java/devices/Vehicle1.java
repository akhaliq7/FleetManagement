package devices;

import com.google.gson.JsonObject;
import com.ibm.wiotp.sdk.codecs.JsonCodec;
import com.ibm.wiotp.sdk.device.DeviceClient;
import com.ibm.wiotp.sdk.device.config.DeviceConfig;
import com.ibm.wiotp.sdk.device.config.DeviceConfigAuth;
import com.ibm.wiotp.sdk.device.config.DeviceConfigIdentity;
import com.ibm.wiotp.sdk.device.config.DeviceConfigOptions;
import com.ibm.wiotp.sdk.device.config.DeviceConfigOptionsMqtt;

import java.util.Scanner;

public class Vehicle1 implements Runnable
{
    private boolean quit = false;
    public DeviceClient client;

    public Vehicle1() throws Exception {
        DeviceConfig config;
        DeviceConfigIdentity identity = new DeviceConfigIdentity("sra4nc", "vehicles", "vehicle_1");
        DeviceConfigAuth auth = new DeviceConfigAuth("6W4kOwlMu3fYx)xJm*");
        DeviceConfigOptionsMqtt deviceMQTTOptions = new DeviceConfigOptionsMqtt(8883, "tcp", null, true, 60, 60);

        DeviceConfigOptions options = new DeviceConfigOptions();
        options.domain = "internetofthings.ibmcloud.com";
        options.mqtt = deviceMQTTOptions;
        config = new DeviceConfig(identity, auth, options);
        this.client = new DeviceClient(config);
        this.client.registerCodec(new JsonCodec());

    }

    public JsonObject createData() throws InterruptedException {
        JsonObject data = new JsonObject();
        // Do something to generate data
        data.addProperty("distance", 10);
        return data;
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
                JsonObject data = createData();
                System.out.println(data);
                client.publishEvent("test", data, 0);
                Thread.sleep(5000);
            }

            // Once told to stop, cleanly disconnect from the service
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // Start the device thread
        Vehicle1 d = new Vehicle1();
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
