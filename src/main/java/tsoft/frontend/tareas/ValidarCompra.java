package tsoft.frontend.tareas;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import tsoft.frontend.objects.targets.ResultadoCompra;

public class ValidarCompra {
    public static Performable exitosa() {
        return Task.where("{0} valida que se realizó el pago de su compra",
                Ensure.that(ResultadoCompra.RUTA_RESULTADO).text().isEqualTo("Success"),
                Ensure.that(ResultadoCompra.MENSAJE_FINAL).text().isEqualTo("Your order has been placed!"),
                Scroll.to(ResultadoCompra.CONTINUE_HOME).andAlignToTop(),
                Click.on(ResultadoCompra.CONTINUE_HOME)
        );
    }
}
