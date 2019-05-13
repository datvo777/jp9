package e.datvo_000.jp9shop.Model.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.DanhMuc;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 20/02/2019.
 */

public class ModelDanhMuc {
    public List<DanhMuc> ParseJSONDanhMuc(String chuoiJSON)
    {
        List<DanhMuc>danhMucList = new ArrayList<>();
        try {
//            [{},{}]
            JSONArray jsonArray =  new JSONArray(chuoiJSON);
            int count = jsonArray.length();
            for (int i =0;i<count;i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DanhMuc dm = new DanhMuc();
                dm.setMa(Integer.parseInt(jsonObject.getString("ma")));
                dm.setTen(jsonObject.getString("ten"));
                danhMucList.add(dm);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  danhMucList;
    }
    public List<DanhMuc> GetDanhmucList(){
        String duongdan= TrangChuActivity.SERVER+"danhmuc";
        String data="";
        List<DanhMuc>danhMucList = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            data=downloadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ModelDanhMuc modelDanhMuc = new ModelDanhMuc();
        danhMucList= modelDanhMuc.ParseJSONDanhMuc(data);
        return danhMucList;
    }
}

