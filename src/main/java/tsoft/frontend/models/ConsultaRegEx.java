package tsoft.frontend.models;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;
import tsoft.both.Utility.BaseClass;
import tsoft.both.Utility.StringHandler;

public class ConsultaRegEx {

    String identificadorDeGuardado;
    String valorObtenido;
    String valorEsperado;
    String report;

    public ConsultaRegEx(@NotNull String consultasRegex) {

        String[] values = consultasRegex.split("',last,'");
        String busquedaRegex = values[0].split("regex'")[1];
        int grupo = NumberUtils.toInt(values[1], 1);
        String columnaExcelComparar = values[2];
        String enfoque = (values[3].equals("")) ? "BODY" : values[3];
        String origen = definirOrigen(enfoque);

        this.valorObtenido = StringHandler.regExprExtractor(busquedaRegex,origen,grupo);

        this.valorEsperado = BaseClass.desdeService(columnaExcelComparar);

        this.report = String.format("Expresión regular: '%s' - grupo de busqueda: '%s'\n" +
                                    "Buscado en: '%s'\n" +"valor obtenido: '%s'"
                                    ,busquedaRegex,grupo,enfoque,valorObtenido)
                                    +((!valorEsperado.isEmpty()) ?
                                    String.format(" - Valor esperado: '%s'", valorEsperado) : "");

        this.identificadorDeGuardado = values[4].replace("',end","");
    }

    protected String definirOrigen(@NotNull String enfoque){
        switch (enfoque) {
            case "BODY":
                return SerenityRest.lastResponse().getBody().asString();
            case "HEADERS":
                return SerenityRest.lastResponse().getHeaders().toString();
            case "COOKIES":
                return SerenityRest.lastResponse().getCookies().toString();
            default:
                return "No existe origen valido";
        }
    }

    public String getReport() {
        return report;
    }

    public String getIdentificadorDeGuardado() {
        return identificadorDeGuardado;
    }

    public String getValorEsperado() {
        return valorEsperado;
    }

    public String getValorObtenido() {
        return valorObtenido;
    }
}
