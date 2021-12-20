package tsoft.frontend.tareas;


import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import tsoft.frontend.objects.targets.Menu;
import tsoft.frontend.objects.targets.Tienda;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ElegirEnLaTienda {


    public static Performable laptopHP() {
        return Task.where("{0} compra una laptop HP",
                Click.on(Menu.LAPTOPS_AND_NOTEBOOKS_BUTTON),
                Click.on(Menu.SEE_ALL_LAPTOPS_BUTTON),
                WaitUntil.the(Tienda.BARRA_LATERAL,isVisible()),
                Scroll.to(Tienda.LAPTOP_HP).andAlignToTop(),
                Click.on(Tienda.LAPTOP_HP),
                Scroll.to(Tienda.BTN_ADDCART).andAlignToTop(),
                Click.on(Tienda.BTN_ADDCART));
    }

    public static Performable camaraNikon(){
        return Task.where("{0} compra una cámara Nikon",
                Click.on(Menu.CAMERAS_BUTTON),
                WaitUntil.the(Tienda.BARRA_LATERAL,isVisible()),
                Scroll.to(Tienda.CAMERA_NIKON),
                Click.on(Tienda.CAMERA_NIKON),
                Scroll.to(Tienda.BTN_ADDCART).andAlignToTop(),
                Click.on(Tienda.BTN_ADDCART));
    }
}
