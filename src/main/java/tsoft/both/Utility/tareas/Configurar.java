package tsoft.both.Utility.tareas;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tsoft.both.Utility.BaseClass;

public class Configurar {
    public static Performable laprueba(String casoPrueba) {
        BaseClass.setCasodePrueba(casoPrueba);
        return Task.where("Se configura la linea "+casoPrueba+" para obtener la informacion del excel");
    }

}
