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
import br.com.bycrr.v5.appclientevip.controller.ClientePJController;
import br.com.bycrr.v5.appclientevip.model.Cliente;
import br.com.bycrr.v5.appclientevip.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente novoVip;
  ClientePJ novoClientePJ;
  ClientePJController clientePJController;
  //não vai ser usado, melhor usar o shared preferences// ClientePFController clientePFController;
  private SharedPreferences preferences;

  // criar variáveis de tela
  EditText editCNPJ, editRazaoSocial, editDataAberturaPJ;
  Button btnSalvarContinuar, btnVoltar, btnCancelar;
  CheckBox chSimplesNacional, chMEI;
  boolean isFormularioOk, isSimplesNacional, isMEI;
  int ultimoIDClientePF;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cliente_pessoa_juridica_card);
    initFormulario();

    btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {
          //ultimoIDClientePF = clientePFController.getUltimoId();
          novoClientePJ.setClientePFID(ultimoIDClientePF);
          novoClientePJ.setCnpj(editCNPJ.getText().toString());
          novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
          novoClientePJ.setDataAbertura(editDataAberturaPJ.getText().toString());
          novoClientePJ.setSimplesNacional(isSimplesNacional);
          novoClientePJ.setMei(isMEI);
          clientePJController.incluir(novoClientePJ);
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
    if(!AppUtil.isCNPJ((editCNPJ.getText().toString()))) {
      editCNPJ.setError("*");
      editCNPJ.requestFocus();
      retorno = false;
      Toast.makeText(getApplicationContext(),"CNPJ inválido! Corrija para continuar.",Toast.LENGTH_LONG).show();

    } else {
      editCNPJ.setText(AppUtil.mascaraCNPJ(editCNPJ.getText().toString()));
    }
    if(TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
      editRazaoSocial.setError("*");
      editRazaoSocial.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editDataAberturaPJ.getText().toString())) {
      editDataAberturaPJ.setError("*");
      editDataAberturaPJ.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private void initFormulario() {
    editCNPJ = findViewById(R.id.editCNPJ);
    editRazaoSocial = findViewById(R.id.editRazaoSocial);
    editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);
    chSimplesNacional = findViewById(R.id.chSimplesNacional);
    chMEI = findViewById(R.id.chMEI);
    btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
    btnCancelar = findViewById(R.id.btnCancelar);
    btnVoltar = findViewById(R.id.btnVoltar);
    isFormularioOk = false;
    isSimplesNacional = false;
    isMEI = false;
    novoClientePJ = new ClientePJ();
    novoVip = new Cliente();
    clientePJController = new ClientePJController(this);
    //clientePFController = new ClientePFController(this);
    restaurarSharedPreferences();
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putString("cnpj", editCNPJ.getText().toString());
    dados.putString("dataAberturaEmpresa", editDataAberturaPJ.getText().toString());
    dados.putString("razaoSocial", editRazaoSocial.getText().toString());
    dados.putBoolean("simplesNacional", isSimplesNacional);
    dados.putBoolean("mei", isMEI);
    dados.putInt("ultimoIDClientePF", ultimoIDClientePF);
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    //isPessoaFisica = preferences.getBoolean("pessoaFisica", false);
    ultimoIDClientePF = preferences.getInt("ultimoIDClientePF", -1);
  }

  public void simplesNacional(View view) {
    isSimplesNacional = chSimplesNacional.isChecked();
  }

  public void MEI(View view) {
    isMEI = chMEI.isChecked();
  }
}
