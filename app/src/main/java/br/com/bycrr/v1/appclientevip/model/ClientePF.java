package br.com.bycrr.v1.appclientevip.model;

public class ClientePF extends Cliente {
  private String cpf;
  private String nomeCompleto;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }
}
