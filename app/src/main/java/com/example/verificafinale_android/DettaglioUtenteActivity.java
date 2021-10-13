package com.example.verificafinale_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.verificafinale_android.models.Account;

public class DettaglioUtenteActivity extends AppCompatActivity {

    Account account;
    TextView nome;
    TextView cognome;
    TextView eta;
    TextView telefono;
    TextView nome_citta;
    TextView provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_utente);



        nome = findViewById(R.id.show_nome);
        cognome = findViewById(R.id.show_cognome);
        eta  =  findViewById(R.id.show_eta);
        telefono  = findViewById(R.id.show_telefono);
        nome_citta = findViewById(R.id.show_citta);
        provincia = findViewById(R.id.show_provincia);


        Intent intent = getIntent();
        account = intent.getParcelableExtra(LoginActivity.EXTRA_MESSAGE);

        nome.setText(account.getPersona().getNome());
        cognome.setText(account.getPersona().getCognome());
        eta.setText(String.valueOf(account.getPersona().getEtà()));
        telefono.setText(account.getPersona().getTelefono());
        nome_citta.setText(account.getPersona().getCitta().getNome());
        provincia.setText(account.getPersona().getCitta().getProvincia());



    }
}