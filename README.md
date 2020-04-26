# WatsonIoTFleetManagement

A Fleet Managing IoT project that will track data on 5 vehicles. 
The data being used is the:
                            Latitude,
                            Longitude,
                            Fuel Volume,
                            Speed,
                            Engine Temperature,
                            Coolant Temperature,
                            RPM,
                            Tyre Pressure
The data will be stored in 5 seperate json file with each file representing each vehicle.
Each file will contain 100 rows of data each.
The data will be read from the files.
Then:The data will be deserialized into a string and serialized into a JSON Array.
The next step is to create devices in watson IoT and save the device configuration details to a local file.
Then each watson Java IoT library Device client gets the configuration details of the related created devices.
From there it will be sent to the IBM Cloud using the Watson IoT Java SDK client library.
Watson Iot must be opened for the cloud to retrieve the events.
The data will be sent as events every 5 seconds to the cloud.
A Node RED application will be created that will be a flow of nodes for all the connected parts of the application.
Once in the cloud the data will be retrieved in Node RED where the data will be deseriazlized to local types.
Then the location data will be passed to the map.
The diagostic and general data will be passed to the dashboard for visualization of the data.


## Getting Started

1. Run the device simulator (Java Application with the implementation of the IBM Watson IoT libary)
2. View the data being retreived in the Watson IoT/Node RED
3. View the visualizations in the map and the dashboard.

### Prerequisites

IBM Cloud account.
A Java IDE
Knowledge of Java for the device simulator

## Built With

* [IBM Cloud](https://cloud.ibm.com/docs) - The Cloud Platform used
* [Watson IoT](https://www.ibm.com/cloud/watson-iot-platform) - IBM Clouds IoT Platform
* [Node-RED](https://nodered.org/) - IoT Platform for wiring inputs and outputs to cloud services and applications.
* [Javascript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) - Language used in Node-RED
* [Java](https://docs.oracle.com/javase/8/docs/) - Language used used in Device Simulator

## Authors

Amar Khaliq


## License

This project is licensed by the author 