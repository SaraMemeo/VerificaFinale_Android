package com.example.verificafinale_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.verificafinale_android.models.Account;
import com.example.verificafinale_android.models.Citta;
import com.example.verificafinale_android.models.Persona;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_REPLY = "com.example.esamefinaleandroid.extra.REPLY";

    EditText nome;
    EditText cognome;
    EditText eta;
    EditText telefono;
    EditText username;
    EditText password;
    EditText password_check;
    EditText nome_citta;
    EditText provincia;

    Account account;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button registrazioneButton;

    Character sesso;

//    private AccountsStorage gestoreAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent intent = getIntent();

        nome = findViewById(R.id.reg_nome);
        cognome = findViewById(R.id.reg_cognome);
        eta = findViewById(R.id.reg_eta);
        telefono = findViewById(R.id.reg_telefono);
        nome_citta = findViewById(R.id.reg_citta);
        provincia = findViewById(R.id.reg_provincia);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        password_check = findViewById(R.id.reg_password_check);

        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        registrazioneButton= (Button) findViewById(R.id.button_registrazione);

        if(savedInstanceState != null) {
            account = savedInstanceState.getParcelable("account");
        }


    }



    public void creaAccount(View view) {


        final String value = ((RadioButton)findViewById(radioSexGroup.getCheckedRadioButtonId())).getText().toString();

        Citta citta = new Citta (nome_citta.getText().toString(), provincia.getText().toString());

        Persona persona = new Persona(nome.getText().toString(), cognome.getText().toString(), Integer.valueOf(eta.getText().toString()), telefono.getText().toString(),
                value.charAt(0), citta);

        account = new Account(username.getText().toString(), password.getText().toString(), persona);


        if(password.getText().toString().equals(password_check.getText().toString()) )
        {
            LoginActivity.elencoAccount.add(account);
            Log.d(LOG_TAG, "Registrazione avvenuta");
            returnReply(account);

        }

        else showError();

    }

    public void returnReply(Account account) {
        Intent replyIntent = new Intent(this, LoginActivity.class);
        replyIntent.putExtra(EXTRA_REPLY ,account);
        setResult(RESULT_OK, replyIntent);
        finish();


    }

    private void showError() {
        Toast toast = Toast.makeText(this, "La password e la password di conferma non coincidono",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(account!= null){
            outState.putParcelable("account", account);
        }
    }
}