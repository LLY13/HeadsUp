package lil115.wordgame;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static lil115.wordgame.R.id.countView;
import static lil115.wordgame.R.id.timeView;
import static lil115.wordgame.R.id.wordView;


public class HeadsUp extends AppCompatActivity implements Serializable {


    int count = 0;
    int category;
    SQLiteDatabase db = null;
    private SensorManager sensorManager = null;
    String word = null;
    TextView countViewer;


    //result to display result
    ArrayList<String> result = new ArrayList<>();
    //ArrayList<String> words = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up);

        //sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(listener, magneticSensor,
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(listener, accelerometerSensor,
                SensorManager.SENSOR_DELAY_GAME);


        final ProgressBar timerBar = (ProgressBar)findViewById(R.id.timerBar);
        final TextView timeViewer = (TextView)findViewById(timeView);
        this.countViewer = (TextView)findViewById(countView);
        final TextView wordViewer = (TextView)findViewById(wordView);
        Button btnRight = (Button)findViewById(R.id.btnRight);
        final Button btnPass = (Button)findViewById(R.id.btnPass);
        Button btnEnd = (Button)findViewById(R.id.endHeadsUp);

        timerBar.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

        //get category
        Bundle extras = getIntent().getExtras();
        category = extras.getInt("category");

        //function to start
        displayWord(category);
        countViewer.setText("" + 0);

        //countdown timer  120 seconds
        final CountDownTimer timer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeViewer.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
                timerBar.setProgress((int)(millisUntilFinished / 1000));
            }

            public void onFinish() {
                endGame();
            }

        }.start();



        //two btns to change word
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayWord(category);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            //int index = 0;
            @Override
            public void onClick(View view) {
                count++;
                countViewer.setText("" + count);
                result.add(wordViewer.getText().toString());
                //index++;
                displayWord(category);
            }
        });

        //end button to jump to next page
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.onFinish();
            }
        });

    }

    //sensor listener
    SensorEventListener listener = new SensorEventListener() {
        float[] mGravity;
        float[] mGeomagnetic;
        float values[] = new float[3];
        boolean isReturn = false;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                mGravity = event.values;
            }

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                mGeomagnetic = event.values;
            }

            if (mGravity != null && mGeomagnetic != null) {
                float R[] = new float[9];
                float I[] = new float[9];
                boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
                if (success) {

                    SensorManager.getOrientation(R, values);
                    //azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                }
            }


            if (Math.abs(values[2]) < 1.66){
                if (Math.abs(values[2]) > 1.47){
                    isReturn = true;
                }

            }

            if (Math.abs(values[2]) < 0.855 && isReturn ){
                displayWord(category);
                isReturn = false;
            }

            if (Math.abs(values[2]) > 2.286 && isReturn ){
                count++;
                countViewer.setText("" + count);
                result.add(word);
                displayWord(category);
                isReturn = false;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    //function to display word in HeadsUp
    private void displayWord(int category){
        TextView wordViewer = (TextView)findViewById(wordView);
        // get word from db
        HeadsUpDataBase helper = new HeadsUpDataBase(getApplicationContext());

        Cursor cursor = null;
        db = helper.getWritableDatabase();
        if (category == 1){
            cursor = db.rawQuery("SELECT  * FROM " + HeadsUpDataBase.TABLE + " WHERE CATEGORY == 1 ORDER BY RANDOM() LIMIT 1", null);
        }else if (category == 2){
            cursor = db.rawQuery("SELECT  * FROM " + HeadsUpDataBase.TABLE + " WHERE CATEGORY == 2 ORDER BY RANDOM() LIMIT 1", null);
        }else {
            cursor = db.rawQuery("SELECT  * FROM " + HeadsUpDataBase.TABLE + " ORDER BY RANDOM() LIMIT 1", null);
        }

        while(cursor.moveToNext())
        {
            word = cursor.getString(1);;
        }

        wordViewer.setText(word);
    }

    //function to jump to result page
    private void endGame(){
        Intent intent = new Intent();
        intent.setClass(HeadsUp.this, HeadsUpResult.class);
        intent.putExtra("count", count);
        intent.putExtra("category", category);
        //Log.i("endgame!!","" + category);
        intent.putStringArrayListExtra("words", result);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
