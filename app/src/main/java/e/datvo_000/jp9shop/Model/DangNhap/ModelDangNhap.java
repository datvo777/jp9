package e.datvo_000.jp9shop.Model.DangNhap;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

//import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
//import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 27/02/2019.
 */

public class ModelDangNhap {
//    AccessToken accessToken;
//    AccessTokenTracker accessTokenTracker;
//    public AccessToken LayTokenFB()
//    {
//
//         accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                accessToken = currentAccessToken;
//            }
//        };
//         //đã đăng nhập
//         accessToken=AccessToken.getCurrentAccessToken();
//        return accessToken ;
//    }
//    public void HuyTokenTracker(){
//        accessTokenTracker.stopTracking();
//    }
    public boolean KiemTraDangNhap(Context context, String email, String matkhau){
        String duongdan = TrangChuActivity .SERVER+"khachhang";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("email",email);
        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();
        try {

            String ketqua = downloadJSON.get();
            Log.d("kiemtra",ketqua);
            if(!ketqua.equals("null")){
                LuuTTDN(context,ModelKhachHang.ParseKhachHang(ketqua));
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  String LayCacheEmailDangNhap(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");
        return  email;
    }

    public   void LuuTTDN(Context context , KhachHang kh)
    {
        SharedPreferences sharedPreferences =context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE); //chỉ có trong ứng dụng mới xài dc
        SharedPreferences.Editor editor  = sharedPreferences.edit(); //Tạo đối tượng editor  để cho phép chỉnh sửa dữ liệu:
       //đưa dữ liệu vào
        editor.putString("email",kh.getEmail());
        editor.commit();//lưu file dangnhap.xml vào data/data/[application package name]/shared_prefs/shared_preferences_name.xml
    }
    public  void XoaTTDN(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();//clear
    }

}
