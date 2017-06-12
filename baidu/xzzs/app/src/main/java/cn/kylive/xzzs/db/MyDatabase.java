package cn.kylive.xzzs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static String CREATE_MYDB = "create table business_license (" +
            "id integer primary key autoincrement, " +
            "license_id text," +
            "company_name text," +
            "boss_name text)";
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_MYDB);
        ContentValues cv = new ContentValues();
        cv.put("license_id", "000001");
        cv.put("company_name", "miaojiang ltd");
        cv.put("boss_name", "shang.zhou");
        db.insert("business_license", null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
