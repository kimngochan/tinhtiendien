package com.example.ngoch.tinhtiendien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TintContextWrapper;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mTenKH, mMaKH, mChiSoCu, mChiSoMoi;
    RadioGroup mRadioGroup;
    Button mBtnXoaTrang, mBtnThem;
    ListView mList;
    RadioButton mCQHC, mHGD, mDVSX;
    ArrayList<TienDienKH> mListTienDien;
    ArrayAdapter adapter;
    private int mPostition;
    private View mView;
    public static int CODE=100;
    MyDatabase mDataBase = new MyDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTenKH = (EditText) findViewById(R.id.txtTenKH);
        mMaKH = (EditText) findViewById(R.id.txtMaKH);
        mChiSoCu = (EditText) findViewById(R.id.txtChiSoCu);
        mChiSoMoi = (EditText) findViewById(R.id.txtChiSoMoi);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mBtnXoaTrang = (Button) findViewById(R.id.btnXoaTrang);
        mBtnThem = (Button) findViewById(R.id.btnThem);
        mList = (ListView) findViewById(R.id.listView);
        mCQHC = (RadioButton) findViewById(R.id.radioCQHC);
        mHGD = (RadioButton) findViewById(R.id.radioHGD);
        mDVSX = (RadioButton) findViewById(R.id.radioDVSX);
      //  mListTienDien = new ArrayList<>();
        mListTienDien = getData();
        adapter = new ArrayAdapter(mListTienDien, MainActivity.this, R.layout.item_laytout);
        mList.setAdapter(adapter);
        mList.setLongClickable(true);
        registerForContextMenu(mList);
        mBtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loai = "";
                if (!mTenKH.getText().toString().trim().isEmpty() && !mMaKH.getText().toString().trim().isEmpty() && !mChiSoCu.getText().toString().trim().isEmpty() && !mChiSoMoi.getText().toString().trim().isEmpty() && isCheckRadio()) {
                    if(Integer.parseInt(mChiSoCu.getText().toString().trim()) > Integer.parseInt(mChiSoMoi.getText().toString().trim()))
                        Toast.makeText(getBaseContext(),"Chỉ số cũ phải nhỏ hơn chỉ số mới!",Toast.LENGTH_LONG).show();
                    else {
                        if (mDVSX.isChecked())
                            loai = "Đơn vị sản xuất";
                        else if (mCQHC.isChecked()) loai = "Cơ quan hành chính";
                        else loai = "Hộ gia đình";

                        TienDienKH td = new TienDienKH(mMaKH.getText().toString().trim(), mTenKH.getText().toString().trim(), Integer.parseInt(mChiSoCu.getText().toString().trim()), Integer.parseInt(mChiSoMoi.getText().toString().trim()), loai, tinhTien(Integer.parseInt(mChiSoCu.getText().toString().trim()),Integer.parseInt(mChiSoMoi.getText().toString().trim()),loai));
                        mListTienDien.add(td);
                        mDataBase.insertData(td);
                        adapter.notifyDataSetChanged();
                    }
                }
                else Toast.makeText(MainActivity.this,"Hãy nhập đầy đủ thông tin trước khi thêm!", Toast.LENGTH_LONG).show();
            }
        });
        mBtnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTenKH.setText("");
                mTenKH.requestFocus();
                mMaKH.setText("");
                mChiSoMoi.setText("");
                mChiSoCu.setText("");
                mCQHC.setChecked(false);
                mDVSX.setChecked(false);
                mHGD.setChecked(false);
            }
        });
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPostition = i;
                mView = view;
                return false;
            }
        });
    }

    public boolean isCheckRadio() {
        if (mDVSX.isChecked() || mCQHC.isChecked() || mHGD.isChecked())
            return true;
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuXoa:
                mListTienDien.remove(mPostition);
                adapter.notifyDataSetChanged();
                break;
            case R.id.menuXem: xemChiTiet();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }
    public ArrayList<TienDienKH> getData(){
        mDataBase.open();
        return mDataBase.selectData();
    }
    public static int tinhTien(int socu, int somoi, String mLoaiKH){
        int tien;
        int sodien = somoi - socu;
        if(sodien<=50){
            if(mLoaiKH.equals("Hộ gia đình"))
                tien = sodien*1450;
            else if(mLoaiKH.equals("Cơ quan hành chính"))
                tien = sodien * 1550;
            else tien = sodien *1400;
        }
        else  if(sodien > 50 && sodien<=100){
            if(mLoaiKH.equals("Hộ gia đình")){
                tien = 50*1450  + (sodien - 50) * 1500;
            }
            else if(mLoaiKH.equals("Cơ quan hành chính")) {
                tien = 50 * 1550 + (sodien - 50) * 1600;
            }
            else {
                tien = 50 * 1400 + (sodien - 50) * 1500;
            }
        }
        else
        {
            if(mLoaiKH.equals("Hộ gia đình")){
                tien = 50*1450 + 50 * 1500 + (sodien - 100) * 1750;
            }
            else if(mLoaiKH.equals("Cơ quan hành chính")) {
                tien = 50*1550 + 50 * 1600 + (sodien - 100) * 1670;
            }
            else {
                tien = 50*1400 + 50 * 1500 + (sodien - 100) * 1550;
            }
        }
        return tien;
    }
    public void xemChiTiet(){
        String ten = mListTienDien.get(mPostition).getmTen();
        String ma = mListTienDien.get(mPostition).getmMa();
        int chisocu = mListTienDien.get(mPostition).getmChiSoCu();
        int chisomoi = mListTienDien.get(mPostition).getmChiSoMoi();
        String loai = mListTienDien.get(mPostition).getmLoaiKH();
        int tt = mListTienDien.get(mPostition).getmThanhTien();
        TienDienKH td = new TienDienKH(ma,ten,chisocu,chisomoi,loai, tt);

        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constaint.KEY_POSTITION,mPostition);
        bundle.putSerializable(Constaint.KEY_KH,td);
        intent.putExtra(Constaint.KEY_BUNDLE,bundle);
        startActivityForResult(intent,CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE && resultCode == RESULT_OK){
            Bundle bundle1 = data.getBundleExtra(Constaint.KEY_BUNDLE_2);
            TienDienKH tienDienKH = (TienDienKH) bundle1.getSerializable(Constaint.KEY_KH_2);
            int i = bundle1.getInt(Constaint.KEY_POSTITION_2);
            mListTienDien.get(i).setmMa(tienDienKH.getmMa());
            mListTienDien.get(i).setmTen(tienDienKH.getmTen());
            mListTienDien.get(i).setmChiSoCu(tienDienKH.getmChiSoCu());
            mListTienDien.get(i).setmChiSoMoi(tienDienKH.getmChiSoMoi());
            mListTienDien.get(i).setmLoaiKH(tienDienKH.getmLoaiKH());
            mListTienDien.get(i).setmThanhTien(tinhTien(tienDienKH.getmChiSoCu(),tienDienKH.getmChiSoMoi(),tienDienKH.getmLoaiKH()));
            mDataBase.updateData(tienDienKH);
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
