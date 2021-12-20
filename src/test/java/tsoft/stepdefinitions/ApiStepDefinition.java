package tsoft.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.backend.tareas.Capturar;
import tsoft.backend.tareas.Solicitud;
import tsoft.backend.tareas.Validar;
import tsoft.both.Utility.BaseClass;
import tsoft.both.Utility.tareas.Configurar;

import java.io.IOException;

public class ApiStepDefinition {


    @Dado("que {actor} quiere probar una API {string}")
    public void quiereProbarUnaAPI(Actor actor,String casoPrueba) {
        actor.attemptsTo(Configurar.laprueba(casoPrueba));
        String Url = BaseClass.desdeService(ExcelServiceObjetcts.COLUMN_RUTA);
        actor.whoCan(CallAnApi.at(Url));
    }

    @Cuando("{actor} envia una solicitud a la API")
    public void EnviaUnaSolicitudALaAPI(Actor actor) {
        actor.attemptsTo(
                Solicitud.conHeaders(),
                Solicitud.conBody(),
                Solicitud.conParametros(),
                Solicitud.seEnvia()
        );

    }

    @Entonces("{actor} valida la respuesta")
    public void ValidaLaRespuesta(Actor actor) {
        actor.attemptsTo(
                Validar.elCodigoDeRespuesta(),
                Validar.elContentType(),
                Validar.elSchemadeRespuesta()
        );
    }

    @Y("capturamos los datos nececesarios")
    public void capturamosLosDatosNececesarios() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Capturar.porRegex(),
                Capturar.porJsonPath(),
                Capturar.porJsonPathSimple()
        );
    }
}
