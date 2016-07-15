package edu.iis.mto.bdd.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"html:target/cucumber" }, tags = {"@booking"}, glue = "edu.iis.mto.bdd.cucumber.steps")
public class BookingAFlightTests {

}
