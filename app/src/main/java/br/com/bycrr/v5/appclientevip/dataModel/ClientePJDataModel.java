package br.com.bycrr.v5.appclientevip.dataModel;

// MOR - Modelo Objeto Relacional

public class ClientePJDataModel {
  /**
   *   private int fk;
   *   private String cnpj;
   *   private String razaoSocial;
   *   private String dataAbertura;
   *   private Boolean simplesNacional;
   *   private Boolean mei;
   */
  public static final String TABELA = "clientePJ";
  public static final String ID = "id";
  public static final String FK = "clientePFID";
  public static final String CNPJ = "cnpj";
  public static final String RAZAO_SOCIAL = "razaoSocial";
  public static final String DATA_ABERTURA = "dataAbertura";
  public static final String SIMPLES_NACIONAL = "simplesNacional";
  public static final String MEI = "mei";
  private static final String DATA_INCLUSAO = "dataInclusao";
  private static final String DATA_ALTERACAO = "dataAlteracao";
  private static String query;

  /**
   * CREATE TABLE clientePJ (
   *     id              INTEGER PRIMARY KEY AUTOINCREMENT,
   *     clientePFID     INTEGER,
   *     cnpj            INTEGER,
   *     razaoSocial     TEXT,
   *     dataAbertura    TEXT,
   *     simplesNacional INTEGER,
   *     mei             INTEGER,
   *     dataInclusao    TEXT,
   *     dataAlteracao   TEXT,
   *     FOREIGN KEY (
   *         clientePFID
   *     )
   *     REFERENCES clientePF (id)
   * );
   */

  public static String gerarTabela() {
    query = "CREATE TABLE " + TABELA + " (";
    query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    query += FK + " INTEGER, ";
    query += CNPJ + " TEXT, ";
    query += RAZAO_SOCIAL + " TEXT, ";
    query += DATA_ABERTURA + " TEXT, ";
    query += SIMPLES_NACIONAL + " INTEGER, ";
    query += MEI + " INTEGER, ";
    query += DATA_INCLUSAO + " datetime default current_timestamp, ";
    query += DATA_ALTERACAO + " datetime default current_timestamp, ";
    query += " FOREIGN KEY(" + FK +") REFERENCES clientePF(id) ";
    query += ")";

    return query;
  }
}
