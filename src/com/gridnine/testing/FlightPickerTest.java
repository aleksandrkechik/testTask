package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightPickerTest {

	List<Flight> flights;
	/*В данном случае я сделал упрощенные тесты, поскольку методы Flight Builder'a приватные,
	а я не хотел его модифицировать, поскольку он является частью задания.
	В идеале я предпочел бы создать список полетов и сравнивать с ним.*/

	@BeforeEach
	void setUp() {
		flights = FlightBuilder.createFlights();
	}

	@org.junit.jupiter.api.Test
	void pickFlightBeforeMoment() {
		assertEquals(5, FlightPicker.pickFlightBeforeMoment(flights, LocalDateTime.now()).size());
	}

	@org.junit.jupiter.api.Test
	void checkArrBeforeDep() {
		assertEquals(5, FlightPicker.checkArrBeforeDep(flights).size());
	}

	@org.junit.jupiter.api.Test
	void lessThanTwoHoursOnTheGround() {
		assertEquals(4, FlightPicker.lessThanTwoHoursOnTheGround(flights).size());
	}
}