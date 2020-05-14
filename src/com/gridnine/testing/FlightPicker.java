package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlightPicker {

	private static final int twoHoursInMinutes = 120;

	//Checking flight list, filtering flights by first segment's departure time
	public static List<Flight> pickFlightBeforeMoment(List<Flight> flights, LocalDateTime moment) {
		return flights.parallelStream().
				filter(flight -> !flight.getSegments().get(0).getDepartureDate().isBefore(moment)).
				collect(Collectors.toList());
	}

	//Checking flight list, filtering flights by comparing every segment's departure and arrival time difference
	public static List<Flight> checkArrBeforeDep(List<Flight> flights) {
		return flights.parallelStream().
				filter(flight -> flight.getSegments().parallelStream().
						noneMatch(segment ->
								segment.getArrivalDate().isBefore(segment.getDepartureDate()))).
				collect(Collectors.toList());
	}

	//Counting total ground time for each flight< and filtering original list by this condition
	public static List<Flight> lessThanTwoHoursOnTheGround(List<Flight> flights) {
		return flights.parallelStream().filter(flight -> IntStream.range(1, flight.getSegments().size()).parallel().
				mapToLong(i -> {
					Segment firstSegment = flight.getSegments().get(i - 1);
					Segment secondSegment = flight.getSegments().get(i);
					return Duration.between(firstSegment.getArrivalDate(), secondSegment.getDepartureDate()).toMinutes();
				}).sum() < twoHoursInMinutes).collect(Collectors.toList());
	}
}
