package com.github.eluceon.avaj.aircraft;
public enum AircraftType {
	JET_PLANE("JetPlane", "554cd647d6b135f7e36ab1214c5e816a"),
    HELICOPTER ("Helicopter", "2ab8b43468e8b92b0fc5c81e70e35a2d"),
	BALOON ("Baloon", "994736b4f0aec72f6e5ae580051d012f");

	private String type;
	private String encryptedType;

	private AircraftType(String type, String encryptedType) {
		this.type = type;
		this.encryptedType = encryptedType;
	}

	public static AircraftType fromString(String type) {
		for (AircraftType aircraftType : values()) {
			if (aircraftType.type.equals(type) || aircraftType.encryptedType.equals(type)) {
				return aircraftType;
			}
		}
		return null;
	}
}
