package tsoft.frontend.tareas;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import tsoft.both.Utility.BaseClass;
import tsoft.frontend.objects.imports.ExcelDataObjects;
import tsoft.frontend.objects.targets.LoginForm;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IniciarSesion implements Task{

    String email = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_LOGIN,ExcelDataObjects.COLUMN_USER);
    String contrasena = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_LOGIN,ExcelDataObjects.COLUMN_PASSWORD);

    @Override
    @Step("{0} usa sus credenciales para iniciar sesión")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(email).into(LoginForm.EMAIL_FIELD),
                Enter.theValue(contrasena).into(LoginForm.PASSWORD_FIELD),
//                Espera.por(5),
                Click.on(LoginForm.LOGIN_BUTTON)
        );
    }

    public static IniciarSesion conEmailyContrasena(){
        return instrumented(IniciarSesion.class);
    }
}
