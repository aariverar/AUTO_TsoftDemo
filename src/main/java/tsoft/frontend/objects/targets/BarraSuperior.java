package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BarraSuperior {
    public static Target MY_ACCOUNT_DROPDOWN = Target.the("Desplegable Mi cuenta")
            .located(By.cssSelector("a[title=\"My Account\"]"));

    public static Target LOGIN_BUTTON = Target.the("Botón Login")
            .locatedBy("ul.dropdown-menu li:last-child a");

    public static Target SHOPPING_CART = Target.the("Botón del carrito")
            .located(By.cssSelector("a[title=\"Shopping Cart\"]"));
}
