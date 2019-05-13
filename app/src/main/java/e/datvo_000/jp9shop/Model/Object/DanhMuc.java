package e.datvo_000.jp9shop.Model.Object;

import java.util.List;

/**
 * Created by datvo_000 on 20/02/2019.
 */

public class DanhMuc {
    int ma;

    String ten;
    List<DanhMuc> listCon;

    public List<DanhMuc> getListCon() {
        return listCon;
    }

    public void setListCon(List<DanhMuc> listCon) {
        this.listCon = listCon;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }
}
