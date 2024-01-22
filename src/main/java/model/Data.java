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
 * @param year Year of the recorded piece of data.
 * @param month Month of the recorded piece of data.
 * @param value Value of the recorded piece of data.
 */
public record Data(int year, int month, int value) {

    /**
     * Represents a single piece of recorded data, including the year, month, and value.
     *
     * @param year  The year of the recorded data.
     * @param month The month of the recorded data.
     * @param value The value of the recorded data.
     */
    public Data { }

    /**
     * Returns the year the data was recorded.
     *
     * @return The recorded year of the data.
     */
    @Override
    public int year() { return this.year; }

    /**
     * Gets the current month of the data.
     *
     * @return The current month of the data.
     */
    @Override
    public int month() { return this.month; }

    /**
     * Return the value of the current data point.
     *
     * @return The current value of the data.
     */
    @Override
    public int value() { return this.value; }

    /**
     * Create a String to represent the data in the data object.
     *
     * @return The data object as a String.
     */
    @Override
    public String toString() { return "Value: " + value() + " - Month: " + month() + " - Year: " + year(); }

}
