package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarritoCompras {
    public static Target CONTENIDO = Target.the("Tabla de resumen de compras")
            .locatedBy("#content");

    public static  Target IR_AL_CHECKOUT = Target.the("Boton para proceder con la compra")
            .located(By.cssSelector("a[href=\"https://opencart.abstracta.us:443/index.php?route=checkout/checkout\"]"));

    public static  Target TOTALES_UNITARIOS = Target.the("Fila de los totales de cada producto")
            .locatedBy("#content form table tbody tr td:last-child");

    public static  Target SUBTOTAL_COMPRA = Target.the("Subtotal de la toda la compra")
            .locatedBy("#content .row table tbody tr:nth-child(1) td:last-child");
}
