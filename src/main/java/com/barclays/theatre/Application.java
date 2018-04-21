package com.barclays.theatre;

import com.barclays.theatre.booking.service.BookingService;
import com.barclays.theatre.booking.service.internal.BookingServiceImpl;
import com.barclays.theatre.seating.service.SeatingService;
import com.barclays.theatre.seating.service.internal.SeatingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class is an entry point to the application. It accepts layout separated by new line and bookings.
 * If no layout format entered it takes default layout format.
 *
 * @author ThirupathiReddy Vajjala
 */

public class Application {
    /**
     * reference to logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    /**
     * reference to seatingService
     */
    private final SeatingService seatingService;

    /**
     * reference to bookingService
     */
    private final BookingService bookingService;

    /**
     * Initialize required service beans in the default constructor
     */
    public Application() {
        seatingService = new SeatingServiceImpl();
        bookingService = new BookingServiceImpl(seatingService);
    }


    /**
     * Application main method
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Application().run();
    }


    /**
     * This method accepts user input and process bookings
     */
    public void run() {

        LOGGER.info("NOTE: Enter layout and bookings in a accepted format. invalid formats ignored");
        LOGGER.info("Enter Layout <PRESS ENTER TO ACCEPT DEFAULT LAYOUT>");

        try (Scanner scanner = new Scanner(System.in)) {


            List<Integer[]> seatingRows = new ArrayList<>(5);
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) {
                seatingRows.add(parseRow(line));
            }
            //Initialize layout
            initializeLayout(seatingRows);
            LOGGER.info("Enter Bookings");

            List<String[]> orders = new ArrayList<>(5);
            while (!(line = scanner.nextLine()).isEmpty()) {
                String[] sections = line.split(" ");
                if (sections.length == 2) {
                    orders.add(sections);
                }
            }


            orders.stream().forEachOrdered(order -> {
                try {
                    String msg = bookingService.processBooking(order[0], Integer.parseInt(order[1]));
                    LOGGER.info(msg);
                } catch (NumberFormatException nfe) {
                    LOGGER.debug("Ignoring invalid booking format");
                }
            });

            seatingService.printCurrentAvailabilityLayout();

        }

    }

    /**
     * initialize theatre with given layout format
     *
     * @param seatingRows seatingLayout
     */
    private void initializeLayout(List<Integer[]> seatingRows) {
        if (!seatingRows.isEmpty()) {
            Integer[][] seatingLayout = seatingRows.stream().filter(row -> row.length > 0).toArray(Integer[][]::new);
            seatingService.initializeTheatreWithCapacity(seatingLayout);
        } else {
            seatingService.initializeTheatreWithDefaultCapacity();
        }

        //print current availability
        seatingService.printCurrentAvailabilityLayout();
    }

    /**
     * validates and parse into array
     *
     * @param row rowAsString
     * @return rowLayout
     */
    private Integer[] parseRow(String row) {
        try {
            return Arrays.stream(row.split("\\s+")).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        } catch (NumberFormatException nfe) {
            LOGGER.debug("Ignoring invalid row format");
        }
        //returns empty array in-case of error
        return new Integer[0];
    }


}
