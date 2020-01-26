package br.com.bycrr.v2.appclientevip.api;

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

import br.com.bycrr.v2.appclientevip.dataModel.ClienteDataModel;
import br.com.bycrr.v2.appclientevip.dataModel.ClientePFDataModel;
import br.com.bycrr.v2.appclientevip.dataModel.ClientePJDataModel;
import br.com.bycrr.v2.appclientevip.model.Cliente;

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

  public List<Cliente> list(String tabela) {
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
}
