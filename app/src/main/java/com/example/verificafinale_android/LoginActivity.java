package com.example.verificafinale_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.verificafinale_android.models.Account;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.esamefinaleandroid.extra.REPLY";

    public static final String EXTRA_MESSAGE = "com.example.android.verificafinale.extra.MESSAGE";
    private EditText username;
    private EditText password;
    public static ArrayList<Account> elencoAccount = new ArrayList<Account>();


    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

    }

    public void launchMainActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, MainActivity.class);

        if (!elencoAccount.isEmpty()) {
            for (Account account : elencoAccount)
                if (account.getUsername().equals(username.getText().toString()) && account.getPassword().equals(password.getText().toString())) {


                    intent.putExtra(EXTRA_MESSAGE , account );
                    startActivity(intent);


                } else {
                    showError();
                }
        }else showError();

    }


    private void showError() {
        Toast toast = Toast.makeText(this, "L'utente non è ancora registrato!",
                Toast.LENGTH_SHORT);
        toast.show();

    }

    public void launchRegistrationActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);


    }


    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {

                Account account = data.getParcelableExtra(RegistrationActivity.EXTRA_REPLY);

                username.setText(account.getUsername());
                password.setText(account.getPassword());


            }
        }
    }

}