package com.github.eluceon.avaj.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        setLongitude(longitude);
        setLatitude(latitude);
        setHeight(height);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude < 1 ? 1 : longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude < 1 ? 1 : latitude;
    }

    public void setHeight(int height) {
        if (height < 0) {
            height = 0;
        } else if (height > 100) {
            height = 100;
        }
        this.height = height;
    }

    public void increaseLongitude(int longitude) {
        setLongitude(this.longitude + longitude);
    }

    public void increaseLatitude(int latitude) {
        setLatitude(this.latitude + latitude);
    }

    public void increaseHeight(int height) {
        setHeight(this.height + height);
    }

    public void decreaseLongitude(int longitude) {
        setLongitude(this.longitude - longitude);
    }

    public void decreaseLatitude(int latitude) {
        setLatitude(this.latitude - latitude);
    }

    public void decreaseHeight(int height) {
        setHeight(this.height - height);
    }
}