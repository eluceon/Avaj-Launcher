package com.github.eluceon.avaj.aircraft;
import com.github.eluceon.avaj.exceptions.AircraftFactoryCreateException;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude,
				int latitude, int height) throws AircraftFactoryCreateException {
		AircraftType aircraftType = AircraftType.fromString(type);

		if (aircraftType == AircraftType.HELICOPTER) {
			return new Helicopter(name, new Coordinates(longitude, latitude, height));
		} else if (aircraftType == AircraftType.JET_PLANE) {
			return new JetPlane(name, new Coordinates(longitude, latitude, height));
		} else if (aircraftType == AircraftType.BALOON) {
			return new Baloon(name, new Coordinates(longitude, latitude, height));
		} else {
			throw new AircraftFactoryCreateException("Wrong aircraft type passed");
		}
    }
}
