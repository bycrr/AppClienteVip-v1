package br.com.bycrr.v4.appclientevip.model;

public class ClientePJ extends ClientePF {
  private int fk;
  private String cnpj;
  private String razaoSocial;
  private String dataAbertura;
  private Boolean simplesNacional;
  private Boolean mei;

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getDataAbertura() {
    return dataAbertura;
  }

  public void setDataAbertura(String dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public Boolean getSimplesNacional() {
    return simplesNacional;
  }

  public void setSimplesNacional(Boolean simplesNacional) {
    this.simplesNacional = simplesNacional;
  }

  public Boolean getMei() {
    return mei;
  }

  public void setMei(Boolean mei) {
    this.mei = mei;
  }
}
