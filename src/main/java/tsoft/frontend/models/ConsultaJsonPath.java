package tsoft.frontend.models;

import net.serenitybdd.rest.SerenityRest;
import tsoft.both.Utility.BaseClass;

public class ConsultaJsonPath {
    String identificadorDeGuardado;
    String valorObtenido;
    String valorEsperado;
    String report;
    String jsonPath;

    public ConsultaJsonPath(String consultaJsonPath) {
        String[] values = consultaJsonPath.split("',last,'");
        String columnaExcelComparar = values[1];

        this.jsonPath = values[0].split("jsonpath'")[1].replace("$.","");
        this.identificadorDeGuardado = (values[2].contains("default")) ? jsonPath : values[2].replace("',end","");
        this.valorObtenido = SerenityRest.lastResponse().getBody().path(jsonPath).toString();
        this.valorEsperado = BaseClass.desdeService(columnaExcelComparar).replace("\n","").replace("\r","");
        this.report = String.format("Query de jsonpath: '%s'\nValor obtenido: '%s'", jsonPath, valorObtenido)
                        +((!valorEsperado.isEmpty()) ? String.format(" - Valor esperado: '%s'", valorEsperado) : "");
    }

    public String getIdentificadorDeGuardado() {
        return identificadorDeGuardado;
    }

    public String getValorObtenido() {
        return valorObtenido;
    }

    public String getValorEsperado() {
        return valorEsperado;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public String getReport() {
        return report;
    }
}
