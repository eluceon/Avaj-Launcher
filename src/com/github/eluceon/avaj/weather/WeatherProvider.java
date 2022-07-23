package com.github.eluceon.avaj.weather;

import com.github.eluceon.avaj.aircraft.Coordinates;

public class WeatherProvider {
    private static volatile WeatherProvider weatherProvider;
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getWeatherProvider() {
        WeatherProvider localInstance = weatherProvider;
        if (localInstance == null) {
            synchronized (WeatherProvider.class) {
                localInstance = weatherProvider;
                if (weatherProvider == null) {
                    weatherProvider = localInstance =  new WeatherProvider();
                }
            }
        }
        return localInstance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % weather.length];
    }
}
