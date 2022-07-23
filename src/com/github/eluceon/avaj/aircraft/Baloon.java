package com.github.eluceon.avaj.aircraft;

import com.github.eluceon.avaj.weathertower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    private final Map<String, String> reports = new HashMap<String, String>() {{
        put("RAIN", "I'm all wet.");
        put("FOG", "Where are you? Give me your hand.");
        put("SUN", "I like sun. Go up.");
        put("SNOW", "It's too cold, I'm down.");
    }};

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        type = "Baloon";
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates.increaseLongitude(2);
                coordinates.increaseHeight(4);
                break;
            case "RAIN":
                coordinates.decreaseHeight(5);
                break;
            case "FROG":
                coordinates.decreaseHeight(3);
                break;
            case "SNOW":
                coordinates.decreaseHeight(15);
        }

        LOGGER.info(getInfo() + ": " + reports.get(weather));

        if (coordinates.getHeight() == 0) {
            LOGGER.info(getInfo() + " landing.");
            weatherTower.unRegister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }
}
