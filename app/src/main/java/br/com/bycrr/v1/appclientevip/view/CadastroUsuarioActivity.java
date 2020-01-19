package br.com.bycrr.v1.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.bycrr.v1.appclientevip.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

  // criar variáveis
  Button btnCadastro;
  EditText editNome;
  EditText editEmail;
  EditText editSenhaA;
  EditText editSenhaB;
  CheckBox ckTermo;
  boolean isFormularioOk;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_usuario);

    // inicializar o formuário
    initFormulario();

    // obter o evento do botão cadastrar
    btnCadastro.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        isFormularioOk = true;

        // validar os dados digitados
        if (TextUtils.isEmpty(editNome.getText().toString())) {
          editNome.setError("*");
          editNome.requestFocus();
          isFormularioOk = false;
        }
        if (TextUtils.isEmpty(editEmail.getText().toString())) {
          editEmail.setError("*");
          editEmail.requestFocus();
          isFormularioOk = false;
        }
        if (TextUtils.isEmpty(editSenhaA.getText().toString())) {
          editSenhaA.setError("*");
          editSenhaA.requestFocus();
          isFormularioOk = false;
        }
        if (TextUtils.isEmpty(editSenhaB.getText().toString())) {
          editSenhaB.setError("*");
          editSenhaB.requestFocus();
          isFormularioOk = false;
        }
        if (!ckTermo.isChecked()) {
          isFormularioOk = false;
        }
        // depois de tudo ok, mudar de tela
        if (isFormularioOk) {

          if (!validarSenha()) {
            editSenhaA.setError("*");
            editSenhaB.setError("b");
            editSenhaB.requestFocus();
            Toast.makeText(getApplicationContext(),"As senhas digitadas não conferem!", Toast.LENGTH_LONG).show();

          } else {
            Intent iMenuPrincipal = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
            startActivity(iMenuPrincipal);
          }
        }
      }
    });
  }

  private void initFormulario() {
    btnCadastro = findViewById(R.id.btnCadastro);
    editNome = findViewById(R.id.editNome);
    editEmail = findViewById(R.id.editEmail);
    editSenhaA = findViewById(R.id.editSenhaA);
    editSenhaB = findViewById(R.id.editSenhaB);
    ckTermo = findViewById(R.id.ckTermo);
    isFormularioOk = false;
  }

  public void validarTermo(View view) {
    if(!ckTermo.isChecked()) {
      Toast.makeText(getApplicationContext(),"É necessário aceitar os termpos de uso para continuar o cadastro", Toast.LENGTH_LONG).show();
    }
  }

  public boolean validarSenha() {
    boolean retorno = false;
    int senhaA, senhaB;
    senhaA = Integer.parseInt(editSenhaA.getText().toString());
    senhaB = Integer.parseInt(editSenhaB.getText().toString());
    retorno = (senhaA == senhaB);
    return retorno;
  }
}
