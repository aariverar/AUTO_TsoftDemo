package tsoft.frontend.objects.targets;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class demoTsoft {

    public static  Target BTN_CERRAR_POPUP = Target.the("Boton para cerrar pop up inicial")
            .located(By.xpath("/html/body/div[16]/div/div/div/div/div[1]/button"));

    public static  Target BTN_MI_CUENTA = Target.the("Boton para ingresar a login")
            .located(By.className("account-btn"));

    public static  Target BTN_SIGNUP = Target.the("Boton para ingresar al formulario")
            .located(By.cssSelector("a[href='sign-up.html'][class='forget-password-link']"));

    public static  Target TXT_NOMBRE = Target.the("Caja de texto del nombre")
            .located(By.cssSelector("input[type='text'][class='form-control rounded-left'][placeholder='Name']"));

    public static  Target TXT_CORREO = Target.the("Caja de texto del correo")
            .located(By.cssSelector("input[type='email'][class='form-control rounded-left'][placeholder='Email']"));

    public static  Target TXT_USERNAME = Target.the("Caja de texto del username")
            .located(By.cssSelector("input[type='text'][class='form-control rounded-left'][placeholder='Username']"));

    public static  Target TXT_PASSWORD = Target.the("Caja de texto del password")
            .located(By.cssSelector("input[type='password'][class='form-control rounded-left'][placeholder='Password']"));

    public static  Target BTN_INGRESAR = Target.the("boton para ingresar el login")
            .located(By.cssSelector("a[href='sign-in.html'][class='forget-password-link']"));




}
