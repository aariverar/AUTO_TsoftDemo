package tsoft.frontend.tareas;

import tsoft.both.Utility.BaseClass;
import tsoft.frontend.objects.imports.ExcelDataObjects;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import tsoft.frontend.objects.targets.Tienda;
import tsoft.frontend.questions.preciosAlPagar;
import tsoft.frontend.objects.targets.CheckOut;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PagarProductos {
    public static Performable ingresaDatosPersonales(){

        String nombre       = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_FIRSTNAME);
        String apellido     = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_LASTNAME);
        String direccion    = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_ADDRESS);
        String ciudad       = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_CITY);
        String codigoPostal = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_POSTCODE);
        String pais         = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_COUNTRY);
        String estado       = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_DETALLES,ExcelDataObjects.COLUMN_ZONE);

        return Task.where("{0} realiza correctamente el pago de sus cámara",
                Click.on(CheckOut.RADIO_NUEVA_DIRECCION),
                Enter.theValue(nombre).into(CheckOut.NOMBRE),
                Enter.theValue(apellido).into(CheckOut.APELLIDO),
                Enter.theValue(direccion).into(CheckOut.DIRECCION_LINEA_1),
                Enter.theValue(ciudad).into(CheckOut.CIUDAD),
                Enter.theValue(codigoPostal).into(CheckOut.CODIGO_POSTAL),
                Click.on(CheckOut.PAIS),
                SelectFromOptions.byVisibleText(pais).from(CheckOut.PAIS),
                SelectFromOptions.byVisibleText(estado).from(CheckOut.ESTADO),
                Scroll.to(CheckOut.BOTON_DATOS_PERSONALES_CONTINUAR).andAlignToTop(),
                Click.on(CheckOut.BOTON_DATOS_PERSONALES_CONTINUAR)
        );
    }

    public static Performable ingresarDatosEnvio(){

        return Task.where("{0} ingresa sus datos de envio}",
                Click.on(CheckOut.BOTON_DATOS_ENVIO_CONTINUAR),
                Click.on(CheckOut.BOTON_DETALLE_ENVIO_CONTINUAR)
        );
    }

    public static Performable metodoDePago(){

        return Task.where("{0} ingresa sus detalles de pago",
                CheckCheckbox.of(CheckOut.ACEPTAR_TERMINOS)
        );
    }

    public static Performable procederConElPago(){

        return Task.where("{0} verifica que la información de pago es correcta",
            Ensure.that(preciosAlPagar.precioTotal()).isEqualTo(
                    preciosAlPagar.precioSubtotal().answeredBy(theActorInTheSpotlight())
                            +
                            preciosAlPagar.precioEnvio().answeredBy(theActorInTheSpotlight())
            ),
                Click.on(CheckOut.BOTON_CONFIRMAR_PAGO)
        );
    }

}
