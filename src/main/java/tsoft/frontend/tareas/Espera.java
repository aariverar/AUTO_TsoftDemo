package tsoft.frontend.tareas;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.DriverTask;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class Espera {
    public Espera() {
    }
    public static Performable por(long mills, WebDriver driver) throws InterruptedException {
        driver.wait(500);
        return Task.where("se espero "+ mills + "Milisegundos"  );
    }
}
