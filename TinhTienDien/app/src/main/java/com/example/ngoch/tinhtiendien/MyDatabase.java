package com.example.ngoch.tinhtiendien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ngoch on 11/29/2016.
 */

public class MyDatabase{
    MySQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDatabase.CursorFactory cursorFactory;
    public static Context context;
    public static String DATABASE_NAME = "KhachHang";
    public static int DATABASE_VERSION = 2;
    public static String TABLE_NAME = "TienDienKhachHang";
    public static String COLUMN_MAKH = "MaKH";
    public static String COLUMN_TENKH = "TenKH";
    public static String COLUMN_LOAIKH = "LoaiKH";
    public static String COLUMN_CHISOCU = "ChiSoCu";
    public static String COLUMN_CHISOMOI = "ChiSoMoi";
    public static String COLUMN_THANHTIEN = "ThanhTien";
    public MyDatabase(Context c){
        context = c;
    }
    //Hàm mở kết nối
    public MyDatabase open() throws SQLiteException{
        sqLiteOpenHelper = new MySQLiteOpenHelper(context);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        return this;

    }
    //Hàm đóng kết nối
    public void close(){
        sqLiteOpenHelper.close();
    }
    //Hàm chèn dữ liệu vào table
    public long insertData( TienDienKH tienDienKH){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MAKH,tienDienKH.getmMa());
        contentValues.put(COLUMN_TENKH,tienDienKH.getmTen());
        contentValues.put(COLUMN_CHISOCU,tienDienKH.getmChiSoCu());
        contentValues.put(COLUMN_CHISOMOI,tienDienKH.getmChiSoMoi());
        contentValues.put(COLUMN_THANHTIEN,MainActivity.tinhTien(tienDienKH.getmChiSoCu(),tienDienKH.getmChiSoMoi(),tienDienKH.getmLoaiKH()));
        contentValues.put(COLUMN_LOAIKH,tienDienKH.getmLoaiKH());
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }
    //Hàm xóa dữ liệu
    public int deleteData(String maKH){
        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_MAKH + "=?", new String[]{maKH});
    }
    //Hàm cập nhật dữ liệu
    public int updateData(TienDienKH tienDienKH){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MAKH,tienDienKH.getmMa());
        contentValues.put(COLUMN_TENKH,tienDienKH.getmTen());
        contentValues.put(COLUMN_CHISOCU,tienDienKH.getmChiSoCu());
        contentValues.put(COLUMN_CHISOMOI,tienDienKH.getmChiSoMoi());
        contentValues.put(COLUMN_THANHTIEN,MainActivity.tinhTien(tienDienKH.getmChiSoCu(),tienDienKH.getmChiSoMoi(),tienDienKH.getmLoaiKH()));
        contentValues.put(COLUMN_LOAIKH,tienDienKH.getmLoaiKH());
        return sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_MAKH + "=?",new String[]{tienDienKH.getmMa()});
    }
    //Hàm lấy dữ liệu
    public ArrayList<TienDienKH> selectData(){
        ArrayList<TienDienKH> listTienDienKH = new ArrayList<>();
        String[] column = {COLUMN_MAKH,COLUMN_TENKH,COLUMN_LOAIKH,COLUMN_CHISOCU,COLUMN_CHISOMOI,COLUMN_THANHTIEN};
        Cursor cursor;
        cursor = sqLiteDatabase.query(TABLE_NAME,column,null,null,null,null,null);
        TienDienKH tienDienKH;
        int iMa = cursor.getColumnIndex(COLUMN_MAKH);
        int iTen = cursor.getColumnIndex(COLUMN_TENKH);
        int iLoai = cursor.getColumnIndex(COLUMN_LOAIKH);
        int iChiSoCu = cursor.getColumnIndex(COLUMN_CHISOCU);
        int iChiSoMoi = cursor.getColumnIndex(COLUMN_CHISOMOI);
        int iTT = cursor.getColumnIndex(COLUMN_THANHTIEN);
        while (cursor.moveToNext()){
            tienDienKH = new TienDienKH(cursor.getString(iMa),cursor.getString(iTen),Integer.parseInt(cursor.getString(iChiSoCu)),Integer.parseInt(cursor.getString(iChiSoMoi)),cursor.getString(iLoai),Integer.parseInt(cursor.getString(iTT)));
            listTienDienKH.add(tienDienKH);
        }
        return listTienDienKH;
    }
}
