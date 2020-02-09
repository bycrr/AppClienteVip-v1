package br.com.bycrr.v5.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import br.com.bycrr.v5.appclientevip.R;
import br.com.bycrr.v5.appclientevip.api.AppUtil;
import br.com.bycrr.v5.appclientevip.controller.ClienteController;
import br.com.bycrr.v5.appclientevip.model.Cliente;

public class CredencialAcessoActivity extends AppCompatActivity {

  // criar variáveis
  Button btnCadastro;
  EditText editNome, editEmail, editSenhaA, editSenhaB;
  CheckBox ckTermo;
  boolean isFormularioOk;
  SharedPreferences preferences;
  Boolean isPessoaFisica;
  int clienteID;
  Cliente cliente;
  ClienteController clienteController;
  String primeiroNome, sobrenome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_usuario_card);

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
            //Toast.makeText(getApplicationContext(),"As senhas digitadas não conferem!", Toast.LENGTH_LONG).show();

            new FancyAlertDialog.Builder(CredencialAcessoActivity.this)
              .setTitle("ATENÇÃO!")
              .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
              .setMessage("As senhas digitadas não são iguais. Por favor, tente novamente.")
              .setPositiveBtnText("OK")
              .setPositiveBtnBackground(Color.parseColor("#4ECA25"))  //Don't pass R.color.colorvalue
              .setAnimation(Animation.POP)
              .isCancellable(true)
              .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
              .OnPositiveClicked(new FancyAlertDialogListener() {
                @Override
                public void OnClick() {
                }
              })
              .build();

          } else {
            cliente.setEmail(editEmail.getText().toString());
            cliente.setSenha(editSenhaA.getText().toString());
            clienteController.alterar(cliente);
            salvarSharedPreferences();
            Intent iMenuPrincipal = new Intent(CredencialAcessoActivity.this, LoginActivity.class);
            startActivity(iMenuPrincipal);
            finish();
            return;
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
    cliente = new Cliente();
    clienteController = new ClienteController(this);
    restaurarSharedPreferences();
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

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putString("email", editEmail.getText().toString());
    dados.putString("senha", editSenhaA.getText().toString());
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    isPessoaFisica = preferences.getBoolean("pessoaFisica", false);
    clienteID = preferences.getInt("clienteID", -1);
    primeiroNome = preferences.getString("primeiroNome", "erro");
    sobrenome = preferences.getString("sobrenome", "erro");
    cliente.setId(clienteID);
    cliente.setPrimeiroNome(primeiroNome);
    cliente.setSobrenome(sobrenome);
    cliente.setPessoaFisica(isPessoaFisica);

    if (isPessoaFisica) {
      editNome.setText(preferences.getString("nomeCompleto", "Verifique os dados"));

    } else {
      editNome.setText(preferences.getString("razaoSocial", "Verifique os dados"));
    }
  }

}
