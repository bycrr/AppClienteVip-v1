package br.com.bycrr.v5.appclientevip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.bycrr.v5.appclientevip.api.AppDataBase;
import br.com.bycrr.v5.appclientevip.dataModel.ClientePFDataModel;
import br.com.bycrr.v5.appclientevip.dataModel.ClientePJDataModel;
import br.com.bycrr.v5.appclientevip.model.ClientePF;
import br.com.bycrr.v5.appclientevip.model.ClientePJ;

public class ClientePJController extends AppDataBase {

  private static final String TABELA = ClientePJDataModel.TABELA;
  private ContentValues dados;

  public ClientePJController(@Nullable Context context) {
    super(context);
  }

  public boolean incluir(ClientePJ cliente) {
    dados = new ContentValues();
    dados.put(ClientePJDataModel.FK, cliente.getClientePFID());
    dados.put(ClientePJDataModel.CNPJ, cliente.getCnpj());
    dados.put(ClientePJDataModel.RAZAO_SOCIAL, cliente.getRazaoSocial());
    dados.put(ClientePJDataModel.DATA_ABERTURA, cliente.getDataAbertura());
    dados.put(ClientePJDataModel.SIMPLES_NACIONAL, cliente.isSimplesNacional());
    dados.put(ClientePJDataModel.MEI, cliente.isMei());
    return insert(TABELA, dados);
  }

  public boolean alterar(ClientePJ cliente) {
    dados = new ContentValues();
    dados.put(ClientePJDataModel.ID, cliente.getId());
    dados.put(ClientePJDataModel.CNPJ, cliente.getCnpj());
    dados.put(ClientePJDataModel.RAZAO_SOCIAL, cliente.getRazaoSocial());
    dados.put(ClientePJDataModel.DATA_ABERTURA, cliente.getDataAbertura());
    dados.put(ClientePJDataModel.SIMPLES_NACIONAL, cliente.isSimplesNacional());
    dados.put(ClientePJDataModel.MEI, cliente.isMei());
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

  public ClientePJ getClientePJByFK(int idFK) {
    // idFK é a chave primária da tabela Cliente (id)
    return getClientePJByFK(ClientePJDataModel.TABELA, idFK);
  }
}
