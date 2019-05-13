package e.datvo_000.jp9shop.Model.TrangChu.Nam;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class ModelNam {
    public List<String> ParseJSONKeyNam(String chuoiJSON)
    {
        Set<String> keyList = new HashSet<>();
        try {
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
               String tenSP =(jsonObject.getString("ten"));
                String tenDM=(jsonObject.getString("tendanhmuc"));
                keyList.add(tenSP);
                keyList.add(tenDM);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        List <String> list = new ArrayList<>();
        list.addAll(keyList);
        return  list;
    }
    public List<SanPham> ParseJSONTopnewNam(String chuoiJSON)
    {
        List<SanPham>sanPhams = new ArrayList<>();
        try {
//            [{},{}]
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SanPham sp = new SanPham();
                sp.setMa(Integer.parseInt(jsonObject.getString("ma")));
                Log.d("MaSP_ParseTopNew",String.valueOf(sp.getMa()) );
                String ten=(jsonObject.getString("ten"));
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
    public List<String> GetListKeyNam(){
        String duongdan= TrangChuActivity.SERVER+"nam";
        String data="";
        List<String>keys = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        keys= this.ParseJSONKeyNam(data);
        return keys;
    }
    public  List<SanPham>GetListTopNew()
    {
        String duongdan = TrangChuActivity.SERVER+"nam"+"?topnew=1";
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
        sanPhams =this.ParseJSONTopnewNam(data);
        return sanPhams;

    }
    public  List<SanPham>GetListTopSell()
    {
        String duongdan = TrangChuActivity.SERVER+"nam"+"?topsell=1";
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
        sanPhams =this.ParseJSONTopSellNam(data);
        return sanPhams;

    }

    private List<SanPham> ParseJSONTopSellNam(String chuoiJSON) {
        List<SanPham>sanPhams = new ArrayList<>();
        try {
//            [{},{}]
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SanPham sp = new SanPham();

                String ten=(jsonObject.getString("ten"));
                sp.setTen(ten);
                sp.setHinhAnh(jsonObject.getString("hinhanh"));
                sp.setGiaTien(jsonObject.getInt("giaTien"));
                sp.setMa(jsonObject.getInt("ma"));
                sanPhams.add(sp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  sanPhams;
    }
}
