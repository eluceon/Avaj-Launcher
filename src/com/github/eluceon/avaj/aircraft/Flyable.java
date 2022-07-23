package com.github.eluceon.avaj.aircraft;

import com.github.eluceon.avaj.weathertower.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
	String getInfo();
}