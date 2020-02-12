package br.com.bycrr.v5.appclientevip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.bycrr.v5.appclientevip.api.AppDataBase;
import br.com.bycrr.v5.appclientevip.dataModel.ClientePFDataModel;
import br.com.bycrr.v5.appclientevip.model.ClientePF;

public class ClientePFController extends AppDataBase {

  private static final String TABELA = ClientePFDataModel.TABELA;
  private ContentValues dados;

  public ClientePFController(@Nullable Context context) {
    super(context);
  }

  public boolean incluir(ClientePF cliente) {
    dados = new ContentValues();
    dados.put(ClientePFDataModel.FK, cliente.getClienteID());
    dados.put(ClientePFDataModel.NOME_COMPLETO, cliente.getNomeCompleto());
    dados.put(ClientePFDataModel.CPF, cliente.getCpf());
    return insert(TABELA, dados);
  }

  public boolean alterar(ClientePF cliente) {
    dados = new ContentValues();
    dados.put(ClientePFDataModel.ID, cliente.getId());
    dados.put(ClientePFDataModel.NOME_COMPLETO, cliente.getNomeCompleto());
    dados.put(ClientePFDataModel.CPF, cliente.getCpf());
    return update(TABELA, dados);
  }

  public boolean excluir(ClientePF cliente) {
    return delete(TABELA, cliente.getId());
  }

  public List<ClientePF> listar() {
    return listClientesPF(TABELA);
  }

  public int getUltimoId() {
    return getLastPK(TABELA);
  }

  public ClientePF getClientePFByFK(int idFK) {
    // idFK é a chave primária da tabela Cliente (id)
    return getClientePFByFK(ClientePFDataModel.TABELA, idFK);
  }

  /*public static boolean validarDadosCliente(Cliente cliente, String email, String senha) {
    boolean retorno = (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha));
    return retorno;
  }*/

  /*public static Cliente getClienteFake() {
    Cliente fake = new Cliente();
    fake.setPrimeiroNome("Claudio");
    fake.setSobrenome("Rosa");
    fake.setEmail("claudio@teste.com");
    fake.setSenha("12345");
    fake.setPessoaFisica(true);
    return fake;
  }*/
}
