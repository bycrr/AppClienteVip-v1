package br.com.bycrr.v3.appclientevip.view;

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

import br.com.bycrr.v3.appclientevip.R;
import br.com.bycrr.v3.appclientevip.api.AppUtil;
import br.com.bycrr.v3.appclientevip.model.Cliente;

public class ClienteVipActivity extends AppCompatActivity {

  // declarar objetos e variáveis
  Cliente novoVip;
  private SharedPreferences preferences;
  boolean isFormularioOk, isPessoaFisica;

  // criar variáveis de tela
  EditText editPrimeiroNome, editSobrenome;
  CheckBox chPessoaFisica;
  Button btnSalvarContinuar, btnCancelar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cliente_vip);
    initFormulario();

    btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(isFormularioOk = validarFormulario()) {
          novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
          novoVip.setSobrenome(editSobrenome.getText().toString());
          novoVip.setPessoaFisica(isPessoaFisica);
          salvarSharedPreferences();

          //if(isPessoaFisica) {
            // tela de cadastro do CPF
            Intent intent = new Intent(ClienteVipActivity.this, ClientePessoaFisicaActivity.class);
            startActivity(intent);

          /*} else {
            // tela de cadastro do CNPJ
            Intent intent = new Intent(ClienteVipActivity.this, ClientePessoaJuridicaActivity.class);
            startActivity(intent);
          }*/
        }
      }
    });
    btnCancelar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new FancyAlertDialog.Builder(ClienteVipActivity.this)
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

    if(TextUtils.isEmpty((editPrimeiroNome.getText().toString()))) {
      editPrimeiroNome.setError("*");
      editPrimeiroNome.requestFocus();
      retorno = false;
    }
    if(TextUtils.isEmpty(editSobrenome.getText().toString())) {
      editSobrenome.setError("*");
      editSobrenome.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private void initFormulario() {
    editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
    editSobrenome = findViewById(R.id.editSobrenome);
    chPessoaFisica = findViewById(R.id.ckPessoaFisica);
    btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
    btnCancelar = findViewById(R.id.btnCancelar);
    isFormularioOk = false;
    novoVip = new Cliente();
    restaurarSharedPreferences();
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putString("primeiroNome", novoVip.getPrimeiroNome().toString());
    dados.putString("sobrenome", novoVip.getSobrenome().toString());
    dados.putBoolean("pessoaFisica", novoVip.getPessoaFisica());
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    //isPessoaFisica = preferences.getBoolean("pessoaFisica", false);
  }

  public void pessoaFisica(View view) {
      isPessoaFisica = chPessoaFisica.isChecked();
  }
}
