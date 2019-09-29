package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringDataOverviewApplicationTests {

	@Autowired
	private FlightRepository flightRepository;

	@Test
	public void shouldPerformCrudOperations() {

		final Flight flight = new Flight();
		flight.setOrigin("London");
		flight.setDestination("New York");
		flight.setOriginallyScheduledAt(ZonedDateTime.parse("2011-12-13T12:12:00Z"));
		flight.setNowScheduledAt(ZonedDateTime.parse("2011-12-13T12:12:00Z"));

		flightRepository.save(flight);

		assertThat(flightRepository.findAll())
				.hasSize(1)
				.first()
				.isEqualToComparingFieldByField(flight);

		flightRepository.deleteById(flight.getId());

		assertThat(flightRepository.count()).isZero();
	}

}
