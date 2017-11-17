package lil115.wordgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HeadsUpResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up_result);

        TextView numResult = (TextView)findViewById(R.id.numResult);
        TextView wordsResult = (TextView)findViewById(R.id.wordsResult);
        Button btnAgain = (Button)findViewById(R.id.btnAgain);
        Button btnHome = (Button)findViewById(R.id.btnHome);

        //get category
        Bundle extras = getIntent().getExtras();
        int count = extras.getInt("count");
        final int category = extras.getInt("category");
        String wordDisplay = "";
        ArrayList<String> words = extras.getStringArrayList("words");

        //word scroll and display
        wordsResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        for(String word : words){
            wordDisplay += word + "\n";
        }
        wordsResult.setText(wordDisplay);
        // number display
        numResult.setText("" + count + " Right!");

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpResult.this, HeadsUp.class);
                intent.putExtra("category",category);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpResult.this, HeadsUpEntry.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
