package com.truongdx8.androidproject4;

public class WeatherItem {
    private String DateTime;
    private int WeatherIcon;
    private Temperature Temperature;

    public WeatherItem() {

    }

    public WeatherItem(String dateTime, int weatherIcon, com.truongdx8.androidproject4.Temperature temperature) {
        DateTime = dateTime;
        WeatherIcon = weatherIcon;
        Temperature = temperature;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public com.truongdx8.androidproject4.Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(com.truongdx8.androidproject4.Temperature temperature) {
        Temperature = temperature;
    }
}
