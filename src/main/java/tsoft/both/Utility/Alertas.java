package tsoft.both.Utility;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Alertas {
    public static Performable obtenerTexto(SessionVariables variable) {
        String mensajeAlerta = BrowseTheWeb.as(theActorInTheSpotlight()).getAlert().getText();
        theActorInTheSpotlight().remember(variable.toString(),mensajeAlerta);
        return Task.where("Se lee que la alerta tiene el texto \""+mensajeAlerta+"\" y se recuerda en la variable " + variable);
    }

    public static Performable obtenerTextoSeccionado(SessionVariables variable, int x, int y) {
        String mensajeAlerta = BrowseTheWeb.as(theActorInTheSpotlight()).getAlert().getText().substring(x,y);
        theActorInTheSpotlight().remember(variable.toString(),mensajeAlerta);
        return Task.where("Se lee que la alerta tiene el texto \""+mensajeAlerta+"\" y se recuerda en la variable " + variable);
    }

    public static Performable aceptar(SessionVariables variable) {
        BrowseTheWeb.as(theActorInTheSpotlight()).getAlert().accept();
        System.out.println(theActorInTheSpotlight().recall(variable.toString()).toString());
        return Task.where("Se acepta la alerta",(actor) -> {
            Serenity.takeScreenshot();
        });
    }
}
