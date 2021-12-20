package tsoft.backend.tareas;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import tsoft.backend.objects.imports.ExcelServiceObjetcts;
import tsoft.both.Utility.FileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static tsoft.both.Utility.BaseClass.desdeService;

public class Validar {
    public static Performable elCodigoDeRespuesta() {
        String codigoRespeustaEsperado = desdeService(ExcelServiceObjetcts.COLUMN_CRE);
        if (codigoRespeustaEsperado.equals(""))
            return Task.where("No se ha definido una validacion de código de respuesta");

        Integer statusCodeEsperado = Integer.valueOf(codigoRespeustaEsperado);
        int statusCodeObtenido = SerenityRest.lastResponse().getStatusCode();
        return Task.where("Se valida el codigo de estado en la respuesta",
                Ensure.that(statusCodeObtenido).isEqualTo(statusCodeEsperado));
    }

    public static Performable elContentType() {
        String contentTypeEsperado = desdeService(ExcelServiceObjetcts.COLUMN_VALIDATECTYPE);
        if (contentTypeEsperado.equals("")) {
            return Task.where("No se ha definido una validacion de ContentType");
        }
        String contentTypeObtenido = SerenityRest.lastResponse().getContentType();
        return Task.where("Se valida el contentType en la respuesta", Ensure.that(contentTypeObtenido).contains(contentTypeEsperado));
    }


    public static Performable elSchemadeRespuesta() {
        String schema = desdeService(ExcelServiceObjetcts.COLUMN_VLDEJ);
        Path rutaJson = Paths.get("src/test/resources/schemas/"+ schema );

        if (schema.equals("")) {
            return Task.where("No se ha definido una validacion de Schema");
        }

        if(!Files.exists(rutaJson)) return Task.where("El archivo Json no ha podido ser encontrado", actor -> {
            Serenity.throwExceptionsImmediately();
        });

        if(SerenityRest.lastResponse().getContentType().contains("application/json")){
            return Task.where("Se valida el contentType en la respuesta", actor -> {
                SerenityRest.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/"+schema));
                try {
                    Serenity.recordReportData().withTitle("Schema Validado").fromFile(rutaJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return Task.where("La respuesta de la consulta no es validable por no ser de tipo Json");
    }
}
