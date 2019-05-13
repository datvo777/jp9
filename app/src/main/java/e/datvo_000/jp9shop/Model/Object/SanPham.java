package e.datvo_000.jp9shop.Model.Object;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class SanPham {
    int ma,maDanhMuc;
    String ten ,mota,hinhAnh,gianhCho;
   int giaTien;
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getGianhCho() {
        return gianhCho;
    }

    public void setGianhCho(String gianhCho) {
        this.gianhCho = gianhCho;
    }
}
