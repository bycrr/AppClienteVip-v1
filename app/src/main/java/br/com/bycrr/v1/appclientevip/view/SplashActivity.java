package br.com.bycrr.v1.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.bycrr.v1.appclientevip.R;
import br.com.bycrr.v1.appclientevip.api.AppUtil;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    iniciarAplicativo();
  }

  private void iniciarAplicativo() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        return;
      }
    }, AppUtil.TIME_SPLASH);
  }
}
