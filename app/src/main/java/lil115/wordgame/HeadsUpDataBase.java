package lil115.wordgame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/10/29.
 */

public class HeadsUpDataBase extends SQLiteOpenHelper {
    public final static int VERSION = 1;
    public final static String DATAFILE_NAME = "HeadsUp.db";
    public final static String TABLE = "HeadsUp";
    public final static String CATEGORY = "CATEGORY";
    public final static String WORD ="WORD";

    public HeadsUpDataBase(Context context)
    {
        super(context, DATAFILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "create table " +TABLE +" ("+CATEGORY+" integer, "+WORD+" text);";
        db.execSQL(sql);
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Stan');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Kyle');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Eric');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Kenny');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Butters');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Wendy');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Tweek');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Bebe');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Bradley');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Clyde');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Craig');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Dougie');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Heidi');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Jimmy');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Timmy');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Token');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(1,'Randy');");

        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'TIMMAY');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'Mmkay');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'authority');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'killed');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'ignorant');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'audience');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'hell');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'towel');");
        db.execSQL("insert into "+ TABLE+" (CATEGORY,WORD) values(2,'high');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+TABLE);

        onCreate(db);
    }
}
