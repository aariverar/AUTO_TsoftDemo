package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Tienda {
    public static Target BARRA_LATERAL = Target.the("Barra lateral de la tienda")
            .located(By.cssSelector("a[class=\"list-group-item active\"]"));

    public static  Target LAPTOP_HP = Target.the("Laptop HP en la tienda")
            .located(By.cssSelector("div.product-layout:nth-child(1) div div.image a"));

    public static  Target LAPTOP_MAC = Target.the("Laptop Mac en la tienda")
            .located(By.cssSelector("div.product-layout:nth-child(2) div div.image a"));

    public static  Target CAMERA_NIKON = Target.the("Cámara Nikon en la tienda")
            .located(By.cssSelector("div.product-layout:nth-child(2) div div.image a"));

    public static  Target BTN_ADDCART = Target.the("Botón para añadir el producto al carrito")
            .located(By.id("button-cart"));
}
