package com.example.ngoch.tinhtiendien;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_CHISOCU;
import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_CHISOMOI;
import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_LOAIKH;
import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_MAKH;
import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_TENKH;
import static com.example.ngoch.tinhtiendien.MyDatabase.COLUMN_THANHTIEN;
import static com.example.ngoch.tinhtiendien.MyDatabase.TABLE_NAME;

/**
 * Created by ngoch on 11/29/2016.
 */

public class MySQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context c){
        super(c,MyDatabase.DATABASE_NAME,null,MyDatabase.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( "
                +COLUMN_MAKH+" TEXT PRIMARY KEY NOT NULL,"
                +COLUMN_TENKH+" TEXT NOT NULL,"
                +COLUMN_LOAIKH+" TEXT NOT NULL,"
                +COLUMN_CHISOCU+" TEXT NOT NULL,"
                +COLUMN_CHISOMOI+" TEXT NOT NULL,"
                +COLUMN_THANHTIEN+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
