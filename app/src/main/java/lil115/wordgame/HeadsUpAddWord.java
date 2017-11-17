package lil115.wordgame;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class HeadsUpAddWord extends AppCompatActivity {

    SQLiteDatabase db = null;
    int category = 1;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up_add_word);

        HeadsUpDataBase helper = new HeadsUpDataBase(getApplicationContext());
        db = helper.getWritableDatabase();

        Switch btnSwitch = (Switch) findViewById(R.id.btnSwitch);
        btnSwitch.setChecked(false);
        Button btnAdd = (Button)findViewById(R.id.btnADD);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        final EditText text = (EditText)findViewById(R.id.text);
        final TextView textRes = (TextView)findViewById(R.id.textResult);

        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    category = 1;
                }else {
                    category = 2;
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = text.getText().toString();
                if(word != null && word != ""){
                    ContentValues values = new ContentValues();
                    values.put(HeadsUpDataBase.CATEGORY, category);
                    values.put(HeadsUpDataBase.WORD,word);
                    db.insert(HeadsUpDataBase.TABLE, null, values);
                    textRes.setText("Word Added");
                }else {
                    textRes.setText("Input Word First!");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = text.getText().toString();
                Cursor cursor = db.query(HeadsUpDataBase.TABLE, null, null, null, null, null, null);
                textRes.setText("Cannot Delete This Word");
                while(cursor.moveToNext())
                {
                    if (cursor.getInt(0) == category && cursor.getString(1).equals(word)){
                        db.execSQL("DELETE FROM " + HeadsUpDataBase.TABLE + " WHERE " + HeadsUpDataBase.CATEGORY + " = " +
                                category + " AND " + HeadsUpDataBase.WORD + " = " + "'" + word + "';");
                        textRes.setText("Word Deleted");
                    }
                }
            }
        });

        Button btnBack = (Button)findViewById(R.id.btnEntry);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HeadsUpAddWord.this, HeadsUpEntry.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }
}
