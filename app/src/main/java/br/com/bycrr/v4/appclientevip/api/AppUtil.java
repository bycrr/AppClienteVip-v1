package br.com.bycrr.v4.appclientevip.api;

import java.util.Calendar;

/**
 * Classe de apoio contendo métodos que podem
 * ser reutilizados em todo o projeto
 * Criado por CRR - 12/01/2020
 * versão v1
 */
public class AppUtil {

  public static final int TIME_SPLASH = 5 * 1000;
  public static final String PREF_APP = "app_cliente_vip_pref";
  public static final String LOG_APP = "CLIENTE_LOG";
  public static final String URL_IMG_BACKGROUND = "http://bit.ly/daaziImgBackground";
  public static final String URL_IMG_LOGO = "http://bit.ly/daaziImgLogo";

  /**
   *
   * @return devolve a data atual
   */
  public static String getDataAtual(){
    String dataAtual = "00/00/0000";
    String dia, mes, ano;

    try {
      Calendar calendar = Calendar.getInstance();
      dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
      mes = String.valueOf(calendar.get(Calendar.MONTH)+1);
      ano = String.valueOf(calendar.get(Calendar.YEAR));
      //dia = (Calendar.DAY_OF_MONTH < 10) ? "0"+dia : dia;
      //dia = dia.substring(1,2);
      mes = ((Calendar.MONTH)+1 < 10) ? "0"+mes : mes;
      dataAtual = dia + "/" + mes + "/" + ano;

    } catch(Exception e) {
      //Toast.makeText(getApplicationContext(),"Erro na captura da data.",Toast.LENGTH_SHORT).show();
    }
    return dataAtual;
  }

  /**
   *
   * @return devolve a hora atual
   */
  public static String getHoraAtual(){
    String hora, minuto, segundo;
    String horaAtual = "00:00:00";

    try {
      Calendar calendar = Calendar.getInstance();
      int iHora = calendar.get(Calendar.HOUR_OF_DAY);
      int iMinuto = calendar.get(Calendar.MINUTE);
      int iSegundo = calendar.get(Calendar.SECOND);
      hora = (iHora <= 9) ? "0"+iHora : Integer.toString(iHora);
      minuto = (iMinuto <= 9) ? "0"+iMinuto : Integer.toString(iMinuto);
      segundo = (iSegundo <= 9) ? "0"+iSegundo : Integer.toString(iSegundo);
      horaAtual = hora + ":" + minuto + ":" + segundo;

    } catch (Exception e) {

    }
    return horaAtual;
  }
}
