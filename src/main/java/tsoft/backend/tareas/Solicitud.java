package tsoft.backend.tareas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.both.Utility.BaseClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static tsoft.both.Utility.StringHandler.*;

public class Solicitud {

    private static HashMap<String,String> headersRequest = new HashMap<>();
    private static HashMap<String,String> parametrosRequest = new HashMap<>();
    private static String body = "";

    public static Performable seEnvia() {
        String metodo = BaseClass.desdeService(ExcelServiceObjetcts.COLUMN_METHOD);

        switch (metodo){
            case "":
                return Task.where("No se ha establecido una solicitud");
            case "GET":
                return Get.resource("").withRequest(Solicitud::generada);
            case "POST":
                return Post.to("").withRequest(Solicitud::generada);
            case "PUT":
                return Put.to("").withRequest(Solicitud::generada);
            case "DELETE":
                return Delete.from("").withRequest(Solicitud::generada);
            default:
                return Task.where("La solicitud de tipo "+metodo+" no se admite");
        }
    }

    public static Performable conHeaders() {
        String headerString = BaseClass.desdeService(ExcelServiceObjetcts.COLUMN_HEADERS);
        parametrosRequest.clear();

        if (headerString.equals("")) return Task.where("No hubieron headers que agregar a la solicitud");

        return Task.where("Se agregaron los headers a la solicitud",actor -> {
            try {
                headersRequest = extractValues(headerString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Serenity.recordReportData().withTitle("Headers importados").andContents(prettyPrintOf(headersRequest));
        });
    }

    public static Performable conBody(){
        String stringBody = BaseClass.desdeService(ExcelServiceObjetcts.COLUMN_BODY);
        body = "";

        if (stringBody.equals("")) return Task.where("No se estabecio body que agregar a la solicitud");

        return Task.where("Se agrego el body a la solicitud",actor -> {
            try {
                body = prepararData(stringBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Serenity.recordReportData().withTitle("Body importado").andContents(body);
        });
    }

    public static Performable conParametros(){
        String parameters = BaseClass.desdeService(ExcelServiceObjetcts.COLUMN_PARAMETERS);
        parametrosRequest.clear();

        if (parameters.equals("")) return Task.where("No hubieron parametros que agregar a la solicitud");

        return Task.where("Se agregaron los parametros a la solicitud",actor -> {
            try {
                parametrosRequest = extractValues(parameters);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Serenity.recordReportData().withTitle("Parametros importados").andContents(prettyPrintOf(parametrosRequest));
        });
    }

    private static String prettyPrintOf(HashMap<String, String> Hash) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(Hash);
    }

    private static RequestSpecification generada(RequestSpecification request) {
        return request.body(body).headers(headersRequest).params(parametrosRequest);
    }
}
