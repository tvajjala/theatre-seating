package com.barclays.theatre.seating.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * In-Memory representation of TheatreLayout. layout contains rows which is represented as {@link SeatingRow} objects and each
 * row contains list of seating sections as {@link SeatingSection}.
 *
 * @author ThirupathiReddy Vajjala
 */
public class SeatingCapacity {

    private List<SeatingRow> seatingRows = new ArrayList<>(5);

    public List<SeatingRow> getSeatingRows() {
        return seatingRows;
    }

    public void setSeatingRows(List<SeatingRow> seatingRows) {
        this.seatingRows = seatingRows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeatingCapacity)) {
            return false;
        }
        SeatingCapacity that = (SeatingCapacity) o;
        return Objects.equals(seatingRows, that.seatingRows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatingRows);
    }

    @Override
    public String toString() {
        return "SeatingCapacity{" +
                "seatingRows=" + seatingRows +
                '}';
    }
}



