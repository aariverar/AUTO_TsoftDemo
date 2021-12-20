package tsoft.both.Utility;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.frontend.objects.imports.ExcelDataObjects;

import java.io.IOException;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static tsoft.both.Utility.ExcelReader.writeCellValue;

public class BaseClass {
    private static int countPage;

    public static void setCasodePrueba(String testCase) {
        BaseClass.countPage = Integer.parseInt(testCase) - 1;
    }

    public static String desdeUI(String hoja, String columna){
        try {
            return ExcelReader.data(ExcelDataObjects.EXCEL_DOC,hoja).get(countPage).get(columna);
        } catch (Exception e) {
            e.printStackTrace();
            return "No encontrado";
        }
    }

    public static String desdeService(String columna){
        try {
            return ExcelReader.data(ExcelServiceObjetcts.EXCEL_DOC,ExcelServiceObjetcts.EXCEL_SHEET).get(countPage).get(columna);
        } catch (Exception e) {
            e.printStackTrace();
            return "No encontrado";
        }
    }

    public static String desdeGeneral(String ruta,String hoja,String fila,String columna){
        int casoPrueba = Integer.parseInt(fila) -1;

        try {
            return ExcelReader.data(ruta,hoja).get(casoPrueba).get(columna);
        } catch (Exception e) {
            e.printStackTrace();
            return "No encontrado";
        }
    }

    public static WebDriver elegirNavegador() {
        String navegador = BaseClass.desdeUI(ExcelDataObjects.EXCEL_SHEET_URL,ExcelDataObjects.COLUMN_BROWSER);
        switch ("Chrome"){
            case "Chrome":
//                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();


            case "Firefox":
//                WebDriverManager.chromedriver().setup();
                return new FirefoxDriver();


            case "Edge":
//                WebDriverManager.chromedriver().setup();
                return new EdgeDriver();

            default:
                return new ChromeDriver();

        }
    }

    public static Performable escribirValorV(SessionVariables variable) {
        String valorVariable=theActorInTheSpotlight().recall(variable.toString()).toString();
        return Task.where("Se escribe en Excel",(actor)-> {
                Serenity.recordReportData().withTitle("Cadena Insertada").andContents(valorVariable);
            try {
                writeCellValue(ExcelDataObjects.EXCEL_DOC,ExcelDataObjects.EXCEL_SHEET_URL,countPage+1,6,valorVariable);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se Pudo escribir en el Excel");
            }
        });
    }

    public static Performable GuardarEnExcelConVariable(String hoja,int columna,SessionVariables variable) {
        return Task.where("Se escribe en Excel desde un valor almacenado",(actor)-> {
                String valorVariable=theActorInTheSpotlight().recall(variable.toString()).toString();
                GuardarEnExcel(hoja, columna,valorVariable);
        });
    }

    protected static Performable GuardarEnExcel(String hoja,int columna, String texto){
        return Task.where("Guarda dato en excel",actor -> {
            try {
                writeCellValue("excel/"+ExcelDataObjects.EXCEL_DOC, hoja, countPage + 1, columna,texto);
                Serenity.recordReportData().withTitle("Cadena Insertada").andContents(texto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Performable Dormir(long i) {
        return Task.where("dormir por "+ i + " milisegundos",(actor)-> {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }});
    }
}
