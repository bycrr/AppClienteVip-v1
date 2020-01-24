package br.com.bycrr.v1.appclientevip.view;

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
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.api.AppUtil;
import br.com.bycrr.v1.appclientevip.controller.ClienteController;
import br.com.bycrr.v1.appclientevip.model.Cliente;

public class LoginActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente clienteSalvo;
  boolean isFormularioOk, isLembrarSenha;
  private SharedPreferences preferences;

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
            salvarSharedPreferences();
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
    btnSejaVip.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, ClienteVipActivity.class);
        startActivity(intent);
        finish();
        return;
      }
    });
    txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Carregando tela de recuperação de senha...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
        startActivity(intent);
      }
    });

    txtLerPolitica.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "Carregando tela com a política de privacidade...", Toast.LENGTH_LONG).show();
        new FancyAlertDialog.Builder(LoginActivity.this)
          .setTitle("Política de Privacidade & Termos de Uso")
          .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
          .setMessage("Aqui vai a mensagem da política ... bla bla bla ...")
          .setNegativeBtnText("Discordo")
          .setNegativeBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
          .setPositiveBtnText("Concordo")
          .setPositiveBtnBackground(Color.parseColor("#4ECA25"))  //Don't pass R.color.colorvalue
          .setAnimation(Animation.POP)
          .isCancellable(true)
          .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
          .OnPositiveClicked(new FancyAlertDialogListener() {
            @Override
            public void OnClick() {
              Toast.makeText(getApplicationContext(),"Obrigado! Seja bem vindo! Conclua seu cadastro.",Toast.LENGTH_SHORT).show();
            }
          })
          .OnNegativeClicked(new FancyAlertDialogListener() {
            @Override
            public void OnClick() {
              Toast.makeText(getApplicationContext(),"Lamentamos. Mas, é necessário concordar com a política e termos.",Toast.LENGTH_SHORT).show();
              finish();
              return;
            }
          })
          .build();
      }
    });
  }

  private boolean validarDadosUsuario() {
    return ClienteController.validarDadosCliente(clienteSalvo, editEmail.getText().toString(), editSenha.getText().toString());
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
    //clienteFake = ClienteController.getClienteFake();
    clienteSalvo = new Cliente();
    restaurarSharedPreferences();
  }

  public void lembrarSenha(View view) {
    isLembrarSenha = chLembrar.isChecked();
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putBoolean("loginAutomatico", isLembrarSenha);
    dados.putString("emailCliente", editEmail.getText().toString());
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    isLembrarSenha = preferences.getBoolean("loginAutomatico", false);
    clienteSalvo.setEmail(preferences.getString("email", "erro"));
    clienteSalvo.setSenha(preferences.getString("senha", "erro"));
    clienteSalvo.setPrimeiroNome(preferences.getString("primeiroNome", "erro"));
    clienteSalvo.setSobrenome(preferences.getString("sobrenome", "erro"));
    clienteSalvo.setPessoaFisica(preferences.getBoolean("pessoaFisica", false));
    isLembrarSenha = preferences.getBoolean("loginAutomatico", false);
  }
}
