package e.datvo_000.jp9shop.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 18/03/2019.
 */

public class ModelCTSP {
    private List<CTSP> ParseJSONGetListCTSP(String chuoiJSON) {
        List<CTSP>ctsps = new ArrayList<>();
        try {
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CTSP ctsp= new CTSP();
                String tenSP=(jsonObject.getString("tenSP"));
                ctsp.setTenSP(tenSP);
                int soluong,maMau,maSize,maSP,maCTSP;
                String tenMau,tenSize;

                soluong=(jsonObject.getInt("soluong"));
                maMau=(jsonObject.getInt("maMau"));
                maSize=(jsonObject.getInt("maSize"));
                maCTSP=jsonObject.getInt("maCTSP");
                maSP =(jsonObject.getInt("maSP"));
                tenMau=(jsonObject.getString("tenMau"));
                tenSize=(jsonObject.getString("tenSize"));
                ctsp.setSoluong(soluong);
                ctsp.setTenMau(tenMau);
                ctsp.setTenSize(tenSize);
                ctsp.setMaMau(maMau);
                ctsp.setMaSize(maSize);
                ctsp.setMaCTSP(maCTSP);
                ctsp.setMaSP(maSP);
                ctsps.add(ctsp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("listSPsize",String.valueOf(ctsps.size()) );
        return  ctsps;
    }
    public List<CTSP> GetListCTSP(int maSP)
    {
        String duongdan = TrangChuActivity.SERVER+"chitietsanpham"+"?maSP="+maSP;
        Log.d("duongdanCTSP",duongdan);
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
        return this.ParseJSONGetListCTSP(data);
    }
    public static CTSP GetCTSPByMaCTSP(int ma)
    {
        String duongdan = TrangChuActivity.SERVER+"chitietsanpham"+"?ma="+ma;
        Log.d("duongdanCTSP",duongdan);
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
        return ParseJSONctsp(data);
    }

    public static CTSP ParseJSONctsp(String data) {
        CTSP ctsp = new CTSP();
        try {
            JSONObject jsonObject = new JSONObject(data);

            ctsp.setMaCTSP(jsonObject.getInt("ma"));
            ctsp.setMaSP(jsonObject.getInt("maSP"));
            ctsp.setSoluong(jsonObject.getInt("soluong"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ctsp;
    }
}
