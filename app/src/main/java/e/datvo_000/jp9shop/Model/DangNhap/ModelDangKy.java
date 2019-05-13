package e.datvo_000.jp9shop.Model.DangNhap;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 06/03/2019.
 */

public class ModelDangKy {
    public String DangKyThanhVien(KhachHang kh){
        String duongdan = TrangChuActivity.SERVER+"khachhang";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsTenKH = new HashMap<>();
        hsTenKH.put("hotenKH",kh.getName());

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("emailKH",kh.getEmail());

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhauKH",kh.getPassword());

        HashMap<String,String> hssdt = new HashMap<>();
        hssdt.put("sdt",kh.getPhone());
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsTenKH);
        attrs.add(hssdt);
        String data ="";
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            Log.d("chuoijson",dulieuJSON);
        data = dulieuJSON;
        data=data.replace("\"","");


    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
        return data;
    }
}
