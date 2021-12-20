package tsoft.backend.tareas;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.both.Utility.ExcelReader;
import tsoft.frontend.objects.imports.ExcelDataObjects;

import java.io.IOException;

public class Guardar {
    public static Performable enExcel( ) throws IOException {
            ExcelReader.writeCellValue("excel/Test.xlsx","NuevHoja",2,2,"Hola");


        return Task.where("Se almacena el valor en el excel de datos");
    }
}
