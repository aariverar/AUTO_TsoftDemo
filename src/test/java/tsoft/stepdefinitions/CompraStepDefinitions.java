package tsoft.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import tsoft.both.Utility.tareas.Configurar;
import tsoft.frontend.tareas.*;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CompraStepDefinitions {

    @Dado("{actor} que quiere comprar en OpenCart {string}")
    public void comprarEnOpenCart(Actor actor, String casoPrueba) {
        actor.attemptsTo(Configurar.laprueba(casoPrueba));

        theActorInTheSpotlight().wasAbleTo(NavegarA.laPaginaPrincipalDeOpenCart());
    }

    @Cuando("se identifica en la pagina")
    public void iniciaSesion() {
        theActorInTheSpotlight().attemptsTo(
                NavegarA.laPaginaDeInicioDeSesion(),
                IniciarSesion.conEmailyContrasena());
    }

    @Y("compra una laptop")
    public void comprarProducto() {
        theActorInTheSpotlight().attemptsTo(
                ElegirEnLaTienda.laptopHP(),
                NavegarA.carritoDeCompras(),
                RevisarCarrito.antesDeComprar()
        );
        theActorInTheSpotlight().attemptsTo(
                RevisarCarrito.verificarSumaCorrectaDePrecios(),
                NavegarA.detalleDePago(),
                PagarProductos.ingresaDatosPersonales(),
                PagarProductos.ingresarDatosEnvio(),
                PagarProductos.metodoDePago(),
                NavegarA.pagarProductos()
        );
        theActorInTheSpotlight().attemptsTo(
                PagarProductos.procederConElPago()
        );
    }

    @Entonces("puede ver la compra exitosa")
    public void puedeVerLaCompraExitosa() {
        Ensure.enableSoftAssertions();
        theActorInTheSpotlight().attemptsTo(
                ValidarCompra.exitosa()
        );
        Ensure.reportSoftAssertions();
    }
}
