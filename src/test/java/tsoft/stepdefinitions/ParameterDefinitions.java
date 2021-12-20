package tsoft.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ParameterDefinitions {
    OnlineCast theCast = new OnlineCast();
    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        Ensure.enableSoftAssertions();
        OnStage.setTheStage(theCast);
    }

    @After
    public void drawCurtains(){
        Ensure.reportSoftAssertions();
    }
}
