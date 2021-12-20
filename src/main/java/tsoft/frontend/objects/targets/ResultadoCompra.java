package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;

public class ResultadoCompra {
    public static  Target RUTA_RESULTADO = Target.the("ruta del resultado de compra")
            .locatedBy(".breadcrumb li:nth-child(4)");

    public static  Target MENSAJE_FINAL = Target.the("mensaje del resultado de compra")
            .locatedBy("#content h1");

    public static Target CONTINUE_HOME = Target.the("botón para volver al inicio")
            .locatedBy(".buttons div a");
}
