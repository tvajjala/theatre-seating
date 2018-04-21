package com.barclays.theatre.seating.model;

import java.util.Objects;

/**
 * In-Memory representation of each seatingSection.
 *
 * @author ThirupathiReddy Vajjala
 */
public class SeatingSection {

    /**
     * Total seats for the sections
     */
    private int seatCount;

    /**
     * boolean flag to check given sections already booked or not
     */
    private boolean booked;

    /**
     * Number of available seats at the given time
     */
    private int available;


    public SeatingSection(int seatCount) {
        this.seatCount = seatCount;
        available = seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }


    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeatingSection)) {
            return false;
        }
        SeatingSection that = (SeatingSection) o;
        return seatCount == that.seatCount &&
                booked == that.booked &&
                available == that.available;
    }

    @Override
    public int hashCode() {

        return Objects.hash(seatCount, booked, available);
    }

    @Override
    public String toString() {
        return "SeatingSection{" +
                "seatCount=" + seatCount +
                ", booked=" + booked +
                ", available=" + available +
                '}';
    }

}
