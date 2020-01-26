package br.com.bycrr.v3.appclientevip.dataModel;

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
  private static final String TABELA = "clientePJ";
  private static final String ID = "id";
  private static final String FK = "clientePFID";
  private static final String CNPJ = "cnpj";
  private static final String RAZAO_SOCIAL = "razaoSocial";
  private static final String DATA_ABERTURA = "dataAbertura";
  private static final String SIMPLES_NACIONAL = "simplesNacional";
  private static final String MEI = "mei";
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
    query += CNPJ + " INTEGER, ";
    query += RAZAO_SOCIAL + " TEXT, ";
    query += DATA_ABERTURA + " TEXT, ";
    query += SIMPLES_NACIONAL + " INTEGER, ";
    query += MEI + " INTEGER, ";
    query += DATA_INCLUSAO + " TEXT, ";
    query += DATA_ALTERACAO + " TEXT, ";
    query += " FOREIGN KEY(" + FK +") REFERENCES clientePF(id) ";
    query += ")";

    return query;
  }
}
