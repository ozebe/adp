package main;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;

/**
 * Classe para realizar a conversão das Strings fornecidas para os tipos
 * necessários, atualmente é recomendada para analise de dados de temperatura ou
 * pressão.
 *
 * @author Wesley Ozebe
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class Converter {

    private List l = new ArrayList();
    private List<Day> datas = new ArrayList<Day>();
    private List<Hour> horas = new ArrayList<Hour>();
    private List<Minute> minutos = new ArrayList<Minute>();

    //retorna uma lista com o padrao passado
    /**
     * Conversão de dados para list
     *
     * @author Wesley Ozebe
     * @param regex String - A ser pesquisada
     * @param dado String - Sem modificações onde será realizada a pesquisa.
     * @return List - para acessar seus valores utilize o getL()
     */
    Converter(String dado, String regex) {
        c(dado, regex);
    }

    /**
     * Conversão de dados para list de org.jfree.data.time.Day
     *
     * @author Wesley Ozebe
     * @param regex String - A ser pesquisada
     * @param dado String - Sem modificações onde será realizada a pesquisa.
     * @param data Boolean - se verdadeiro ele retornará no padrão de Data
     * @return List - para acessar seus valores utilize o getDatas(), ex:
     * 28-maio-2019
     */
    Converter(String dado, String regex, boolean data) {
        if (data == true) {
            cDate(c(dado, regex));
        } else if (data == false) {
            c(dado, regex);
        }
    }

    /**
     * Conversão de dados para list de org.jfree.data.time.Hour
     *
     * @author Wesley Ozebe
     * @param regex String - A ser pesquisada
     * @param dado String - Sem modificações onde será realizada a pesquisa.
     * @param hora Boolean - se verdadeiro ele retornará no padrão de Hora
     * @return List - para acessar seus valores utilize o getHoras(), ex de
     * retorno: [17,28/5/2019]
     */
    Converter(String dado, String h, String d, boolean hora) {
        if (hora == true) {
            List hh = new ArrayList();
            List dd = new ArrayList();
            Converter data2 = new Converter(dado, "data");

            Converter hora2 = new Converter(dado, "hora");

            for (int i = 0; i < data2.getL().size(); i++) {
                dd.add(data2.getL().get(i));
            }

            for (int i = 0; i < hora2.getL().size(); i++) {
                hh.add(hora2.getL().get(i));
            }
            cHour(hh, dd);
        }
    }

    /**
     * Conversão de dados para list de org.jfree.data.time.Minute
     *
     * @author Wesley Ozebe
     * @param regex String - A ser pesquisada
     * @param dado String - Sem modificações onde será realizada a pesquisa.
     * @param hora Boolean - não implica no retorno
     * @param minutos Boolean - se verdadeiro ele retornará no padrão de Minutos
     * @return List - para acessar seus valores utilize o getMinutos(), ex de
     * retorno: Tue May 28 17:35:00 BRT 2019
     */
    Converter(String dado, String h, String d, boolean hora, boolean minutos) {
        if (minutos == true) {
            // cMinute(cHour(c(dado, h), cDate(c(dado, d))), cDate(c(dado, d)));
            List hh = new ArrayList();
            List dd = new ArrayList();
            Converter data2 = new Converter(dado, "data");

            Converter hora2 = new Converter(dado, "hora");

            for (int i = 0; i < data2.getL().size(); i++) {
                dd.add(data2.getL().get(i));
            }

            for (int i = 0; i < hora2.getL().size(); i++) {
                hh.add(hora2.getL().get(i));
            }
            cMinute(hh, dd);
        }
    }

    /**
     * Método para acesso a Lista criada utilizando-se o pattern passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista com os valores selecionados
     */
    public List getL() {
        return l;
    }

    /**
     * Método para acesso a Lista de datas criada utilizando-se o pattern
     * passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de datas org.jfree.data.time.Day no
     * seguinte padrao: DD-MM-AAAA, ex: 28-maio-2019
     */
    public List<Day> getDatas() {
        return datas;
    }

    /**
     * Método para acesso a Lista de horas criada utilizando-se o pattern
     * passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de horas org.jfree.data.time.Hour no
     * seguinte padrao: [HH,DD/MM/AAAA], ex: [17,28/5/2019]
     */
    public List<Hour> getHoras() {
        return horas;
    }

    /**
     * Método para acesso a Lista de minutos e respectivas horas e dias criada
     * utilizando-se o pattern passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de minutos org.jfree.data.time.Minute no
     * seguinte padrao: DIASEMANA MM DD HH:MM:SS TIMEZONE AAAA, ex: Tue May 28
     * 17:35:00 BRT 2019
     */
    public List<Minute> getMinutos() {
        return minutos;
    }

    /**
     * Método para retorno de uma Lista utilizando-se o pattern passado
     *
     * @author Wesley Ozebe
     * @param dado String - String sem modificações onde será realizada a
     * pesquisa.
     * @param regex String - String que irá ser utilizada para pesquisar
     * @return List - Lista com os valores
     */
    private List c(String dado, String regex) {

        dado = dado.replaceAll("\r", "");
        dado = dado.replaceAll("\t", " ");
        dado = dado.replaceAll("\n", "");
        dado = dado.replaceAll(" ", "");
        dado = dado.replaceAll("/", ",");
        String array[];
        array = dado.split(";");
        array = dado.split("=");
        array = dado.split(";");
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (string.matches(".*(?i)" + regex + ".*")) {
                String[] stringDividida = string.split("=");
                l.add(stringDividida[1]);
            }

        }

        return l;
    }

    /**
     * Método para retorno de uma Lista de datas no modelo JFreechart
     * (org.jfree.data.time.Day)
     *
     * @author Wesley Ozebe
     * @param date List - Lista de datas em formato string.
     * @return List - Lista no formato JFreechart Day
     */
    private List cDate(List date) {
        List dias = new ArrayList();
        List meses = new ArrayList();
        List anos = new ArrayList();
        for (int i = 0; i < date.size(); i++) {
            String d = date.get(i).toString();
            d = d.replaceAll("\\s+", "");
            String array[];
            array = d.split(",");
            dias.add(array[0]);
            meses.add(array[1]);
            anos.add(array[2]);

            Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
            datas.add(d01);
        }
        return datas;

    }

    /**
     * Método para retorno de uma Lista de minutos no modelo JFreechart
     * (org.jfree.data.time.Minute)
     *
     * @author Wesley Ozebe
     * @param date List - Lista de datas em formato string.
     * @param hour List - Lista de horas em formato string.
     * @return List - Lista no formato JFreechart Minute
     */
    private List cMinute(List hour, List date) {

        List dias = new ArrayList();
        List meses = new ArrayList();
        List anos = new ArrayList();
        List horasList = new ArrayList();
        List minutosList = new ArrayList();
        for (int i = 0; i < hour.size(); i++) {
            String d = date.get(i).toString();
            d = d.replaceAll("\\s+", "");
            String array[];
            array = d.split(",");
            dias.add(array[0]);
            meses.add(array[1]);
            anos.add(array[2]);

            Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
            String d2 = hour.get(i).toString();
            String array02[];
            array02 = d2.split(":");
            horasList.add(array02[0]);
            minutosList.add(array02[1]);

            Hour h = new Hour(Integer.parseInt(horasList.get(i).toString()), d01);
            Minute m = new Minute(Integer.parseInt(minutosList.get(i).toString()), h);
            minutos.add(m);

        }

        return minutos;
    }

    /**
     * Método para retorno de uma Lista de horas no modelo JFreechart
     * (org.jfree.data.time.Hour)
     *
     * @author Wesley Ozebe
     * @param date List - Lista de datas em formato string.
     * @param hour List - Lista de horas em formato string.
     * @return List - Lista no formato JFreechart Hour
     */
    private List cHour(List hour, List date) {
        List dias = new ArrayList();
        List meses = new ArrayList();
        List anos = new ArrayList();
        List horasList = new ArrayList();
        for (int i = 0; i < hour.size(); i++) {
            String d = date.get(i).toString();
            d = d.replaceAll("\\s+", "");
            String array[];
            array = d.split(",");
            dias.add(array[0]);
            meses.add(array[1]);
            anos.add(array[2]);

            Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
            String d2 = hour.get(i).toString();
            String array02[];
            array02 = d2.split(":");
            horasList.add(array02[0]);

            Hour h = new Hour(Integer.parseInt(horasList.get(i).toString()), d01);
            horas.add(h);

        }

        return horas;
    }

}
