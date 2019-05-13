package e.datvo_000.jp9shop.Model.DangNhap;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class ModelKhachHang {
     public static KhachHang GetAccountByEmailKH(String email)
    {
        String duongdan = TrangChuActivity.SERVER+"khachhang?email="+email;

        //duongdan= duongdan.replaceAll(" ","%20");
        String data="";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return ParseKhachHang(data);
    }
    public static boolean EditAcc(KhachHang acc)
    {
        String duongdan = TrangChuActivity.SERVER+"khachhang";
        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,String> hsKH = new HashMap<>();

        try {
            hsKH.put("jsonAcc",objectMapper.writeValueAsString(acc) );
            Log.d("jsonKH",objectMapper.writeValueAsString(acc));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        attrs.add(hsKH);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs,"PUT");
        downloadJSON.execute();
        try {
            data=downloadJSON.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(data) ;
    }

    public static   KhachHang ParseKhachHang(String data) {

        KhachHang kh = new KhachHang();


        try {
            JSONObject jsonObject = new JSONObject(data);
            kh.setEmail(jsonObject.getString("email"));

            kh.setName(jsonObject.getString("name"));
            kh.setPhone(jsonObject.getString("phone"));
            kh.setPassword(jsonObject.getString("password"));
            JSONArray jsonArray = jsonObject.getJSONArray("sdcList");
            int count = jsonArray.length();
            List<SoDiaChi>soDiaChiList= new ArrayList<>();
            for (int i =0;i<count;i++)
            {
                JSONObject jo = jsonArray.getJSONObject(i);
                SoDiaChi sdc = new SoDiaChi();
                sdc.setAddress(jo.getString("address"));
                sdc.setMa(jo.getInt("ma"));
                sdc.setEmail(jo.getString("email"));
                sdc.setMacdinh(jo.getInt("ma"));
                sdc.setName(jo.getString("name"));
                sdc.setPhone(jo.getString("phone"));
                sdc.setMacdinh(jo.getInt("macdinh"));
                soDiaChiList.add(sdc);

            }
            kh.setSdcList(soDiaChiList);

        } catch (JSONException e) {
            e.printStackTrace();

        }


        return kh;
    }
}
