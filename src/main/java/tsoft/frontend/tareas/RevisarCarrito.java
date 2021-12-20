package tsoft.frontend.tareas;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import tsoft.frontend.questions.preciosCarrito;
import tsoft.frontend.objects.targets.BarraSuperior;
import tsoft.frontend.objects.targets.CarritoCompras;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RevisarCarrito {
    public static Performable antesDeComprar() {
        return Task.where("{0} espera a que cargue el carrito",
                Click.on(BarraSuperior.SHOPPING_CART),
                WaitUntil.the(CarritoCompras.CONTENIDO,isVisible()),
                Scroll.to(CarritoCompras.TOTALES_UNITARIOS)
        );
    }

    public static Performable verificarSumaCorrectaDePrecios(){
        return Task.where("{0} revisa que se sumen correctamente los precios en el carrito",
                Ensure.that(preciosCarrito.unitariosSuman())
                        .isEqualTo(preciosCarrito.subtotalCompra().answeredBy(theActorInTheSpotlight())
                        )
        );
    }
}
