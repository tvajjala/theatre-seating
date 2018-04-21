package com.barclays.theatre.seating.service.test

import com.barclays.theatre.seating.service.internal.SeatingServiceImpl
import spock.lang.Specification

/**
 * Test case to check layout initialization
 *
 * @author ThirupathiReddy Vajjala
 */
class SeatingServiceTest extends Specification {

    def seatingService = new SeatingServiceImpl()


    def "initializeTheatreWithCapacity() | positive"() {

        given: "seating layout"
        def layout = (Integer[][]) [[8, 4], [5, 6, 5]].toArray()

        when: "Invoking initializeTheatreWithCapacity method"
        seatingService.initializeTheatreWithCapacity(layout)

        then: "Expect valid layout"
        seatingService.getTotalAvailableSeats() == 28
        seatingService.getSeatingCapacity().seatingRows.get(0).sections.get(0).seatCount == 8
    }


}
