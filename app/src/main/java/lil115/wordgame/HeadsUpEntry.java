package lil115.wordgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HeadsUpEntry extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up_entry);


        //button category 1
        Button btnCat1 = (Button)findViewById(R.id.category1);
        btnCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpEntry.this, HeadsUp.class);
                intent.putExtra("category", 1);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //button category 2
        Button btnCat2 = (Button)findViewById(R.id.category2);
        btnCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpEntry.this, HeadsUp.class);
                intent.putExtra("category", 2);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //button category 3
        Button btnCat3 = (Button)findViewById(R.id.category3);
        btnCat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpEntry.this, HeadsUp.class);
                intent.putExtra("category", 3);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //add button
        Button btnAdd = (Button)findViewById(R.id.addWord);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpEntry.this, HeadsUpAddWord.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
