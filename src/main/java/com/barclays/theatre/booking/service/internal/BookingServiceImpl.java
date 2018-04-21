package com.barclays.theatre.booking.service.internal;

import com.barclays.theatre.booking.service.BookingService;
import com.barclays.theatre.seating.model.SeatingCapacity;
import com.barclays.theatre.seating.model.SeatingRow;
import com.barclays.theatre.seating.model.SeatingSection;
import com.barclays.theatre.seating.service.SeatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BookingService implementation
 *
 * @author ThirupathiReddy Vajjala
 */
public class BookingServiceImpl implements BookingService {

    /**
     * reference to logger
     */
    private static final Logger LOGGER = LogManager.getLogger(BookingServiceImpl.class);

    /**
     * reference to seatingService
     */
    private final SeatingService seatingService;


    public BookingServiceImpl(SeatingService seatingService) {
        this.seatingService = seatingService;
    }


    @Override
    public String processBooking(String party, int ticketCount) {

        if (party == null || party == "") {
            return "Invalid Party.";
        }

        if (ticketCount <= 0 || seatingService.getTotalAvailableSeats() < ticketCount) {
            return String.format("%s Sorry, we can't handle your party.", party);
        }

        SeatingCapacity seatingCapacity = seatingService.getSeatingCapacity();

        for (SeatingRow seatingRow : seatingCapacity.getSeatingRows()) {

            for (SeatingSection seatingSection : seatingRow.getSections()) {

                if (seatingSection.isBooked() || seatingSection.getAvailable() < ticketCount) {
                    continue;
                }

                return handleBooking(seatingCapacity, seatingRow, seatingSection, party, ticketCount);

            }
        }

        return String.format("%s Call to split party.", party);
    }

    private String handleBooking(SeatingCapacity seatingCapacity, SeatingRow seatingRow, SeatingSection seatingSection, String party, int ticketCount) {
        int rowIndex = seatingCapacity.getSeatingRows().indexOf(seatingRow) + 1;
        int sectionIndex = seatingRow.getSections().indexOf(seatingSection) + 1;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} Row {} Section {}", party, rowIndex, sectionIndex);
        }

        seatingSection.setAvailable(seatingSection.getAvailable() - ticketCount);
        seatingSection.setBooked(seatingSection.getAvailable() == 0);

        return String.format("%s Row %d Section %d", party, rowIndex, sectionIndex);
    }
}
