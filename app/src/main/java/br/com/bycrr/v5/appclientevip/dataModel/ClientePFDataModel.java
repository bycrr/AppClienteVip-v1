package br.com.bycrr.v5.appclientevip.dataModel;

// MOR - Modelo Objeto Relacional

public class ClientePFDataModel {
  /**
   *   private int fk;
   *   private String cpf;
   *   private String nomeCompleto;
   */
  public static final String TABELA = "clientePF";
  public static final String ID = "id";
  public static final String FK = "clienteID";
  public static final String CPF = "cpf";
  public static final String NOME_COMPLETO = "nomeCompleto";
  private static final String DATA_INCLUSAO = "dataInclusao";
  private static final String DATA_ALTERACAO = "dataAlteracao";
  private static String query;

  /**
   * CREATE TABLE clientePF (
   *     id            INTEGER PRIMARY KEY AUTOINCREMENT,
   *     clienteID     INTEGER,
   *     cpf           TEXT,
   *     nomeCompleto  TEXT,
   *     dataInclusao  TEXT,
   *     dataAlteracao TEXT,
   *     FOREIGN KEY (
   *         clienteID
   *     )
   *     REFERENCES cliente (id)
   * );
   */

  public static String gerarTabela() {
    query = "CREATE TABLE " + TABELA + " (";
    query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    query += FK + " INTEGER, ";
    query += CPF + " TEXT, ";
    query += NOME_COMPLETO + " TEXT, ";
    query += DATA_INCLUSAO + " datetime default current_timestamp, ";
    query += DATA_ALTERACAO + " datetime default current_timestamp, ";
    query += " FOREIGN KEY(" + FK +") REFERENCES cliente(id) ";
    query += ")";

    return query;
  }
}
