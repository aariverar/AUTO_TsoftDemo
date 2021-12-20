package tsoft.backend.tareas;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.both.Utility.StringHandler;
import tsoft.frontend.models.ConsultaJsonPath;
import tsoft.frontend.models.ConsultaRegEx;

import static tsoft.both.Utility.BaseClass.desdeService;

public class Capturar {

    public static Performable porRegex() {
        String regexExcel = desdeService(ExcelServiceObjetcts.COLUMN_REGEX);
        if(regexExcel.equals("")){
            return Task.where("No se ha definido una captura de datos por Expresiones Regulares");
        }
        return Task.where("",actor -> {
            String[] consultasRegex = regexExcel.split("(_regex;\r\n|_regex;)");

            for (int i = 0; i < consultasRegex.length; i++) {

                ConsultaRegEx ConsultaRegEx = new ConsultaRegEx(consultasRegex[1]);
                String valorEsperado = ConsultaRegEx.getValorEsperado();
                String valorRegex = ConsultaRegEx.getValorObtenido();
                String identificadorDeGuardado = ConsultaRegEx.getIdentificadorDeGuardado();
                String report = ConsultaRegEx.getReport();

                if (!valorEsperado.equals("")) Ensure.that(valorEsperado).isEqualTo(valorRegex);
                if (!identificadorDeGuardado.equals(""))
                    StringHandler.saveParameter(identificadorDeGuardado, valorRegex);

                Serenity.recordReportData().withTitle("Validador regex N°" + (i+1)).andContents(report);
            }
        });
    }

    public static Performable porJsonPath() {
        String jsonPathExcel = desdeService(ExcelServiceObjetcts.COLUMN_JSONPATH);
        if(jsonPathExcel.equals("")) return Task.where("No se ha definido una captura de datos por Json Path");

        return Task.where("Se realiza captura por JsonPath",actor -> {

            String[] jsonpathValuesStrings = jsonPathExcel.split("(_jsonpath;\r\n|_jsonpath;)");

            for (int x = 0; x < jsonpathValuesStrings.length; x++) {

                ConsultaJsonPath consultaJsonPath = new ConsultaJsonPath(jsonpathValuesStrings[x]);
                String paramToSave = consultaJsonPath.getIdentificadorDeGuardado();
                String valorObtenido = consultaJsonPath.getValorObtenido();
                String valorEsperado = consultaJsonPath.getValorEsperado();
                String report = consultaJsonPath.getReport();

                if (!valorEsperado.equals("")) Ensure.that(valorObtenido).isEqualTo(valorEsperado);
                if (!paramToSave.equals("")) StringHandler.saveParameter(paramToSave, valorObtenido);
                Serenity.recordReportData().withTitle("Validador jsonpath N°" + (x+1)).andContents(report);

            }
        });
    }

    public static Performable porJsonPathSimple() {
        String jsonPathExcel = desdeService(ExcelServiceObjetcts.COLUMN_JSONPATH_SIMPLE);
        if(jsonPathExcel.equals("")) return Task.where("No se ha definido una captura de datos por Json Path Simple");
        return Task.where("Se realiza captura por JsonPath Simple",actor ->
        {
            String[] stringValues = jsonPathExcel.split("(;\n|;\n\r|;)");

            for (int x = 0; x < stringValues.length && !stringValues[x].equals(""); x++) {
                String report;
                String valorObtenido;
                String jsonpath = stringValues[x];
                String compareWith = jsonpath;
                String save = "";

                if (stringValues[x].contains(",")){
                    jsonpath = stringValues[x].split(",")[0];
                    compareWith = jsonpath;
                    save = stringValues[x].split(",")[1];
                    if (save.equals("def")) save = jsonpath;
                }
                report = "Extraer valor de: '" + jsonpath;
                valorObtenido = SerenityRest.lastResponse().getBody().path(jsonpath).toString();

                if(valorObtenido.contains("[[")){
                    jsonpath += ".*";
                    valorObtenido = SerenityRest.lastResponse().getBody().path(jsonpath).toString();
                }

                valorObtenido = valorObtenido.replace("[","").replace("]","").replace("\"","").replace(",",",\n\t");

                report += "'\nValor obtenido: \n{\n\t" + valorObtenido + "\n}";

                if(!compareWith.equals("")){
                    String valToCompare = desdeService(compareWith).replace("\n","").replace("\r","");
                    System.out.println(compareWith);
                    if (!valToCompare.equals("")){
                        Ensure.that(valToCompare).isEqualTo(valorObtenido);
                        report += "\n - Valor esperado: \n{\n\t" + valToCompare.replace(",",",\n\t") + "\n}";
                    }
                }

                Serenity.recordReportData().withTitle("Validador simple de json path N°"+ (x+1)).andContents(report);
                if(!save.equals("")) StringHandler.saveParameter(save, valorObtenido);
            }
        });
    }
}
