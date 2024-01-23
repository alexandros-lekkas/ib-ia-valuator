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
     */
    public Data() { }

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
    public String toString() { return "Value: " + getValue() + " - Month: " + getMonth() + " - Year: " + getYear(); }

    /**
     * Returns the year the data was recorded.
     *
     * @return The recorded year of the data.
     */
    public int getYear() { return this.year; }

    /**
     * Sets the year of the recorded data.
     *
     * @param year The year of the recorded data.
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Gets the current month of the data.
     *
     * @return The current month of the data.
     */
    public int getMonth() { return this.month; }

    /**
     * Sets the month of the recorded data.
     *
     * @param month The month of the recorded data.
     */
    public void setMonth(int month) { this.month = month; }

    /**
     * Return the value of the current data point.
     *
     * @return The current value of the data.
     */
    public int getValue() { return this.value; }

    /**
     * Sets the value of the data point to the provided value.
     *
     * @param value The new value for the data point.
     */
    public void setValue(int value) { this.value = value; }

}
