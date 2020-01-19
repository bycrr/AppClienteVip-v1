package br.com.bycrr.v1.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.controller.ClienteController;
import br.com.bycrr.v1.appclientevip.model.Cliente;

public class LoginActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente cliente;
  boolean isFormularioOk, isLembrarSenha;

  // criar variáveis de tela
  TextView txtRecuperarSenha, txtLerPolitica;
  EditText editEmail, editSenha;
  CheckBox chLembrar;
  Button btnAcessar, btnSejaVip;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initFormulario();

    btnAcessar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {

          if(validarDadosUsuario()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;

          } else {
            Toast.makeText(getApplicationContext(), "Verifique os dados...", Toast.LENGTH_LONG).show();
          }

        }
      }
    });
    txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Carregando tela de recuperação de senha...", Toast.LENGTH_LONG).show();
      }
    });

    txtLerPolitica.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Carregando tela com a política de privacidade...", Toast.LENGTH_LONG).show();
      }
    });
  }

  private boolean validarDadosUsuario() {
    return ClienteController.validarDadosCliente();
  }

  private boolean validarFormulario() {
    boolean retorno = true;

    if(TextUtils.isEmpty((editEmail.getText().toString()))) {
      editEmail.setError("*");
      editEmail.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editSenha.getText().toString())) {
      editSenha.setError("*");
      editSenha.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private void initFormulario() {
    txtRecuperarSenha = findViewById(R.id.txtRecuperarSenha);
    txtLerPolitica = findViewById(R.id.txtLerPolitica);
    editEmail = findViewById(R.id.editEmail);
    editSenha = findViewById(R.id.editSenha);
    chLembrar = findViewById(R.id.ckLembrar);
    btnAcessar = findViewById(R.id.btnAcessar);
    btnSejaVip = findViewById(R.id.btnSejaVip);
    isFormularioOk = false;
  }

  public void lembrarSenha(View view) {
    isLembrarSenha = chLembrar.isChecked();
    int teste = 0;
  }
}
