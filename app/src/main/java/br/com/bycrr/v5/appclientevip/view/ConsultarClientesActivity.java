package br.com.bycrr.v5.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.bycrr.v5.appclientevip.R;
import br.com.bycrr.v5.appclientevip.api.ClienteAdapter;
import br.com.bycrr.v5.appclientevip.controller.ClienteController;
import br.com.bycrr.v5.appclientevip.model.Cliente;

public class ConsultarClientesActivity extends AppCompatActivity {

  List<Cliente> clientes;
  ClienteAdapter adapter;
  Cliente obj;
  ClienteController controller;
  RecyclerView rvClientesVip;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_consultar_clientes);
    rvClientesVip = findViewById(R.id.rvClientesVip);
    clientes = new ArrayList<>();
    controller = new ClienteController(getApplicationContext());
    clientes = controller.listar();
    /*for (int i = 0; i < 50; i++) {
      obj = new Cliente();
      obj.setPrimeiroNome("Cliente " + i);
      obj.setPessoaFisica(i % 2==0);
      clientes.add(obj);
    }*/
    adapter = new ClienteAdapter(clientes, getApplicationContext());
    rvClientesVip.setAdapter(adapter);
    rvClientesVip.setLayoutManager(new LinearLayoutManager(this));
  }
}
