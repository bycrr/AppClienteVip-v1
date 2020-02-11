package br.com.bycrr.v5.appclientevip.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v5.appclientevip.dataModel.ClienteDataModel;
import br.com.bycrr.v5.appclientevip.dataModel.ClientePFDataModel;
import br.com.bycrr.v5.appclientevip.dataModel.ClientePJDataModel;
import br.com.bycrr.v5.appclientevip.model.Cliente;
import br.com.bycrr.v5.appclientevip.model.ClientePF;
import br.com.bycrr.v5.appclientevip.model.ClientePJ;

public class AppDataBase extends SQLiteOpenHelper {

  private static final String DB_NAME = "clienteDB.sqlite";
  public static final int DB_VERSION = 1;
  SQLiteDatabase db;
  Cursor cursor;

  public AppDataBase(@Nullable Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    db = getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // criar as tabelas
    /*String sqlTabelaCliente = "CREATE TABLE cliente (\n" +
      "    id      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
      "    nome    TEXT,\n" +
      "    email   TEXT,\n" +
      "    status  INTEGER,\n" +
      "    datainc TEXT,\n" +
      "    dataalt TEXT\n" +
      ")";*/

    try {
      //db.execSQL(sqlTabelaCliente);
      db.execSQL(ClienteDataModel.gerarTabela());
      //Log.i(AppUtil.LOG_APP, "TB Cliente: " + sqlTabelaCliente);
      Log.i(AppUtil.LOG_APP, "TB Cliente: " + ClienteDataModel.gerarTabela());

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro TB Cliente: " + e.getMessage());
    }

    try {
      db.execSQL(ClientePFDataModel.gerarTabela());
      Log.i(AppUtil.LOG_APP, "TB ClientePF: " + ClientePFDataModel.gerarTabela());

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro TB ClientePF: " + e.getMessage());
    }

    try {
      db.execSQL(ClientePJDataModel.gerarTabela());
      Log.i(AppUtil.LOG_APP, "TB ClientePJ: " + ClientePJDataModel.gerarTabela());

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro TB ClientePJ: " + e.getMessage());
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // atualizar o banco de dados e tabelas

  }

  public boolean insert(String tabela, ContentValues dados) {
    boolean sucesso = true;

    try {
      sucesso = db.insert(tabela, null, dados) > 0;
      Log.i(AppUtil.LOG_APP, "Insert ok na " + tabela);

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no insert da " + tabela + ": " + e.getMessage());
    }
    return sucesso;
  }

  public boolean update(String tabela, ContentValues dados) {
    boolean sucesso = true;

    try {
      int id = dados.getAsInteger("id");
      sucesso = db.update(tabela, dados,"id=?", new String[]{Integer.toString(id)}) > 0;
      Log.i(AppUtil.LOG_APP, "Update ok na " + tabela);

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no update da " + tabela + ": " + e.getMessage());
    }
    return sucesso;
  }

  public boolean delete(String tabela, int id) {
    boolean sucesso = true;

    try {
      sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;
      Log.i(AppUtil.LOG_APP, "Delete ok na " + tabela);

    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no delete da " + tabela + ": " + e.getMessage());
    }
    return sucesso;
  }

  public List<Cliente> listClientes(String tabela) {
    List<Cliente> listClientes = new ArrayList<>();
    Cliente cliente;
    // select no DB
    String sql = "SELECT * FROM " + tabela;

    try {
      cursor = db.rawQuery(sql, null);

      if (cursor.moveToFirst()) {
        do {
          cliente = new Cliente();
          cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
          cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
          cliente.setSobrenome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRENOME)));
          cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
          cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
          cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA))==1);
          listClientes.add(cliente);
        } while (cursor.moveToNext());

        Log.i(AppUtil.LOG_APP, "List ok na " + tabela);
      }
    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no list da " + tabela + ": " + e.getMessage());
    }
    return listClientes;
  }

  public List<ClientePF> listClientesPF(String tabela) {
    List<ClientePF> listClientes = new ArrayList<>();
    ClientePF clientePF;
    String sql = "SELECT * FROM " + tabela;

    try {
      cursor = db.rawQuery(sql, null);

      if (cursor.moveToFirst()) {
        do {
          clientePF = new ClientePF();
          clientePF.setId(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.ID)));
          clientePF.setClienteID(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.FK)));
          clientePF.setNomeCompleto(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.NOME_COMPLETO)));
          clientePF.setCpf(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.CPF)));
          listClientes.add(clientePF);
        } while (cursor.moveToNext());

        Log.i(AppUtil.LOG_APP, "List ok na " + tabela);
      }
    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no list da " + tabela + ": " + e.getMessage());
    }
    return listClientes;
  }

  public List<ClientePJ> listClientesPJ(String tabela) {
    List<ClientePJ> listClientes = new ArrayList<>();
    ClientePJ clientePJ;
    String sql = "SELECT * FROM " + tabela;

    try {
      cursor = db.rawQuery(sql, null);

      if (cursor.moveToFirst()) {
        do {
          clientePJ = new ClientePJ();
          clientePJ.setId(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.ID)));
          clientePJ.setClientePFID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
          clientePJ.setCpf(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.CNPJ)));
          clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.RAZAO_SOCIAL)));
          clientePJ.setDataAbertura(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.DATA_ABERTURA)));
          clientePJ.setSimplesNacional(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.SIMPLES_NACIONAL))==1);
          clientePJ.setMei(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.MEI))==1);
          listClientes.add(clientePJ);
        } while (cursor.moveToNext());

        Log.i(AppUtil.LOG_APP, "List ok na " + tabela);
      }
    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no list da " + tabela + ": " + e.getMessage());
    }
    return listClientes;
  }

  public int getLastPK(String tabela) {
    String sql = "SELECT seq FROM sqlite_sequence WHERE name = '" + tabela + "'";

    try {
      cursor = db.rawQuery(sql, null);

      if (cursor.moveToFirst()) {
        do {
          return cursor.getInt(cursor.getColumnIndex("seq"));

        } while (cursor.moveToNext());
      }
    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro no getLastPK da " + tabela + ": " + e.getMessage());
    }
    return -1;
  }

  public Cliente getClienteByID(String tabela, Cliente obj) {
    Cliente cliente = new Cliente();
    String sql = "SELECT * FROM " + tabela + " WHERE ID = " + obj.getId();

    try {
      cursor = db.rawQuery(sql, null);

      if (cursor.moveToNext()) {
        cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
        cliente.setSobrenome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRENOME)));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
        cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
        cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA))==1);
      }
    } catch (SQLException e) {
      Log.e(AppUtil.LOG_APP, "Erro getClienteByID " + obj.getId());
      Log.e(AppUtil.LOG_APP, "Erro "+ e.getMessage());
    }
    return cliente;
  }
}
