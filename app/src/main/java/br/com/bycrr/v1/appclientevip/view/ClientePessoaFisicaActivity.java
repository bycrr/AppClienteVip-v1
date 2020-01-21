package br.com.bycrr.v1.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.api.AppUtil;
import br.com.bycrr.v1.appclientevip.model.Cliente;
import br.com.bycrr.v1.appclientevip.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente novoVip;
  ClientePF novoClientePF;
  private SharedPreferences preferences;

  // criar variáveis de tela
  EditText editCPF, editNomeCompleto;
  Button btnSalvarContinuar, btnVoltar, btnCancelar;
  boolean isFormularioOk;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cliente_pessoa_fisica);
    initFormulario();

    btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {
          novoClientePF.setCpf(editCPF.getText().toString());
          novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());
          salvarSharedPreferences();
          Intent intent = new Intent(ClientePessoaFisicaActivity.this, LoginActivity.class);
          startActivity(intent);
        }
      }
    });

    btnVoltar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ClientePessoaFisicaActivity.this, LoginActivity.class);
        startActivity(intent);
      }
    });
  }

  private boolean validarFormulario() {
    boolean retorno = true;

    if(TextUtils.isEmpty((editCPF.getText().toString()))) {
      editCPF.setError("*");
      editCPF.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
      editNomeCompleto.setError("*");
      editNomeCompleto.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private void initFormulario() {
    editCPF = findViewById(R.id.editCPF);
    editNomeCompleto = findViewById(R.id.editNomeCompleto);
    btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
    btnCancelar = findViewById(R.id.btnCancelar);
    btnVoltar = findViewById(R.id.btnVoltar);
    isFormularioOk = false;
    novoClientePF = new ClientePF();
    novoVip = new Cliente();
    restaurarSharedPreferences();
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    //dados.putString("primeiroNome", novoVip.getPrimeiroNome().toString());
    //dados.putString("sobrenome", novoVip.getSobrenome().toString());
    //dados.putBoolean("pessoaFisica", novoVip.getPessoaFisica());
    //dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    //isPessoaFisica = preferences.getBoolean("pessoaFisica", false);
  }
}
