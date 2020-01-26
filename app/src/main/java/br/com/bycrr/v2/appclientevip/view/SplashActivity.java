package br.com.bycrr.v2.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import br.com.bycrr.v2.appclientevip.R;
//import br.com.bycrr.v2.appclientevip.api.AppDataBase;
import br.com.bycrr.v2.appclientevip.api.AppUtil;

public class SplashActivity extends AppCompatActivity {

  private SharedPreferences preferences;
  boolean isLembrarSenha = false;
  //AppDataBase dataBase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    //salvarSharedPreferences();
    restaurarSharedPreferences();
    //dataBase = new AppDataBase(getApplicationContext());
    iniciarAplicativo();
  }

  private void iniciarAplicativo() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent;

        if(isLembrarSenha) {
          intent = new Intent(SplashActivity.this, MainActivity.class);

        } else {
          intent = new Intent(SplashActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
        return;
      }
    }, AppUtil.TIME_SPLASH);
  }

  /*private void salvarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    SharedPreferences.Editor dados = preferences.edit();
    dados.putBoolean("loginAutomatico", false);
    dados.apply();
  }*/

  private void restaurarSharedPreferences() {
    preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    isLembrarSenha = preferences.getBoolean("loginAutomatico", false);
  }
}
