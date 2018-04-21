package com.barclays.theatre.seating.service;

import com.barclays.theatre.seating.model.SeatingCapacity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This {@link SeatingService} interface handles layout creation and updates seating availability after each booking.
 * <p>
 * It contains one default implementation to populate default layout, if the user don't enters layout
 *
 * @author ThirupathiReddy Vajjala
 */
public interface SeatingService {

    Logger LOGGER = LogManager.getLogger(SeatingService.class);

    /**
     * initialize Theatre with default capacity
     */
    default void initializeTheatreWithDefaultCapacity() {
        Integer[][] defaultCapacity = new Integer[][]{{6, 6}, {3, 5, 5, 3}, {4, 6, 6, 4}, {2, 8, 8, 2}, {6, 6}};
        initializeTheatreWithCapacity(defaultCapacity);
        LOGGER.info("Theatre initialized with default layout...");
    }


    /**
     * Initialize Theatre with the capacity given
     *
     * @param layout theatreLayout
     */
    void initializeTheatreWithCapacity(Integer[][] layout);

    /**
     * Clear Seating Capacity for the next show
     */
    void clearSeatingCapacity();

    /**
     * Returns seating Capacity details
     *
     * @return SeatingCapacity {@link SeatingCapacity}
     */
    SeatingCapacity getSeatingCapacity();


    /**
     * prints currentAvailability layout
     */
    void printCurrentAvailabilityLayout();


    /**
     * Returns total available seats
     *
     * @return availableSeats
     */
    int getTotalAvailableSeats();


}
