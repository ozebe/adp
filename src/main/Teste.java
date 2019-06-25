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
                + "\nhora = 19:20; data = 30/05/2019; Temperatura = 15; pressao = 885;\n"
                + "\nhora = 20:31; data = 31/05/2019; Temperatura = 11; pressao = 890;";
//        System.out.println("" + dado);
//        Converter m = new Converter(dado, "hora", "data", true, true);
//        for (int i = 0; i < m.getMinutos().size(); i++) {
//            System.out.println("horas: c1 " + m.getMinutos().get(i).toString());
//
//        }
//
//        Converter2 c = new Converter2.Builder(dado, "hora").converter().build();
//        for (int i = 0; i < c.getMinutos().size(); i++) {
//            System.out.println("horas: c2 " + c.getHoras().get(i).toString());
//            
//        }
//        
//        for (int i = 0; i < c.getL().size(); i++) {
//            System.out.println("" + c.getL().get(i).toString());
//            
//        }

        //retorna lista de data JFreeChart
        Converter2 c3 = new Converter2.Builder(dado, "data").converterData().build();
        for (int i = 0; i < c3.getDatas().size(); i++) {
            System.out.println("datas: " + c3.getDatas().get(i).toString());

        }

        //retorna lista de hora JFreeChart
        Converter2 h = new Converter2.Builder(dado, "hora").converterHora().build();
        for (int i = 0; i < h.getHoras().size(); i++) {
            System.out.println("horas :  " + h.getHoras().get(i).toString());

        }

        //retonra lista de minuto JFreeChart
        Converter2 m = new Converter2.Builder(dado, "hora").converterMinutos().build();
        for (int i = 0; i < m.getMinutos().size(); i++) {
            System.out.println("minutos :  " + m.getMinutos().get(i).toString());

        }

    }

}
