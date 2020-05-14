package com.gridnine.testing;


import java.time.LocalDateTime;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Flight> flights = FlightBuilder.createFlights();

		System.out.println("Printing flights not departed yet:");
		printFlights(FlightPicker.pickFlightBeforeMoment(flights, LocalDateTime.now()));
		System.out.println("Printing flights without arrival before departure:");
		printFlights(FlightPicker.checkArrBeforeDep(flights));
		System.out.println("Printing flights with less than two hours on the ground:");
		printFlights(FlightPicker.lessThanTwoHoursOnTheGround(flights));
	}

	private static void printFlights(List<Flight> flights) {
		for (Flight flight : flights) {
			System.out.println(flight);
		}
	}
}
