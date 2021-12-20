package tsoft.frontend.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import tsoft.frontend.objects.targets.CarritoCompras;

public class preciosCarrito {

    public static Question<Double> unitariosSuman() {

        return Text.ofEach(CarritoCompras.TOTALES_UNITARIOS)
                .mapEach(String::trim)
                .mapEach( e -> e.replace("$",""))
                .mapEach( e -> e.replace(",",""))
                .mapEach(Double::valueOf)
                .map(e -> e.stream().reduce(0.0,Double::sum))
                ;
    }

    public static Question<Double> subtotalCompra() {

        return Text.of(CarritoCompras.SUBTOTAL_COMPRA)
                .map(String::trim)
                .map( e -> e.replace("$",""))
                .map( e -> e.replace(",",""))
                .map(Double::valueOf)
                ;
    }
}