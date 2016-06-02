package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class DestinationStationSteps {

    @Zakładając("^że chcę się dostać z \"(.*)\" do \"(.*)\"$")
    public void givenArrivingTrains(String city1, String city2) {
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (.*) na linii \"(.*)\"$")
    public void givenDepartureTrains(@Transform(JodaLocalTimeConverter.class) LocalTime startTime, String city) {
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIAskForTime() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) LocalTime arrivingTime) {
        throw new PendingException();
    }

}
