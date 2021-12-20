package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginForm {
    public static Target EMAIL_FIELD = Target.the("Ingreso de email")
            .locatedBy("#input-email");
    public static Target PASSWORD_FIELD = Target.the("Ingreso de contrasena")
            .locatedBy("#input-password");
    public static Target LOGIN_BUTTON = Target.the("Botón de Inicio de sesión")
            .located(By.cssSelector("input[value=\"Login\"]"));
}
