package edu.iis.mto.bdd.trains.cucumber.steps;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import cucumber.api.Transformer;

public class JodaLocalTimeConverter extends Transformer<LocalTime> {
	DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("hh:mm");

	@Override
	public LocalTime transform(String timeString) {

		return FORMATTER.parseLocalTime(timeString);
	}

}
