package br.com.caelum.clines.api.flights;

import br.com.caelum.clines.api.locations.LocationView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightViewFactory viewFactory;

    @Mock
    private FlightFactory flightFactory;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private final String DEFAULT_DATE = "01-05-2020";
    private final LocalDateTime DEFAULT_DATE_TIME = LocalDate.parse(DEFAULT_DATE, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay();
    private final String DEFAULT_COUNTRY = "BR";
    private final String DEFAULT_STATE = "RJ";
    private final String DEFAULT_CITY = "Rio de Janeiro";
    private final LocationView DEFAULT_LOCATION = new LocationView(DEFAULT_COUNTRY, DEFAULT_STATE, DEFAULT_CITY);

    @Test
    void shouldReturnEmptyListWhenNotFoundFlights() {
        given(flightRepository.findAllBy(DEFAULT_DATE_TIME, DEFAULT_COUNTRY, DEFAULT_STATE, DEFAULT_CITY)).willReturn(Collections.emptyList());
        List<FlightView> flights = flightService.searchBy(DEFAULT_DATE_TIME, DEFAULT_LOCATION);
        assertTrue(flights.isEmpty());
    }

    @Test
    void shouldReturnFlightWhenFoundFlights() {
        given(flightRepository.findAllBy(DEFAULT_DATE_TIME, DEFAULT_COUNTRY, DEFAULT_STATE, DEFAULT_CITY)).willReturn(List.of());
        List<FlightView> flights = flightService.searchBy(DEFAULT_DATE_TIME, DEFAULT_LOCATION);
        assertTrue(flights.isEmpty());
    }
}