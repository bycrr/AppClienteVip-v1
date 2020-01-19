package br.com.bycrr.v1.appclientevip.model;

public class Cliente {
  private int id;
  private String primeiroNome;
  private String sobrenome;
  private String email;
  private String senha;
  private Boolean pessoaFisica;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPrimeiroNome() {
    return primeiroNome;
  }

  public void setPrimeiroNome(String primeiroNome) {
    this.primeiroNome = primeiroNome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Boolean getPessoaFisica() {
    return pessoaFisica;
  }

  public void setPessoaFisica(Boolean pessoaFisica) {
    this.pessoaFisica = pessoaFisica;
  }
}
