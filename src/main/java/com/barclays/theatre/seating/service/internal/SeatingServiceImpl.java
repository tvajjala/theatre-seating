package com.barclays.theatre.seating.service.internal;

import com.barclays.theatre.seating.model.SeatingCapacity;
import com.barclays.theatre.seating.model.SeatingRow;
import com.barclays.theatre.seating.model.SeatingSection;
import com.barclays.theatre.seating.service.SeatingService;

import java.util.Arrays;

/**
 * SeatingService implementation
 *
 * @author ThirupathiReddy Vajjala
 */
public class SeatingServiceImpl implements SeatingService {

    private final SeatingCapacity seatingCapacity = new SeatingCapacity();


    public SeatingServiceImpl() {

    }


    @Override
    public int getTotalAvailableSeats() {
        return seatingCapacity.getSeatingRows().stream().flatMapToInt(row -> row.getSections().stream().mapToInt(seatingSection -> seatingSection.getAvailable())).sum();
    }

    @Override
    public void initializeTheatreWithCapacity(Integer[][] layout) {

        Arrays.stream(layout).forEachOrdered(row -> {
            SeatingRow seatingRow = new SeatingRow();
            Arrays.stream(row).forEachOrdered(seatCount -> {
                SeatingSection seatingSection = new SeatingSection(seatCount);
                seatingRow.getSections().add(seatingSection);
            });
            seatingCapacity.getSeatingRows().add(seatingRow);
        });
    }


    @Override
    public void clearSeatingCapacity() {
        seatingCapacity.getSeatingRows().clear();
    }


    @Override
    public SeatingCapacity getSeatingCapacity() {
        return seatingCapacity;
    }


    @Override
    public void printCurrentAvailabilityLayout() {
        LOGGER.info("********************");
        LOGGER.info("------ SCREEN ------");
        LOGGER.info("********************");
        seatingCapacity.getSeatingRows().stream().forEachOrdered(row ->
                {
                    String rowLayout = row.getSections().stream().map(seatingSection -> String.valueOf(seatingSection.getAvailable() + "\t")).reduce("", String::concat);
                    LOGGER.info("{}", rowLayout);
                }
        );

        LOGGER.info("****** [{}] ********\n", getTotalAvailableSeats());

    }


}
