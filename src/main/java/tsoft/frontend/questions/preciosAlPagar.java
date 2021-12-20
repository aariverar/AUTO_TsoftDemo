package tsoft.frontend.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import tsoft.frontend.objects.targets.CheckOut;

public class preciosAlPagar {
    public static Question<Double> precioSubtotal() {

        return Text.of(CheckOut.SUBTOTAL_ORDEN)
                .map(String::trim)
                .map( e -> e.replace("$",""))
                .map( e -> e.replace(",",""))
                .map(Double::valueOf)
                ;
    }
    public static Question<Double> precioEnvio() {

        return Text.of(CheckOut.PAGO_ENVIO)
                .map(String::trim)
                .map( e -> e.replace("$",""))
                .map( e -> e.replace(",",""))
                .map(Double::valueOf)
                ;
    }
    public static Question<Double> precioTotal() {

        return Text.of(CheckOut.TOTAL_ORDEN)
                .map(String::trim)
                .map( e -> e.replace("$",""))
                .map( e -> e.replace(",",""))
                .map(Double::valueOf)
                ;
    }
}
