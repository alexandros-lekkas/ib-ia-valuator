/*
 * Copyright (c) 2024 Alexandros Lekkas. All rights reserved.
 *
 * This work is a part of the Computer Science Internal Assessment for the International Baccalaureate program by
 * Alexandros Lekkas. Unauthorized reproduction, distribution, or use of this material is prohibited.
 */

package model;

/**
 * Data represents a single piece of recorded data, including the year, month, and value.
 *
 * @implNote Not stored as a Record as there are certain operations needed.
 */
public class Data {

    // Year of the recorded Data.
    int year;

    // Month of the recorded Data.
    int month;

    // Value of the recorded Data.
    int value;

    /**
     * Represents a single piece of recorded data, including the year, month, and value.
     *
     * @param year  The year of the recorded data.
     * @param month The month of the recorded data.
     * @param value The value of the recorded data.
     */
    public Data(int year, int month, int value) {

        this.year = year;
        this.month = month;
        this.value = value;

    }

    /**
     * Create a String to represent the data in the data object.
     *
     * @return The data object as a String.
     */
    @Override
    public String toString() { return "Value: " + value() + " - Month: " + month() + " - Year: " + year(); }

    /**
     * Returns the year the data was recorded.
     *
     * @return The recorded year of the data.
     */
    public int year() { return this.year; }

    /**
     * Gets the current month of the data.
     *
     * @return The current month of the data.
     */
    public int month() { return this.month; }

    /**
     * Return the value of the current data point.
     *
     * @return The current value of the data.
     */
    public int value() { return this.value; }

}
