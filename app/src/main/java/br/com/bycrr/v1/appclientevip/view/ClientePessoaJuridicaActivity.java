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
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.api.AppUtil;
import br.com.bycrr.v1.appclientevip.model.Cliente;
import br.com.bycrr.v1.appclientevip.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente novoVip;
  ClientePJ novoClientePJ;
  private SharedPreferences preferences;

  // criar variáveis de tela
  EditText editCNPJ, editRazaoSocial, editDataAberturaEmpresa;
  Button btnSalvarConcluir, btnVoltar, btnCancelar;
  CheckBox chSimplesNacional, chMEI;
  boolean isFormularioOk, isSimplesNacional, isMEI;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cliente_pessoa_juridica);
    initFormulario();

    btnSalvarConcluir.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {
          novoClientePJ.setCnpj(editCNPJ.getText().toString());
          novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
          novoClientePJ.setDataAbertura(editDataAberturaEmpresa.getText().toString());
          novoClientePJ.setSimplesNacional(isSimplesNacional);
          novoClientePJ.setMei(isMEI);
          salvarSharedPreferences();
          Intent intent = new Intent(ClientePessoaJuridicaActivity.this, CredencialAcessoActivity.class);
          startActivity(intent);
        }
      }
    });

    btnVoltar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ClientePessoaJuridicaActivity.this, LoginActivity.class);
        startActivity(intent);
      }
    });
    btnCancelar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new FancyAlertDialog.Builder(ClientePessoaJuridicaActivity.this)
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

    if(TextUtils.isEmpty((editCNPJ.getText().toString()))) {
      editCNPJ.setError("*");
      editCNPJ.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
      editRazaoSocial.setError("*");
      editRazaoSocial.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editDataAberturaEmpresa.getText().toString())) {
      editDataAberturaEmpresa.setError("*");
      editDataAberturaEmpresa.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private void initFormulario() {
    editCNPJ = findViewById(R.id.editCNPJ);
    editRazaoSocial = findViewById(R.id.editRazaoSocial);
    editDataAberturaEmpresa = findViewById(R.id.editDataAberturaEmpresa);
    chSimplesNacional = findViewById(R.id.ckSimplesNacional);
    chMEI = findViewById(R.id.ckMEI);
    btnSalvarConcluir = findViewById(R.id.btnSalvarConcluir);
    btnCancelar = findViewById(R.id.btnCancelar);
    btnVoltar = findViewById(R.id.btnVoltar);
    isFormularioOk = false;
    isSimplesNacional = false;
    isMEI = false;
    novoClientePJ = new ClientePJ();
    novoVip = new Cliente();
    restaurarSharedPreferences();
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putString("cnpj", editCNPJ.getText().toString());
    dados.putString("dataAberturaEmpresa", editDataAberturaEmpresa.getText().toString());
    dados.putString("razaoSocial", editRazaoSocial.getText().toString());
    dados.putBoolean("simplesNacional", isSimplesNacional);
    dados.putBoolean("mei", isMEI);
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    //isPessoaFisica = preferences.getBoolean("pessoaFisica", false);
  }
  public void simplesNacional(View view) {
    isSimplesNacional = chSimplesNacional.isChecked();
  }

  public void MEI(View view) {
    isMEI = chMEI.isChecked();
  }
}
