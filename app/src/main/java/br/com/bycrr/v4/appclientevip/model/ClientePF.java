package br.com.bycrr.v4.appclientevip.model;

public class ClientePF extends Cliente {
  private int ID;
  private int clienteID;
  private String cpf;
  private String nomeCompleto;

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public int getClienteID() {
    return clienteID;
  }

  public void setClienteID(int clienteID) {
    this.clienteID = clienteID;
  }

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
