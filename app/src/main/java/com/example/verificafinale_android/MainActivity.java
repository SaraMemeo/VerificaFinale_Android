package com.example.verificafinale_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.verificafinale_android.models.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.verificafinale.extra.REPLY";
    public static final String EXTRA_MESSAGE = "com.example.android.verificafinale.extra.MESSAGE";

    public static final int TEXT_REQUEST = 1;
    private TextView textHeader;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Account account;



    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHeader = findViewById(R.id.main_header);

        Intent intent = getIntent();
        account = intent.getParcelableExtra(LoginActivity.EXTRA_MESSAGE);
        textHeader.setText(account.getPersona().getNome() + " " + account.getPersona().getCognome());

        mRecyclerView = findViewById(R.id.recyclerview);

        if(savedInstanceState != null){
            mAdapter = new WordListAdapter(this, mWordList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            Integer j = savedInstanceState.size();

            for( Integer i=0; i<=j; i++ )
                mWordList.add(savedInstanceState.getString(String.valueOf(i)));

            }






        }

    public void launchAggiungiProdActivity(View view) {
        Intent intent = new Intent(this, AggiungiProdActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }




    public void launchDettaglioUtente(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, DettaglioUtenteActivity.class);
        intent.putExtra(EXTRA_MESSAGE , account );
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String word = data.getStringExtra(AggiungiProdActivity.EXTRA_MESSAGE);
                mWordList.addLast(word);

                mAdapter = new WordListAdapter(this, mWordList);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                System.out.println("Prodotto aggiunto con successo!");
        }
    } }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(!mWordList.isEmpty()){
            Integer i=0;
            for(String s: mWordList){
                outState.putString(String.valueOf(i), s);
                i++;
            }
        }
    }
}