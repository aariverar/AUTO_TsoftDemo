package tsoft.frontend.tareas;

import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.waits.WaitWithTimeout;
import net.thucydides.core.model.TakeScreenshots;
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.openqa.selenium.Keys;
import tsoft.both.Utility.BaseClass;
import tsoft.frontend.objects.imports.ExcelDataObjects;
import tsoft.frontend.objects.targets.BarraSuperior;
import tsoft.frontend.objects.targets.CarritoCompras;
import tsoft.frontend.objects.targets.CheckOut;
import tsoft.frontend.objects.targets.demoTsoft;


import java.awt.*;
import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarA {

    public static Performable laPaginaPrincipalDeOpenCart(){

        String Url = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_URL,ExcelDataObjects.COLUMN_URLCARLOS);
        return Task.where("{0} abre la home page de OpenCart",
                Open.url(Url));

    }
    public static Performable laPaginaDeGoogle(){

        String Url = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_URL,ExcelDataObjects.COLUMN_URLPEPE);
        return Task.where("{0} abre la home page de GOOGLE",
                Open.url(Url));

    }

    public static Performable carritoDeCompras() {
        return Task.where("{0} se dirige al carrito de compras",
                Scroll.to(BarraSuperior.SHOPPING_CART),
                Click.on(BarraSuperior.SHOPPING_CART),
                WaitUntil.the(CarritoCompras.CONTENIDO,isVisible()),
                Scroll.to(CarritoCompras.TOTALES_UNITARIOS)
        );
    }
    public static Performable detalleDePago(){
        return Task.where("{0} se dirige a ingresar sus detalles para pagar",
                Click.on(CarritoCompras.IR_AL_CHECKOUT));
    }

    public static Performable pagarProductos(){
        return Task.where("{0} se dirige a pagar su carrito de compras",
                Click.on(CheckOut.BOTON_PAGO_CONTINUAR),
                WaitUntil.the(CheckOut.TOTAL_ORDEN,isVisible())
        );
    }

    public static Performable laPaginaDeInicioDeSesion() {
        return Task.where("{0} se dirige a iniciar sesión",
                Click.on(BarraSuperior.MY_ACCOUNT_DROPDOWN),
                Click.on(BarraSuperior.LOGIN_BUTTON)
        );
    }

    public static Performable demoLaPaginaDeZairito(){

        String Url = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_URL,ExcelDataObjects.COLUMN_URLDEMO);
        return Task.where("{0} abre la home page de ZAIRITO",
                Open.url(Url));

    }

    public static Performable demoCerrarPopUp(){

        return Task.where("{0} cierra el popup inicial",
              Hit.the(Keys.ESCAPE).into(demoTsoft.BTN_CERRAR_POPUP),
              Hit.the(Keys.ESCAPE).into(demoTsoft.BTN_CERRAR_POPUP)
                      );
    }
    public static Performable demoIngresarFormulario(){

        return Task.where("{0} ingresa al formulario",
                Click.on(demoTsoft.BTN_MI_CUENTA),
                Hit.the(Keys.ENTER).into(demoTsoft.BTN_SIGNUP)
        );
    }

    public static Performable ingresarDatosFormulario(){

        return Task.where("{0} llena datos de formulario",
                Enter.theValue("Abraham").into(demoTsoft.TXT_NOMBRE),
                Enter.theValue("aalexriverar@gmail.com").into(demoTsoft.TXT_CORREO),
                Enter.theValue("ariverar").into(demoTsoft.TXT_USERNAME),
                Enter.theValue("a4671639").into(demoTsoft.TXT_PASSWORD),
                Hit.the(Keys.ENTER).into(demoTsoft.BTN_INGRESAR)
                        );
    }
}
