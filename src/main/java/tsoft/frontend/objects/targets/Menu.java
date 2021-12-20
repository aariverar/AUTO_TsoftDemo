package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Menu {
    public static Target LAPTOPS_AND_NOTEBOOKS_BUTTON = Target.the("Boton de laptops y Notebooks en el navbar")
                                                    .located(By.cssSelector("nav#menu div ul li.dropdown:nth-child(2) a"));

    public static Target SEE_ALL_LAPTOPS_BUTTON = Target.the("Boton de ver todas las laptops y notebooks")
                                                .located(By.cssSelector("nav#menu div ul li.dropdown:nth-child(2) a.see-all"));

    public static Target CAMERAS_BUTTON = Target.the("Boton de cámaras")
                                        .located(By.cssSelector("nav#menu div ul li:nth-child(7) a"));
}
