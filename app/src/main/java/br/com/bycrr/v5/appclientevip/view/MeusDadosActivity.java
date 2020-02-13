package br.com.bycrr.v5.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import br.com.bycrr.v5.appclientevip.R;
import br.com.bycrr.v5.appclientevip.api.AppUtil;
import br.com.bycrr.v5.appclientevip.controller.ClienteController;
import br.com.bycrr.v5.appclientevip.controller.ClientePFController;
import br.com.bycrr.v5.appclientevip.controller.ClientePJController;
import br.com.bycrr.v5.appclientevip.model.Cliente;

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
  ClientePFController clientePFController;
  ClientePJController clientePJController;
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
      cliente.setClientePF(clientePFController.getClientePFByFK(cliente.getId()));

      if (!cliente.isPessoaFisica()) {
        cliente.setClientePJ(clientePJController.getClientePJByFK(cliente.getClientePF().getID()));
      }
      // dados obj Cliente
      editPrimeiroNome.setText(cliente.getPrimeiroNome());
      editSobrenome.setText(cliente.getSobrenome());
      editEmail.setText(cliente.getEmail());
      editSenha.setText(cliente.getSenha());
      chPessoaFisica.setChecked(cliente.isPessoaFisica());

      // dados obj ClientePF
      editCPF.setText(cliente.getClientePF().getCpf());
      editNomeCompleto.setText(cliente.getClientePF().getNomeCompleto());

      // dados obj ClientePJ
      if (!cliente.isPessoaFisica()) {
        editCNPJ.setText(cliente.getClientePJ().getCnpj());
        editRazaoSocial.setText(cliente.getClientePJ().getRazaoSocial());
        editDataAberturaPJ.setText(cliente.getClientePJ().getDataAbertura());
        chSimplesNacional.setChecked(cliente.getClientePJ().isSimplesNacional());
        chMEI.setChecked(cliente.getClientePJ().isMei());
      }
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
    editSenha = findViewById(R.id.editSenhaA);

    //btnVoltar = findViewById(R.id.btnVoltar);

    cliente = new Cliente();
    cliente.setId(clienteID);
    clienteController = new ClienteController(this);
    clientePFController = new ClientePFController(this);
    clientePJController = new ClientePJController(this);

    //if(!cliente.isPessoaFisica()) {
      // busco os dados PJ
    //}
  }

  public void voltar(View view) {
    Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
    startActivity(intent);
  }
}
