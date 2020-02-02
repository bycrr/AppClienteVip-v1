package br.com.bycrr.v4.appclientevip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.bycrr.v4.appclientevip.api.AppDataBase;
import br.com.bycrr.v4.appclientevip.dataModel.ClienteDataModel;
import br.com.bycrr.v4.appclientevip.model.Cliente;

public class ClienteController extends AppDataBase {

  private static final String TABELA = ClienteDataModel.TABELA;
  private ContentValues dados;

  public ClienteController(@Nullable Context context) {
    super(context);
  }

  public boolean incluir(Cliente cliente) {
    dados = new ContentValues();
    dados.put(ClienteDataModel.PRIMEIRO_NOME, cliente.getPrimeiroNome());
    dados.put(ClienteDataModel.SOBRENOME, cliente.getSobrenome());
    dados.put(ClienteDataModel.EMAIL, cliente.getEmail());
    dados.put(ClienteDataModel.SENHA, cliente.getSenha());
    dados.put(ClienteDataModel.PESSOA_FISICA, cliente.getPessoaFisica());
    return insert(TABELA, dados);
  }

  public boolean alterar(Cliente cliente) {
    dados = new ContentValues();
    dados.put(ClienteDataModel.ID, cliente.getId());
    dados.put(ClienteDataModel.PRIMEIRO_NOME, cliente.getPrimeiroNome());
    dados.put(ClienteDataModel.SOBRENOME, cliente.getSobrenome());
    dados.put(ClienteDataModel.EMAIL, cliente.getEmail());
    dados.put(ClienteDataModel.SENHA, cliente.getSenha());
    dados.put(ClienteDataModel.PESSOA_FISICA, cliente.getPessoaFisica());
    return update(TABELA, dados);
  }

  public boolean excluir(Cliente cliente) {
    return delete(TABELA, cliente.getId());
  }

  public List<Cliente> listar() {
    return listClientes(TABELA);
  }

  public int getUltimoId() {
    return getLastPK(TABELA);
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
