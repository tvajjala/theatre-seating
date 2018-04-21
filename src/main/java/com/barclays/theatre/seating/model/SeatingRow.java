package com.barclays.theatre.seating.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * In-Memory representation of each row. It can accommodate additional attributes in future if required.
 *
 * @author ThirupathiReddy vajjala
 */
public class SeatingRow {

    private List<SeatingSection> sections = new ArrayList<>(3);

    public List<SeatingSection> getSections() {
        return sections;
    }

    public void setSections(List<SeatingSection> sections) {
        this.sections = sections;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeatingRow)) {
            return false;
        }
        SeatingRow that = (SeatingRow) o;
        return Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sections);
    }


    @Override
    public String toString() {
        return "SeatingRow{" +
                "sections=" + sections +
                '}';
    }
}