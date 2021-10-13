package com.example.verificafinale_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AggiungiProdActivity extends AppCompatActivity {

    private Button item1;
    private Button item2;
    private Button item3;
    private Button item4;

    public static final String EXTRA_MESSAGE =
            "com.example.shoppinglistapp.extra.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_prod);
        item1 = findViewById(R.id.item_1);
        item2 = findViewById(R.id.item_2);
        item3 = findViewById(R.id.item_3);
        item4 = findViewById(R.id.item_4);

    }


    public void returnReply(View view) {
        Intent replyIntent = new Intent();
        switch (view.getId()) {
            case R.id.item_1:
                String item1 = getString(R.string.item_1);
                replyIntent.putExtra(EXTRA_MESSAGE, item1);
                break;
            case R.id.item_2:
                String item2 = getString(R.string.item_2);
                replyIntent.putExtra(EXTRA_MESSAGE, item2);
                break;
            case R.id.item_3:
                String item3 = getString(R.string.item_3);
                replyIntent.putExtra(EXTRA_MESSAGE, item3);

                break;

            case R.id.item_4:
                String item4 = getString(R.string.item_4);
                replyIntent.putExtra(EXTRA_MESSAGE, item4);

                break;

            default:
                throw new RuntimeException("Unknow button ID");
        }

        setResult(RESULT_OK,replyIntent);
        finish();
    }

}