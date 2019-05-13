package e.datvo_000.jp9shop.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 24/03/2019.
 */

public class ModelSanPham {
    public static SanPham GetSPById(int maSP)
    {
        String duongdan = TrangChuActivity.SERVER+"sanpham"+"?maSP="+maSP;
        String data="";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ParseJSONSanPham(data);
    }
    public static SanPham GetSPByMaCTSP(int maCTSP)
    {
        String duongdan = TrangChuActivity.SERVER+"sanpham"+"?maCTSP="+maCTSP;
        String data="";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ParseJSONSanPham(data);
    }

    public static SanPham ParseJSONSanPham(String data) {
        SanPham sp = new SanPham();
        try {
                JSONObject jsonObject = new JSONObject(data);
                int maSP =(jsonObject.getInt("ma"));
                int maDM = jsonObject.getInt("maDanhMuc");
                int giaTien = jsonObject.getInt("giaTien");
                String mota = jsonObject.getString("mota");
                String hinhanh=jsonObject.getString("hinhanh");
                String gianhcho = jsonObject.getString("gianhcho");
                String tenSP=(jsonObject.getString("ten"));
                sp.setTen(tenSP);
                sp.setMa(maSP);
                sp.setMaDanhMuc(maDM);
                sp.setGiaTien(giaTien);
                sp.setHinhAnh(hinhanh);
                sp.setMota(mota);
                sp.setGianhCho(gianhcho);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  sp;
    }
}
