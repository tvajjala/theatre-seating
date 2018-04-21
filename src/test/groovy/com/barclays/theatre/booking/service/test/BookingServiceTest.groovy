package com.barclays.theatre.booking.service.test

import com.barclays.theatre.booking.service.internal.BookingServiceImpl
import com.barclays.theatre.seating.service.internal.SeatingServiceImpl
import spock.lang.Specification

/**
 * This bookingServiceTest case runs against test data
 */
class BookingServiceTest extends Specification {


    static def seatingService = new SeatingServiceImpl()

    def bookingService = new BookingServiceImpl(seatingService)

    def setupSpec() {
        seatingService.initializeTheatreWithDefaultCapacity()
    }


    def "processBooking() | valid partyName and ticketCount | positive "() {

        given: "default Seating layout"
        seatingService.printCurrentAvailabilityLayout()
        when: "Accepting booking "
        print "Booking " + party + " with " + ticketCount + " tickets\n"
        def msg = bookingService.processBooking(party, ticketCount)
        print msg + "\n"
        then: "Expecting response"

        msg == response
        where: "Booking dataSet"
        party      | ticketCount | response
        "Smith"    | 2           | "Smith Row 1 Section 1"
        "Jones"    | 5           | "Jones Row 1 Section 2"
        "Davis"    | 6           | "Davis Row 3 Section 2"
        "Wilson"   | 100         | "Wilson Sorry, we can't handle your party."
        "Johnson"  | 3           | "Johnson Row 1 Section 1"
        "Williams" | 4           | "Williams Row 2 Section 2"
        "Brown"    | 8           | "Brown Row 4 Section 2"
        "Miller"   | 12          | "Miller Call to split party."

    }


    def "processBooking() | Invalid  ticketCount | negative "() {

        given: "default Seating layout"
        seatingService.printCurrentAvailabilityLayout()
        when: "Accepting booking "
        print "Booking " + party + " with " + ticketCount + " tickets\n"
        def msg = bookingService.processBooking(party, ticketCount)
        print msg + "\n"
        then: "Expecting response"

        msg == response
        where: "Booking dataSet"
        party    | ticketCount | response
        "Wilson" | -2          | "Wilson Sorry, we can't handle your party."

    }

    def "processBooking() | Invalid  party | negative "() {

        given: "default Seating layout"
        seatingService.printCurrentAvailabilityLayout()
        when: "Accepting booking "
        print "Booking " + party + " with " + ticketCount + " tickets\n"
        def msg = bookingService.processBooking(party, ticketCount)
        print msg + "\n"
        then: "Expecting response"

        msg == response
        where: "Booking dataSet"
        party | ticketCount | response
        ""    | -2          | "Invalid Party."
        null  | -2          | "Invalid Party."

    }


}
