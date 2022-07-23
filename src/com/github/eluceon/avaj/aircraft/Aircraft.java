package com.github.eluceon.avaj.aircraft;

import java.util.logging.Logger;

public class Aircraft {
	protected long id;
	protected String type;
	protected String name;
	protected Coordinates coordinates;

	protected static final Logger LOGGER = Logger.getLogger("com.github.eluceon.avaj");
	
	private static long idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private long nextId() {
		return (++idCounter);
	}

	public String getInfo() {
		return (type + '#' + name + '(' + this.id + ')');
	}
}
