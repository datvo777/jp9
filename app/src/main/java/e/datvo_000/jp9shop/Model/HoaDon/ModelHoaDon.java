package e.datvo_000.jp9shop.Model.HoaDon;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 24/03/2019.
 */

public class ModelHoaDon {
    public int ThemHoaDon()
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon?them=1";
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
        return Integer.parseInt(data);
    }
    public static  List<HoaDon>ParseListHd(String data)
    {
        List<HoaDon > list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            int count= jsonArray.length();
            for(int i=0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HoaDon hd = ParseHoaDon(jsonObject.toString());
                list.add(hd);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static HoaDon ParseHoaDon(String data)
    {
        HoaDon hoaDon = new HoaDon();
        try {
            JSONObject jsonObject = new JSONObject(data);
            hoaDon.setMa(jsonObject.getInt("ma"));
            //{cthds : []}
            JSONArray jsonArray =  jsonObject.getJSONArray("cthds");
            List<CTHD>cthds = new ArrayList<>();
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jo = jsonArray.getJSONObject(i);


                CTHD cthd= new CTHD();

                int soluong,maHD,maCTSP;

                soluong=(jo.getInt("soluong"));

                maCTSP=jo.getInt("maCTSP");
                maHD =(jo.getInt("maHD"));
                cthd.setMaCTSP(maCTSP);
                cthd.setMaHD(maHD);
                cthd.setSoluong(soluong);
                cthd.setGiaTien  (jo.getInt("giaTien"));
                cthd.setTenSize(jo.getString("tenSize"));
                cthd.setTenMau(jo.getString("tenMau"));
                cthd.setTenSP(jo.getString("tenSP"));

                cthds.add(cthd);

            }
            hoaDon.setEmail(jsonObject.getString("email"));
            hoaDon.setCthds(cthds);

            hoaDon.setTinhtrang(jsonObject.getString("tinhtrang"));
            hoaDon.setTongTien(jsonObject.getInt("tongTien"));
            hoaDon.setHinhthucGH(jsonObject.getString("hinhthucGH"));
            hoaDon.setHinhthucTT(jsonObject.getString("hinhthucTT"));
            hoaDon.setMaDiaChi(jsonObject.getInt("maDiaChi"));
            hoaDon.setNgayGiao(jsonObject.getString("ngayGiao"));
            hoaDon.setNgayLap(jsonObject.getString("ngayLap"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }
    public static HoaDon GetHDByEmailKH(String email)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon?emailKH="+email;

        //duongdan= duongdan.replaceAll(" ","%20");
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
        return ParseHoaDon(data);
    }
    public static List<HoaDon> GetDonHangByEmailKH(String email)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon?emailAcc="+email;
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
        return ParseListHd(data);
    }
    public static Boolean UpdateHD(String emailKH,int maHD)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon";


        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();



        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email",emailKH );

        HashMap<String,String> hsMaHD= new HashMap<>();
        hsMaHD.put("maHD",String.valueOf(maHD));



        attrs.add(hsEmail);
        attrs.add(hsMaHD);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
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
    public static boolean CompleteHD(HoaDon hoaDon)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon";


        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,String> hsHD = new HashMap<>();

        try {
            hsHD.put("json",objectMapper.writeValueAsString(hoaDon) );
            Log.d("jsonHoaDon",objectMapper.writeValueAsString(hoaDon));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        attrs.add(hsHD);
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
    public static String Xacthuc(String email)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon";
        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email",email );
        attrs.add(hsEmail);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return data ;
    }
    public static boolean XoaHD(int maHD)
    {
        String duongdan = TrangChuActivity.SERVER+"hoadon";
        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsHD = new HashMap<>();
        hsHD.put("maHD",String.valueOf(maHD) );
        attrs.add(hsHD);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs,"DELETE");
        downloadJSON.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean( data) ;
    }

}
