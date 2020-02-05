package br.com.bycrr.v4.appclientevip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.bycrr.v4.appclientevip.api.AppDataBase;
import br.com.bycrr.v4.appclientevip.dataModel.ClientePFDataModel;
import br.com.bycrr.v4.appclientevip.dataModel.ClientePJDataModel;
import br.com.bycrr.v4.appclientevip.model.ClientePF;
import br.com.bycrr.v4.appclientevip.model.ClientePJ;

public class ClientePJController extends AppDataBase {

  private static final String TABELA = ClientePJDataModel.TABELA;
  private ContentValues dados;

  public ClientePJController(@Nullable Context context) {
    super(context);
  }

  public boolean incluir(ClientePJ cliente) {
    dados = new ContentValues();
    dados.put(ClientePJDataModel.FK, cliente.getClienteID());
    dados.put(ClientePJDataModel.CNPJ, cliente.getCnpj());
    dados.put(ClientePJDataModel.RAZAO_SOCIAL, cliente.getRazaoSocial());
    dados.put(ClientePJDataModel.DATA_ABERTURA, cliente.getDataAbertura());
    dados.put(ClientePJDataModel.SIMPLES_NACIONAL, cliente.getSimplesNacional());
    dados.put(ClientePJDataModel.MEI, cliente.getMei());
    return insert(TABELA, dados);
  }

  public boolean alterar(ClientePJ cliente) {
    dados = new ContentValues();
    dados.put(ClientePJDataModel.ID, cliente.getId());
    dados.put(ClientePJDataModel.CNPJ, cliente.getCnpj());
    dados.put(ClientePJDataModel.RAZAO_SOCIAL, cliente.getRazaoSocial());
    dados.put(ClientePJDataModel.DATA_ABERTURA, cliente.getDataAbertura());
    dados.put(ClientePJDataModel.SIMPLES_NACIONAL, cliente.getSimplesNacional());
    dados.put(ClientePJDataModel.MEI, cliente.getMei());
    return update(TABELA, dados);
  }

  public boolean excluir(ClientePJ cliente) {
    return delete(TABELA, cliente.getId());
  }

  public List<ClientePJ> listar() {
    return listClientesPJ(TABELA);
  }

  public int getUltimoId() {
    return getLastPK(TABELA);
  }
}
