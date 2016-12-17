package com.example.ngoch.tinhtiendien;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by ngoch on 11/16/2016.
 */

public class ViewHolder {
    private  TextView mTenKH, mMaKH, mChiSoCu, mChiSoMoi, mLoai, mTien;
    private Context mContext;
    private View mView;
    public ViewHolder(View v, Context context){
        mTenKH = (TextView) v.findViewById(R.id.tenKH);
        mMaKH = (TextView) v.findViewById(R.id.maKH);
        mChiSoCu = (TextView) v.findViewById(R.id.chiSoCu);
        mChiSoMoi = (TextView) v.findViewById(R.id.chiSoMoi);
        mLoai = (TextView) v.findViewById(R.id.loaiKH);
        mTien = (TextView) v.findViewById(R.id.thanhTien);
        v.setLongClickable(true);
    }
    public void setData(Object kh){
        TienDienKH tdkh = (TienDienKH) kh;
        mTenKH.setText("Tên: " + tdkh.getmTen());
        mMaKH.setText("Mã: " + tdkh.getmMa());
        mChiSoCu.setText("Chỉ số cũ: " + tdkh.getmChiSoCu()+"");
        mChiSoMoi.setText("Chỉ số mới: " +tdkh.getmChiSoMoi());
        mLoai.setText( tdkh.getmLoaiKH());
        mTien.setText(tdkh.getmThanhTien() +"");
    }
}
