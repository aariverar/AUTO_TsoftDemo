package tsoft.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import tsoft.both.Utility.tareas.Configurar;
import tsoft.frontend.tareas.IniciarSesion;
import tsoft.frontend.tareas.NavegarA;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class demoStepDefinitions {

    @Given("{actor} quiere registrarse en Zairito {string}")
    public void clienteQuiereRegistrarseEnZairito(Actor actor, String casoPrueba) {
        actor.attemptsTo(Configurar.laprueba(casoPrueba));
        theActorInTheSpotlight().wasAbleTo(NavegarA.demoLaPaginaDeZairito());
    }

    @When("ingresa a mi cuenta y crear cuenta")
    public void ingresaAMiCuentaYCrearCuenta() {
        theActorInTheSpotlight().attemptsTo(
                NavegarA.demoCerrarPopUp(),
                NavegarA.demoIngresarFormulario());

    }

    @Then("llena el formulario con sus {string}")
    public void llenaElFormularioConSus(String arg0) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(
                NavegarA.ingresarDatosFormulario()
        );
        Thread.sleep(5000);
    }

}
