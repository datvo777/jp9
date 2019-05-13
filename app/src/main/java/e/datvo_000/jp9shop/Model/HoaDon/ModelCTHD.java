package e.datvo_000.jp9shop.Model.HoaDon;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 24/03/2019.
 */

public class ModelCTHD {
    public void ThemCTHoaDonSP(CTHD cthd)
    {
        String duongdan = TrangChuActivity.SERVER+"chitiethoadon";

        //duongdan= duongdan.replaceAll(" ","%20");
        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();



        HashMap<String,String> hsMaHD = new HashMap<>();
        hsMaHD.put("maHD",String.valueOf(cthd.getMaHD()) );

        HashMap<String,String> hsMaCTHD= new HashMap<>();
        hsMaCTHD.put("maCTSP",String.valueOf(cthd.getMaCTSP()));

        HashMap<String,String> hsSoluong = new HashMap<>();
        hsSoluong.put("soluong",String.valueOf(cthd.getSoluong()));

        HashMap<String,String> hsGiaTien = new HashMap<>();
        hsGiaTien.put("giaTien",String.valueOf(cthd.getGiaTien()));

        attrs.add(hsMaHD);
        attrs.add(hsMaCTHD);

        attrs.add(hsSoluong);
        attrs.add(hsGiaTien);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void CapnhatCTHD(CTHD cthd)
    {
        String duongdan = TrangChuActivity.SERVER+"chitiethoadon";
        String data="";
        List<HashMap<String,String>> attrs = new ArrayList<>();



        HashMap<String,String> hsMaHD = new HashMap<>();
        hsMaHD.put("maHD",String.valueOf(cthd.getMaHD()) );

        HashMap<String,String> hsMaCTHD= new HashMap<>();
        hsMaCTHD.put("maCTSP",String.valueOf(cthd.getMaCTSP()));

        HashMap<String,String> hsSoluong = new HashMap<>();
        hsSoluong.put("soluongNew",String.valueOf(cthd.getSoluong()));

        HashMap<String,String> hsGiaTien = new HashMap<>();
        hsGiaTien.put("giaTienNew",String.valueOf(cthd.getGiaTien()));

        attrs.add(hsMaHD);
        attrs.add(hsMaCTHD);

        attrs.add(hsSoluong);
        attrs.add(hsGiaTien);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);//task chồng task
        //.execute() method to call them, they will execute in a queue(first then second).
        //executeOnExecutor Means both asyncTasks will execute simultaneously
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
