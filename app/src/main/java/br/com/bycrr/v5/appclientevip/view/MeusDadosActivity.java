package br.com.bycrr.v5.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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
import br.com.bycrr.v5.appclientevip.model.ClientePF;
import br.com.bycrr.v5.appclientevip.model.ClientePJ;

public class MeusDadosActivity extends AppCompatActivity {

  // Card Cliente
  EditText editPrimeiroNome, editSobrenome;
  CheckBox chPessoaFisica;

  // Card ClientePF
  EditText editCPF, editNomeCompleto;

  // Card ClientePj
  EditText editCNPJ, editRazaoSocial, editDataAberturaPJ;
  CheckBox chSimplesNacional, chMEI;

  // Card Credenciais
  EditText editEmail, editSenha;

  //Button btnVoltar;

  ClienteController clienteController;
  Cliente cliente;
  SharedPreferences preferences;
  int clienteID;
  boolean isPessoaFisica;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meus_dados);
    restaurarSharedPreferences();
    initFormulario();
    popularFormulario();
  }

  private void popularFormulario() {
    if (clienteID >= 1) {
      // Buscar os dados via controller
      cliente = clienteController.getClienteByID(cliente);

    } else {
      new FancyAlertDialog.Builder(MeusDadosActivity.this)
        .setTitle("ATENÇÃO")
        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
        .setMessage("Não foi possível recuperar os dados do cliente")
        .setNegativeBtnText("Retornar")
        .setNegativeBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
        .setAnimation(Animation.POP)
        .isCancellable(true)
        .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
        .OnNegativeClicked(new FancyAlertDialogListener() {
          @Override
          public void OnClick() {
            Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
            startActivity(intent);
          }
        })
        .OnPositiveClicked(new FancyAlertDialogListener() {
          @Override
          public void OnClick() {
            Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
            startActivity(intent);
          }
        })
        .build();
    }
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
    clienteID = preferences.getInt("clienteID", -1);
  }

  private void initFormulario() {
    // Card Cliente
    editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
    editSobrenome = findViewById(R.id.editSobrenome);
    chPessoaFisica = findViewById(R.id.chPessoaFisica);

    // Card ClientePF
    editCPF = findViewById(R.id.editCPF);
    editNomeCompleto = findViewById(R.id.editNomeCompleto);

    // Card ClientePj
    editCNPJ = findViewById(R.id.editCNPJ);
    editRazaoSocial = findViewById(R.id.editRazaoSocial);
    editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);
    chSimplesNacional = findViewById(R.id.chSimplesNacional);
    chMEI = findViewById(R.id.chMEI);

    // Card Credenciais
    editEmail = findViewById(R.id.editEmail);
    editSenha = findViewById(R.id.editSenha);

    //btnVoltar = findViewById(R.id.btnVoltar);

    cliente = new Cliente();
    cliente.setId(clienteID);
    clienteController = new ClienteController(this);
  }

  public void voltar(View view) {
    Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
    startActivity(intent);
  }
}
