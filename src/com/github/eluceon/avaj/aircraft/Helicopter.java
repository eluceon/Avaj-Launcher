package com.github.eluceon.avaj.aircraft;

import com.github.eluceon.avaj.weathertower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Helicopter  extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    private final Map<String, String> reports = new HashMap<String, String>() {{
       put("RAIN", "It's raining. I hate it.");
       put("FOG", "I can't see anything. Be careful.");
       put("SUN", "Looks sunny and nice.");
       put("SNOW", "Don't freeze me!");
    }};
    Helicopter(String name, Coordinates coordinates) {
        super (name, coordinates);
        type = "Helicopter";
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
               coordinates.increaseLongitude(10);
               coordinates.increaseHeight(2);
               break;
            case "RAIN":
                coordinates.increaseLongitude(5);
                break;
            case "FROG":
                coordinates.increaseLongitude(1);
                break;
            case "SNOW":
                coordinates.decreaseHeight(12);
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
