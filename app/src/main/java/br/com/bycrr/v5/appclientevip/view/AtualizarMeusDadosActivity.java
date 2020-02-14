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
import br.com.bycrr.v5.appclientevip.controller.ClientePFController;
import br.com.bycrr.v5.appclientevip.controller.ClientePJController;
import br.com.bycrr.v5.appclientevip.model.Cliente;

public class AtualizarMeusDadosActivity extends AppCompatActivity {

  // Card Cliente
  EditText editPrimeiroNome, editSobrenome;
  CheckBox chPessoaFisica;
  Button btnSalvarCardCliente, btnEditarCardCliente;

  // Card ClientePF
  EditText editCPF, editNomeCompleto;
  Button btnSalvarCardClientePF, btnEditarCardClientePF;

  // Card ClientePj
  EditText editCNPJ, editRazaoSocial, editDataAberturaPJ;
  CheckBox chSimplesNacional, chMEI;
  Button btnSalvarCardClientePJ, btnEditarCardClientePJ;

  // Card Credenciais
  EditText editEmail, editSenha;
  Button btnSalvarCardSenha, btnEditarCardSenha;

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
    setContentView(R.layout.activity_atualizar_meus_dados);
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
      new FancyAlertDialog.Builder(AtualizarMeusDadosActivity.this)
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
            Intent intent = new Intent(AtualizarMeusDadosActivity.this, MainActivity.class);
            startActivity(intent);
          }
        })
        .OnPositiveClicked(new FancyAlertDialogListener() {
          @Override
          public void OnClick() {
            Intent intent = new Intent(AtualizarMeusDadosActivity.this, MainActivity.class);
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
    btnEditarCardCliente = findViewById(R.id.btnEditarCardCliente);
    btnSalvarCardCliente = findViewById(R.id.btnSalvarCardCliente);

    // Card ClientePF
    editCPF = findViewById(R.id.editCPF);
    editNomeCompleto = findViewById(R.id.editNomeCompleto);
    btnEditarCardClientePF = findViewById(R.id.btnEditarCardClientePF);
    btnSalvarCardClientePF = findViewById(R.id.btnSalvarCardClientePF);

    // Card ClientePj
    editCNPJ = findViewById(R.id.editCNPJ);
    editRazaoSocial = findViewById(R.id.editRazaoSocial);
    editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);
    chSimplesNacional = findViewById(R.id.chSimplesNacional);
    chMEI = findViewById(R.id.chMEI);
    btnEditarCardClientePJ = findViewById(R.id.btnEditarCardClientePJ);
    btnSalvarCardClientePJ = findViewById(R.id.btnSalvarCardClientePJ);

    // Card Credenciais
    editEmail = findViewById(R.id.editEmail);
    editSenha = findViewById(R.id.editSenhaA);
    btnEditarCardSenha = findViewById(R.id.btnEditarCardSenha);
    btnSalvarCardSenha = findViewById(R.id.btnSalvarCardSenha);

    //btnVoltar = findViewById(R.id.btnVoltar);

    cliente = new Cliente();
    cliente.setId(clienteID);
    clienteController = new ClienteController(this);
    clientePFController = new ClientePFController(this);
    clientePJController = new ClientePJController(this);
  }

  public void editarCardCliente(View view) {
    btnEditarCardCliente.setEnabled(false);
    btnSalvarCardCliente.setEnabled(true);
    editPrimeiroNome.setEnabled(true);
    editSobrenome.setEnabled(true);
    chPessoaFisica.setEnabled(true);
  }

  public void salvarCardCliente(View view) {
    if(validarFormularioCardCliente()) {
      btnEditarCardCliente.setEnabled(true);
      btnSalvarCardCliente.setEnabled(false);
      editPrimeiroNome.setEnabled(false);
      editSobrenome.setEnabled(false);
      chPessoaFisica.setEnabled(false);
      // alterar os dados salvando no banco de dados
      // controller
    }
  }

  public void editarCardClientePF(View view) {
    btnEditarCardClientePF.setEnabled(false);
    btnSalvarCardClientePF.setEnabled(true);
    editCPF.setEnabled(true);
    editNomeCompleto.setEnabled(true);
  }

  public void salvarCardClientePF(View view) {
    if(validarFormularioCardClientePF()) {
      btnEditarCardClientePF.setEnabled(true);
      btnSalvarCardClientePF.setEnabled(false);
      editCPF.setEnabled(false);
      editNomeCompleto.setEnabled(false);
    }
  }

  public void editarCardClientePJ(View view) {
    btnEditarCardClientePJ.setEnabled(false);
    btnSalvarCardClientePJ.setEnabled(true);
    editCNPJ.setEnabled(true);
    editRazaoSocial.setEnabled(true);
    editDataAberturaPJ.setEnabled(true);
    chSimplesNacional.setEnabled(true);
    chMEI.setEnabled(true);
  }

  public void salvarCardClientePJ(View view) {
    if(validarFormularioCardClientePJ()) {
      btnEditarCardClientePJ.setEnabled(true);
      btnSalvarCardClientePJ.setEnabled(false);
      editCNPJ.setEnabled(false);
      editRazaoSocial.setEnabled(false);
      editDataAberturaPJ.setEnabled(false);
      chSimplesNacional.setEnabled(false);
      chMEI.setEnabled(false);
    }
  }

  public void editarCardSenha(View view) {
    btnEditarCardSenha.setEnabled(false);
    btnSalvarCardSenha.setEnabled(true);
    editEmail.setEnabled(true);
    editSenha.setEnabled(true);
  }

  public void salvarCardSenha(View view) {
    btnEditarCardSenha.setEnabled(true);
    btnSalvarCardSenha.setEnabled(false);
    editEmail.setEnabled(false);
    editSenha.setEnabled(false);
  }

  public void voltar(View view) {
    Intent intent = new Intent(AtualizarMeusDadosActivity.this, MainActivity.class);
    startActivity(intent);
  }

  private boolean validarFormularioCardCliente() {
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

  private boolean validarFormularioCardClientePF() {
    boolean retorno = true;

    if(TextUtils.isEmpty((editCPF.getText().toString()))) {
      editCPF.setError("*");
      editCPF.requestFocus();
      retorno = false;
    }
    if(!AppUtil.isCPF(editCPF.getText().toString())) {
      editCPF.setError("*");
      editCPF.requestFocus();
      retorno = false;
      Toast.makeText(getApplicationContext(),"CPF inválido! Corrija para continuar.",Toast.LENGTH_LONG).show();

    } else {
      editCPF.setText(AppUtil.mascaraCPF(editCPF.getText().toString()));
    }
    if(TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
      editNomeCompleto.setError("*");
      editNomeCompleto.requestFocus();
      retorno = false;
    }
    return retorno;
  }

  private boolean validarFormularioCardClientePJ() {
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

}
