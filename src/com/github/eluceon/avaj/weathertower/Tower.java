package com.github.eluceon.avaj.weathertower;

import com.github.eluceon.avaj.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    private static final Logger LOGGER = Logger.getLogger("com.github.eluceon.avaj");

    public void register(Flyable flyable) {
        observers.add(flyable);
        LOGGER.info("Tower says: " + flyable.getInfo() + " registered to weather tower.");
    }
    public void unRegister(Flyable flyable) {
        observers.remove(flyable);
        LOGGER.info("Tower says: " + flyable.getInfo() + " unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            int curSize = observers.size();
            observers.get(i).updateConditions();
            if (curSize > observers.size()) {
                --i;
            }
        }
    }
}
