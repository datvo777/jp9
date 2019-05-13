package e.datvo_000.jp9shop.Model.Object;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by datvo_000 on 20/03/2019.
 */

public class HoaDon {
    int ma,tongTien;

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }


    int maDiaChi;

    public int getMaDiaChi() {
        return maDiaChi;
    }

    public void setMaDiaChi(int maDiaChi) {
        this.maDiaChi = maDiaChi;
    }


    String hinhthucGH;
    String email;
    String tinhtrang;

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String hinhthucTT;
    String ngayGiao;
    String ngayLap;

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getHinhthucTT() {
        return hinhthucTT;
    }

    public void setHinhthucTT(String hinhthucTT) {
        this.hinhthucTT = hinhthucTT;
    }

    String ghichu;
    List<CTHD>cthds=new ArrayList<>();

    public HoaDon() {

    }

    public HoaDon(int ma) {
        this.ma = ma;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }




    public String getHinhthucGH() {
        return hinhthucGH;
    }

    public void setHinhthucGH(String hinhthucGH) {
        this.hinhthucGH = hinhthucGH;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public List<CTHD> getCthds() {
        return cthds;
    }

    public void setCthds(List<CTHD> cthds) {
        this.cthds = cthds;
    }
}
