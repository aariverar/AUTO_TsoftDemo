package tsoft.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import tsoft.both.Utility.tareas.Configurar;
import tsoft.frontend.tareas.*;

public class CompraParalelaStepDefinition {

    @Y("{actor} quiere ingresar a Google")
    public void usuarioQuiereIngresarAGoogle(Actor actor) {
        actor.wasAbleTo(NavegarA.laPaginaDeGoogle());
    }

    @Cuando("{actor} se identifica en la pagina")
    public void iniciaSesion(Actor actor) {
        actor.attemptsTo(
                NavegarA.laPaginaDeInicioDeSesion(),
                IniciarSesion.conEmailyContrasena());
    }

    @Y("{actor} compra una laptop")
    public void comprarProducto(Actor actor) {
        actor.attemptsTo(
                ElegirEnLaTienda.laptopHP(),
                NavegarA.carritoDeCompras(),
                RevisarCarrito.antesDeComprar()
        );
        actor.attemptsTo(
                RevisarCarrito.verificarSumaCorrectaDePrecios(),
                NavegarA.detalleDePago(),
                PagarProductos.ingresaDatosPersonales(),
                PagarProductos.ingresarDatosEnvio(),
                PagarProductos.metodoDePago(),
                NavegarA.pagarProductos()
        );
        actor.attemptsTo(
                PagarProductos.procederConElPago()
        );
    }

    @Entonces("{actor} puede ver la compra exitosa")
    public void puedeVerLaCompraExitosa(Actor actor) {
        Ensure.enableSoftAssertions();
        actor.attemptsTo(
                ValidarCompra.exitosa()
        );
        Ensure.reportSoftAssertions();
    }

}
