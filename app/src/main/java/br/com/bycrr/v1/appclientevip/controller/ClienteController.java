package br.com.bycrr.v1.appclientevip.controller;

import br.com.bycrr.v1.appclientevip.model.Cliente;

public class ClienteController {
  public static boolean validarDadosCliente(Cliente cliente, String email, String senha) {
    boolean retorno = (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha));
    return retorno;
  }

  public static Cliente getClienteFake() {
    Cliente fake = new Cliente();
    fake.setPrimeiroNome("Claudio");
    fake.setSobrenome("Rosa");
    fake.setEmail("claudio@teste.com");
    fake.setSenha("12345");
    fake.setPessoaFisica(true);
    return fake;
  }
}
