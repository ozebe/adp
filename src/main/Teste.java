package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wesley
 */
public class Teste {

    static List dias = new ArrayList();
    static List meses = new ArrayList();
    static List anos = new ArrayList();

    public static void main(String[] args) {
        String dado = "Hora = 17:35; data = 28/05/2019; Temperatura = 17; pressao = 870;\n"
                + "Hora = 17:55; data = 28/05/2019; Temperatura = 16; pressao = 870;\n"
                + "\nhora = 18:35; data = 29/05/2019; Temperatura = 18; pressao = 880;\n"
                + "\nhora = 19:00; data = 30/05/2019; Temperatura = 15; pressao = 885;\n"
                + "\nhora = 20:30; data = 31/05/2019; Temperatura = 11; pressao = 890;";
        System.out.println("" + dado);


        Converter data = new Converter(dado, "data", true);
        Converter hora = new Converter(dado, "hora");

        Converter hora2 = new Converter(dado, "hora", "data", true);

        for (int i = 0; i < hora2.getHoras().size(); i++) {
            System.out.println("horas Freechart: " + hora2.getHoras().get(i).toString());

        }

        Converter m = new Converter(dado, "hora", "data", true, true);
        for (int i = 0; i < m.getMinutos().size(); i++) {
            System.out.println("minutos FreeChart: " + m.getMinutos().get(i).toString());

        }

        Converter data2 = new Converter(dado, "data");
        for (int i = 0; i < data2.getL().size(); i++) {
            System.out.println("datas: " + data2.getL().get(i).toString());

        }

        Converter data3 = new Converter(dado, "data", true);
        for (int i = 0; i < data3.getDatas().size(); i++) {
            System.out.println("datas Freechart: " + data3.getDatas().get(i).toString());

        }

    }

}
