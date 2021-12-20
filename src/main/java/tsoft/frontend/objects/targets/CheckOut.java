package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckOut {
    public static Target NOMBRE = Target.the("Ingreso de texto para el nombre")
            .locatedBy("#input-payment-firstname");

    public static Target APELLIDO = Target.the("Ingreso de texto para el apellido")
            .locatedBy("#input-payment-lastname");

    public static Target DIRECCION_LINEA_1 = Target.the("Ingreso de texto para la primera linea de la dirección")
            .locatedBy("#input-payment-address-1");

    public static Target CIUDAD = Target.the("Ingreso de texto para la ciudad")
            .locatedBy("#input-payment-city");

    public static Target CODIGO_POSTAL = Target.the("Ingreso de texto para el código postal")
            .locatedBy("#input-payment-postcode");

    public static Target PAIS = Target.the("Select para el pais")
            .locatedBy("#input-payment-country");

    public static Target ESTADO = Target.the("Select para el estado")
            .locatedBy("#input-payment-zone");

    public static Target RADIO_NUEVA_DIRECCION = Target.the("Boton para continuar despues de ingresar datos")
            .located(By.cssSelector("input[value=\"new\"]"));

    public static Target BOTON_DATOS_PERSONALES_CONTINUAR = Target.the("Boton para continuar despues de ingresar datos")
            .locatedBy("#button-payment-address");

    public static Target BOTON_DATOS_ENVIO_CONTINUAR = Target.the("Boton para continuar despues de ingresar direccion de envio")
            .locatedBy("#button-shipping-address");

    public static Target BOTON_DETALLE_ENVIO_CONTINUAR = Target.the("Boton para continuar despues de ingresar detalles de envio")
            .locatedBy("#button-shipping-method");

    public static Target ACEPTAR_TERMINOS = Target.the("Boton para aceptar los terminos y condiciones de la compra")
            .located(By.name("agree"));

    public static Target BOTON_PAGO_CONTINUAR = Target.the("Boton para continuar despues de seleccionar metodo de pago")
            .locatedBy("#button-payment-method");

    public static  Target SUBTOTAL_ORDEN = Target.the("Subtotal de la orden")
            .locatedBy("tfoot tr:nth-child(1) td:last-child");

    public static  Target PAGO_ENVIO = Target.the("Cobro del envio")
            .locatedBy("tfoot tr:nth-child(2) td:last-child");

    public static  Target TOTAL_ORDEN = Target.the("Total de la orden")
            .locatedBy("tfoot tr:nth-child(3) td:last-child");

    public static  Target BOTON_CONFIRMAR_PAGO = Target.the("Boton para confirmar el pago")
            .locatedBy("#button-confirm");
}
