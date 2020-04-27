package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("fuel volume")
    @Expose
    private Double fuelVolume;
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @SerializedName("engine temperature")
    @Expose
    private Integer engineTemperature;
    @SerializedName("coolant temperature")
    @Expose
    private Integer coolantTemperature;
    @SerializedName("rpm")
    @Expose
    private Integer rpm;
    @SerializedName("tyre pressure")
    @Expose
    private Integer tyrePressure;

    /**
     * No args constructor for use in serialization
     */
    public Vehicle() {
    }

    /**
     * @param fuelVolume
     * @param latitude
     * @param engineTemperature
     * @param tyrePressure
     * @param coolantTemperature
     * @param speed
     * @param rpm
     * @param longitude
     */
    public Vehicle(Double latitude, Double longitude, Double fuelVolume, Integer speed, Integer engineTemperature, Integer coolantTemperature, Integer rpm, Integer tyrePressure) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.fuelVolume = fuelVolume;
        this.speed = speed;
        this.engineTemperature = engineTemperature;
        this.coolantTemperature = coolantTemperature;
        this.rpm = rpm;
        this.tyrePressure = tyrePressure;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(Double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEngineTemperature() {
        return engineTemperature;
    }

    public void setEngineTemperature(Integer engineTemperature) {
        this.engineTemperature = engineTemperature;
    }

    public Integer getCoolantTemperature() {
        return coolantTemperature;
    }

    public void setCoolantTemperature(Integer coolantTemperature) {
        this.coolantTemperature = coolantTemperature;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Integer getTyrePressure() {
        return tyrePressure;
    }

    public void setTyrePressure(Integer tyrePressure) {
        this.tyrePressure = tyrePressure;
    }

}

