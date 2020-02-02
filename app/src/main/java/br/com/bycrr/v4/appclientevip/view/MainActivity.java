package br.com.bycrr.v4.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v4.appclientevip.R;
import br.com.bycrr.v4.appclientevip.api.AppUtil;
import br.com.bycrr.v4.appclientevip.model.Cliente;
import br.com.bycrr.v4.appclientevip.model.ClientePF;
import br.com.bycrr.v4.appclientevip.model.ClientePJ;

public class MainActivity extends AppCompatActivity {

  /*TextView txtTitulo;
  TextView txtDataAtual;
  TextView txtHoraAtual;
  Button btnCadastro;*/

  SharedPreferences preferences;
  Cliente cliente;
  ClientePF clientePF;
  ClientePJ clientePJ;
  TextView txtNomeCliente;
  List<Cliente> clientes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initFormulario();
    buscarListaDeClientes();

    /*txtTitulo = findViewById(R.id.txtTitulo);
    txtDataAtual = findViewById(R.id.txt);
    txtHoraAtual = findViewById(R.id.txtHoraAtual);
    btnCadastro = findViewById(R.id.btnCadastro);
    txtTitulo.setText("Curso Android");
    txtTitulo.setTextColor(getResources().getColor(R.color.colorTextView));
    txtDataAtual.setText(AppUtil.getDataAtual());
    txtHoraAtual.setText(AppUtil.getHoraAtual());

    btnCadastro.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent iTelaCadastro = new Intent(MainActivity.this, CredencialAcessoActivity.class);
        startActivity(iTelaCadastro);
      }
    });*/
  }

  private void buscarListaDeClientes() {
    clientes = new ArrayList<>();
    clientes.add(cliente);
  }

  private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putBoolean("loginAutomatico", false);
    dados.apply();
  }

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    cliente.setPrimeiroNome(preferences.getString("primeiroNome", "erro"));
    cliente.setSobrenome(preferences.getString("sobrenome", "erro"));
    cliente.setEmail(preferences.getString("emailCliente", "erro"));
    cliente.setSenha(preferences.getString("senha", "erro"));
    cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", false));
    clientePF.setCpf(preferences.getString("cpf", "erro"));
    clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "erro"));
    clientePJ.setCnpj(preferences.getString("cnpj", "erro"));
    clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "erro"));
    clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
    clientePJ.setMei(preferences.getBoolean("mei", false));
    clientePJ.setDataAbertura(preferences.getString("dataAberturaEmpresa", "erro"));
  }

  private void initFormulario() {
    cliente = new Cliente();
    clientePF = new ClientePF();
    clientePJ = new ClientePJ();
    txtNomeCliente = findViewById(R.id.txtNomeCliente);
    restaurarSharedPreferences();
    txtNomeCliente.setText("Bem vindo, " + cliente.getPrimeiroNome());
  }

  public void meusDados(View view) {
    Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE ***");
    Log.i(AppUtil.LOG_APP, "ID: " + cliente.getId());
    Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
    Log.i(AppUtil.LOG_APP, "Sobrenome: " + cliente.getSobrenome());
    Log.i(AppUtil.LOG_APP, "Email: " + cliente.getEmail());
    Log.i(AppUtil.LOG_APP, "Senha: " + cliente.getSenha());
    Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PF ***");
    Log.i(AppUtil.LOG_APP, "CPF: " + clientePF.getCpf());
    Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());

    if (!cliente.getPessoaFisica()) {
      Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PJ ***");
      Log.i(AppUtil.LOG_APP, "CPNJ: " + clientePJ.getCnpj());
      Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());
      Log.i(AppUtil.LOG_APP, "Data Abertura: " + clientePJ.getDataAbertura());
      Log.i(AppUtil.LOG_APP, "Simples Nacional: " + clientePJ.getSimplesNacional());
      Log.i(AppUtil.LOG_APP, "MEI: " + clientePJ.getMei());
    }
  }

  public void atualizarMeusDados(View view) {
    if (cliente.getPessoaFisica()) {
      cliente.setPrimeiroNome("Claudio R");
      cliente.setSobrenome("Powerguido");
      clientePF.setNomeCompleto("Claudinho Powerguido");
      //salvarSharedPreferences();
      Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE ***");
      Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
      Log.i(AppUtil.LOG_APP, "Sobrenome: " + cliente.getSobrenome());
      Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PF ***");
      Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());

    } else {
      clientePJ.setRazaoSocial("Empresa do Claudio");
      Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PJ ***");
      Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());
    }
  }

  public void excluirMinhaConta(View view) {
    new FancyAlertDialog.Builder(MainActivity.this)
      .setTitle("EXCLUIR SUA CONTA")
      .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
      .setMessage("Confirma exclusão definitiva da sua conta?")
      .setPositiveBtnText("Sim")
      .setPositiveBtnBackground(Color.parseColor("#4ECA25"))  //Don't pass R.color.colorvalue
      .setNegativeBtnText("Retornar")
      .setNegativeBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
      .setAnimation(Animation.POP)
      .isCancellable(true)
      .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
      .OnPositiveClicked(new FancyAlertDialogListener() {
        @Override
        public void OnClick() {
          Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome() + ", sua conta foi excluída. Esperamos que retorne em breve.",Toast.LENGTH_SHORT).show();
          cliente = new Cliente();
          clientePF = new ClientePF();
          clientePJ = new ClientePJ();
          // lembrar senha p/login automático tem q ser resetado
          //salvarSharedPreferences();
          finish();
          return;
        }
      })
      .OnNegativeClicked(new FancyAlertDialogListener() {
        @Override
        public void OnClick() {
          Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo.",Toast.LENGTH_SHORT).show();
        }
      })
      .build();
  }

  public void consultarClientesVip(View view) {
    Intent intent = new Intent(MainActivity.this, ConsultarClientesActivity.class);
    startActivity(intent);
  }

  public void sairDoAplicativo(View view) {
    new FancyAlertDialog.Builder(MainActivity.this)
      .setTitle("SAIR DO APLICATIVO")
      .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
      .setMessage("Confirma saída do aplicativo?")
      .setPositiveBtnText("Sim")
      .setPositiveBtnBackground(Color.parseColor("#4ECA25"))  //Don't pass R.color.colorvalue
      .setNegativeBtnText("Retornar")
      .setNegativeBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
      .setAnimation(Animation.POP)
      .isCancellable(true)
      .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
      .OnPositiveClicked(new FancyAlertDialogListener() {
        @Override
        public void OnClick() {
          Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome() + ", volte sempre! Obrigado.",Toast.LENGTH_SHORT).show();
          finish();
          return;
        }
      })
      .OnNegativeClicked(new FancyAlertDialogListener() {
        @Override
        public void OnClick() {
          Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo.",Toast.LENGTH_SHORT).show();
        }
      })
      .build();
 }
}
