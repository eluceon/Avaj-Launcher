package com.github.eluceon.avaj;

import com.github.eluceon.avaj.aircraft.AircraftFactory;
import com.github.eluceon.avaj.aircraft.Flyable;
import com.github.eluceon.avaj.exceptions.UsageException;
import com.github.eluceon.avaj.exceptions.InvalidScenarioException;
import com.github.eluceon.avaj.exceptions.AircraftFactoryCreateException;

import com.github.eluceon.avaj.weathertower.WeatherTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Simulator {
    private static final Logger LOGGER = Logger.getLogger("com.github.eluceon.avaj");
    private static int totalSimulations;
    private static WeatherTower weatherTower;

    private static void parseFile(String file) throws IOException,
					InvalidScenarioException, AircraftFactoryCreateException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            totalSimulations = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Invalid number of simulations");
        }

        weatherTower = new WeatherTower();
        while(reader.ready()) {
            String buf[] = reader.readLine().split("\\s+");
            if (buf.length != 5) {
                throw new InvalidScenarioException("[ERR] invalid scenario file");
            }
            try {
                int coords[] = { Integer.parseInt(buf[2]), Integer.parseInt(buf[3]), Integer.parseInt(buf[4]) };
                for (int coord : coords) {
                    if (coord < 1)
                        throw new InvalidScenarioException("[ERR] invalid coordinate in scenario file");
                }
                AircraftFactory.newAircraft(buf[0], buf[1], coords[0], coords[1], coords[2]).registerTower(weatherTower);
            } catch (NumberFormatException e) {
                throw new InvalidScenarioException("[ERR] invalid coordinate in scenario file");
            }
        }
        reader.close();
    }

    private static void simulate() {
        for (int i = 1; i <= totalSimulations; i++) {
            LOGGER.info(String.format("\t------ Simulation #%d ------", i));
            weatherTower.simulate();
        }
    }

    private static void initLogger() throws IOException {
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler("simulation.txt");
        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOGGER.addHandler(fileHandler);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new UsageException("Usage: java Simulator file");
            }
            initLogger();
            parseFile(args[0]);
            simulate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
