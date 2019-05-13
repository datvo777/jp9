package e.datvo_000.jp9shop.Model.DangNhap;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class ModelSDC {
    public static boolean EditSDC(SoDiaChi sdc) {
        String duongdan = TrangChuActivity.SERVER + "sodiachi";
        List<HashMap<String, String>> attr = new ArrayList<>();
        HashMap<String, String> hsSDC = new HashMap<>();
        ObjectMapper objectMappe = new ObjectMapper();
        try {
            hsSDC.put("jsonSDC", objectMappe.writeValueAsString(sdc));
            attr.add(hsSDC);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        String data = "";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attr, "PUT");
        downloadJSON.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        try {
            data = downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(data);
    }

    public static boolean AddSDC(SoDiaChi sdc) {
        String duongdan = TrangChuActivity.SERVER + "sodiachi";
        List<HashMap<String, String>> attr = new ArrayList<>();
        HashMap<String, String> hsSDC = new HashMap<>();
        ObjectMapper objectMappe = new ObjectMapper();
        try {
            hsSDC.put("json", objectMappe.writeValueAsString(sdc));
            attr.add(hsSDC);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String data = "";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attr, "PUT");
        downloadJSON.execute();
        try {
            data = downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(data);
    }

    public static boolean DeleteSDC(int ma) {
        String duongdan = TrangChuActivity.SERVER + "sodiachi";
        List<HashMap<String, String>> attr = new ArrayList<>();
        HashMap<String, String> hsSDC = new HashMap<>();
        hsSDC.put("ma", String.valueOf(ma));
        String data = "";
        attr.add(hsSDC);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attr, "DELETE");
        downloadJSON.execute();
        try {
            data = downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(data);
    }

    public static SoDiaChi GetSDCByMa(int maDC) {
        String duongdan = TrangChuActivity.SERVER + "sodiachi" + "?maDC=" + maDC;


        String data = "";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data = downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ParseJSONsdc(data);


    }

    public static SoDiaChi ParseJSONsdc(String data) {
        SoDiaChi sdc = new SoDiaChi();
        try {
            JSONObject jsonObject = new JSONObject(data);

            sdc.setMa(jsonObject.getInt("ma"));
            sdc.setEmail(jsonObject.getString("email"));
            sdc.setMacdinh(jsonObject.getInt("macdinh"));
            sdc.setPhone(jsonObject.getString("phone"));
            sdc.setAddress(jsonObject.getString("address"));
            sdc.setName(jsonObject.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sdc;
    }
}
