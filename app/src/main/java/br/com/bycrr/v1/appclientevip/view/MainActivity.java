package br.com.bycrr.v1.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.api.AppUtil;

public class MainActivity extends AppCompatActivity {

  TextView txtTitulo;
  TextView txtDataAtual;
  TextView txtHoraAtual;
  Button btnCadastro;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
}
