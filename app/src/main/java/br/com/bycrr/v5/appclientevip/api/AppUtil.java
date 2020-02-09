package br.com.bycrr.v5.appclientevip.api;

import java.util.Calendar;
import java.util.InputMismatchException;

/**
 * Classe de apoio contendo métodos que podem
 * ser reutilizados neste projeto
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

  public static boolean isCNPJ(String CNPJ) {
    // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
      CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
      CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
      CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
      CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
      (CNPJ.length() != 14))
      return (false);

    char dig13, dig14;
    int sm, i, r, num, peso;

    try {

      sm = 0;
      peso = 2;
      for (i = 11; i >= 0; i--) {
        num = (int) (CNPJ.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
          peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
        dig13 = '0';
      else dig13 = (char) ((11 - r) + 48);


      sm = 0;
      peso = 2;
      for (i = 12; i >= 0; i--) {
        num = (int) (CNPJ.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
          peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
        dig14 = '0';
      else dig14 = (char) ((11 - r) + 48);

      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
        return (true);
      else return (false);
    } catch (InputMismatchException erro) {
      return (false);
    }
  }

  public static boolean isCPF(String CPF) {
    if (CPF.equals("00000000000") ||
      CPF.equals("11111111111") ||
      CPF.equals("22222222222") || CPF.equals("33333333333") ||
      CPF.equals("44444444444") || CPF.equals("55555555555") ||
      CPF.equals("66666666666") || CPF.equals("77777777777") ||
      CPF.equals("88888888888") || CPF.equals("99999999999") ||
      (CPF.length() != 11))
      return (false);

    char dig10, dig11;
    int sm, i, r, num, peso;

    try {

      sm = 0;
      peso = 10;
      for (i = 0; i < 9; i++) {

        num = (int) (CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
        dig10 = '0';
      else dig10 = (char) (r + 48);


      sm = 0;
      peso = 11;
      for (i = 0; i < 10; i++) {
        num = (int) (CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
        dig11 = '0';
      else dig11 = (char) (r + 48);

      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
        return (true);
      else return (false);
    } catch (InputMismatchException erro) {
      return (false);
    }
  }

  public static String mascaraCNPJ(String CNPJ) {

    String retorno;

    retorno =(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
      CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
      CNPJ.substring(12, 14));

    return  retorno;
  }

  public static String mascaraCPF(String CPF) {
    return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
      CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
  }
}
