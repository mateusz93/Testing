package edu.iis.mto.bdd.trains.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"html:target/cucumber" }, glue = "edu.iis.mto.bdd.trains.cucumber.steps", features = {"classpath:edu.iis.mto.bdd.trains.cucumber"})
public class AcceptanceTestsSuite {

}
