package com.example.ngoch.tinhtiendien;

import java.io.Serializable;

/**
 * Created by ngoch on 11/16/2016.
 */

public class TienDienKH implements Serializable{
    private String mMa;
    private String mTen;
    private int mChiSoCu;
    private int mChiSoMoi;
    private String mLoaiKH;
    private int mThanhTien;

    public TienDienKH(String mMa, String mTen, int mChiSoCu, int mChiSoMoi, String mLoaiKH, int mThanhTien) {
        this.mMa = mMa;
        this.mTen = mTen;
        this.mChiSoCu = mChiSoCu;
        this.mChiSoMoi = mChiSoMoi;
        this.mLoaiKH = mLoaiKH;
        this.mThanhTien = mThanhTien;
    }

    public String getmMa() {
        return mMa;
    }

    public void setmMa(String mMa) {
        this.mMa = mMa;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public int getmChiSoCu() {
        return mChiSoCu;
    }

    public void setmChiSoCu(int mChiSoCu) {
        this.mChiSoCu = mChiSoCu;
    }

    public int getmChiSoMoi() {
        return mChiSoMoi;
    }

    public void setmChiSoMoi(int mChiSoMoi) {
        this.mChiSoMoi = mChiSoMoi;
    }

    public String getmLoaiKH() {
        return mLoaiKH;
    }

    public void setmLoaiKH(String mLoaiKH) {
        this.mLoaiKH = mLoaiKH;
    }
    public int getmThanhTien(){ return this.mThanhTien;}

    public void setmThanhTien(int mThanhTien) {
        this.mThanhTien = mThanhTien;
    }

}
