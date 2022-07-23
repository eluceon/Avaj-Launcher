package com.github.eluceon.avaj.weathertower;

import com.github.eluceon.avaj.aircraft.Coordinates;
import com.github.eluceon.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }

    public void simulate() {
        changeWeather();
    }
    void changeWeather() {
        conditionsChanged();
    }
}

