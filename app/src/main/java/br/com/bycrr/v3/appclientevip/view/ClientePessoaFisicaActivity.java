package br.com.bycrr.v3.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import br.com.bycrr.v3.appclientevip.R;
import br.com.bycrr.v3.appclientevip.api.AppUtil;
import br.com.bycrr.v3.appclientevip.model.Cliente;
import br.com.bycrr.v3.appclientevip.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente novoVip;
  ClientePF novoClientePF;
  private SharedPreferences preferences;

  // criar variáveis de tela
  EditText editCPF, editNomeCompleto;
  Button btnSalvarConcluir, btnVoltar, btnCancelar;
  boolean isFormularioOk, isPessoaFisica;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cliente_pessoa_fisica);
    initFormulario();

    btnSalvarConcluir.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {
          novoClientePF.setCpf(editCPF.getText().toString());
          novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());
          salvarSharedPreferences();
          Intent intent;

          if(isPessoaFisica) {
            intent = new Intent(ClientePessoaFisicaActivity.this, CredencialAcessoActivity.class);

          } else {
            intent = new Intent(ClientePessoaFisicaActivity.this, ClientePessoaJuridicaActivity.class);
          }
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
    btnCancelar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new FancyAlertDialog.Builder(ClientePessoaFisicaActivity.this)
          .setTitle("Confirme o Cancelamento")
          .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
          .setMessage("Deseja realmente cancelar?")
          .setNegativeBtnText("Não")
          .setPositiveBtnBackground(Color.parseColor("#4ECA25"))  //Don't pass R.color.colorvalue
          .setPositiveBtnText("Sim")
          .setNegativeBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
          .setAnimation(Animation.POP)
          .isCancellable(true)
          .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
          .OnPositiveClicked(new FancyAlertDialogListener() {
            @Override
            public void OnClick() {
              Toast.makeText(getApplicationContext(),"Cancelado com sucesso.",Toast.LENGTH_SHORT).show();
            }
          })
          .OnNegativeClicked(new FancyAlertDialogListener() {
            @Override
            public void OnClick() {
              Toast.makeText(getApplicationContext(),"Continue seu cadastro.",Toast.LENGTH_SHORT).show();
            }
          })
          .build();
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
    btnSalvarConcluir = findViewById(R.id.btnSalvarConcluir);
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
    dados.putString("cpf", editCPF.getText().toString());
    dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
  }
}
