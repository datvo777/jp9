package e.datvo_000.jp9shop.Model.API;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 15/03/2019.
 */

public class ModelTimKiem {
    public List<SanPham> GetListSPTimKiem(String tukhoa)
    {
        String duongdan = TrangChuActivity.SERVER+"sanpham"+"?tukhoa="+tukhoa;
        duongdan= duongdan.replaceAll(" ","%20");
        Log.d("duongdan",duongdan);
        String data="";
        List<SanPham>sanPhams = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        sanPhams =this.ParseJSONTimKiemSP(data);
        return sanPhams;

    }
    public List<SanPham> GetListSPTimKiemNam(String tukhoa)
    {
        String duongdan = TrangChuActivity.SERVER+"nam"+"?tukhoa="+tukhoa.toString();
        Log.d("duongdan",duongdan);
        duongdan= duongdan.replaceAll(" ","%20");
        String data="";
        List<SanPham>sanPhams = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        sanPhams =this.ParseJSONTimKiemSP(data);
        return sanPhams;

    }
    public List<SanPham> GetListSPTimKiemNu(String tukhoa)
    {
        String duongdan = TrangChuActivity.SERVER+"nu"+"?tukhoa="+tukhoa;
        duongdan= duongdan.replaceAll(" ","%20");
        String data="";
        List<SanPham>sanPhams = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        sanPhams =this.ParseJSONTimKiemSP(data);//xai chung
        return sanPhams;

    }

       private List<SanPham> ParseJSONTimKiemSP(String chuoiJSON) {
        List<SanPham>sanPhams = new ArrayList<>();
        try {
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SanPham sp = new SanPham();
                sp.setMa(jsonObject.getInt("ma"));
                String ten=(jsonObject.getString("tenSP"));
                sp.setTen(ten);
                sp.setHinhAnh(jsonObject.getString("hinhanh"));
                sp.setGiaTien(jsonObject.getInt("giaTien"));
                sanPhams.add(sp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("listSPsize",String.valueOf(sanPhams.size()) );
        return  sanPhams;
    }
}
