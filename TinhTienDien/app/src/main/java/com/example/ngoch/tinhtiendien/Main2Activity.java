package com.example.ngoch.tinhtiendien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText mTenKH, mMaKH, mChiSoCu, mChiSoMoi;
    RadioGroup mRadioGroup;
    Button mBtnOk;
    RadioButton mCQHC, mHGD, mDVSX;
    private int mPostition;
    TienDienKH t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTenKH = (EditText) findViewById(R.id.txtTenKH);
        mMaKH = (EditText) findViewById(R.id.txtMaKH);
        mChiSoCu = (EditText) findViewById(R.id.txtChiSoCu);
        mChiSoMoi = (EditText) findViewById(R.id.txtChiSoMoi);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mBtnOk = (Button) findViewById(R.id.btnOk);
        mCQHC = (RadioButton) findViewById(R.id.radioCQHC);
        mHGD = (RadioButton) findViewById(R.id.radioHGD);
        mDVSX = (RadioButton) findViewById(R.id.radioDVSX);

        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra(Constaint.KEY_BUNDLE);
        TienDienKH td = (TienDienKH) bundle.getSerializable(Constaint.KEY_KH);
        mPostition = bundle.getInt(Constaint.KEY_POSTITION);

        mTenKH.setText(td.getmTen());
        mMaKH.setText(td.getmMa());
        mChiSoCu.setText(td.getmChiSoCu()+"");
        mChiSoMoi.setText(td.getmChiSoMoi()+"");
        if(td.getmLoaiKH().equals("Cơ quan hành chính")) mCQHC.setChecked(true);
        else if(td.getmLoaiKH().equals("Hộ gia đình")) mHGD.setChecked(true);
        else if(td.getmLoaiKH().equals("Đơn vị sản xuất")) mDVSX.setChecked(true);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loai= "";
                if (!mTenKH.getText().toString().trim().isEmpty() && !mMaKH.getText().toString().trim().isEmpty() && !mChiSoCu.getText().toString().trim().isEmpty() && !mChiSoMoi.getText().toString().trim().isEmpty() && isCheckRadio()) {
                    if(Integer.parseInt(mChiSoCu.getText().toString().trim()) > Integer.parseInt(mChiSoMoi.getText().toString().trim()))
                        Toast.makeText(getBaseContext(),"Chỉ số cũ phải nhỏ hơn chỉ số mới!",Toast.LENGTH_LONG).show();
                    else {
                        if (mDVSX.isChecked())
                            loai = "Đơn vị sản xuất";
                        else if (mCQHC.isChecked()) loai = "Cơ quan hành chính";
                        else loai = "Hộ gia đình";
                        TienDienKH td = new TienDienKH(mMaKH.getText().toString().trim(), mTenKH.getText().toString().trim(), Integer.parseInt(mChiSoCu.getText().toString().trim()), Integer.parseInt(mChiSoMoi.getText().toString().trim()),loai, MainActivity.tinhTien(Integer.parseInt(mChiSoCu.getText().toString().trim()),Integer.parseInt(mChiSoMoi.getText().toString().trim()),loai));

                        Intent intent1 = new Intent();
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable(Constaint.KEY_KH_2, td);
                        bundle1.putInt(Constaint.KEY_POSTITION_2, mPostition);
                        intent1.putExtra(Constaint.KEY_BUNDLE_2, bundle1);

                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                }
                else Toast.makeText(Main2Activity.this,"Hãy nhập đầy đủ thông tin trước khi nhấn!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isCheckRadio() {
        if (mDVSX.isChecked() || mCQHC.isChecked() || mHGD.isChecked())
            return true;
        return false;
    }
    }
