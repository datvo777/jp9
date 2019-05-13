package e.datvo_000.jp9shop.Presenter.Trangchu.XuliMenu;

//import com.facebook.AccessToken;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Connect.DownloadJSON;
import e.datvo_000.jp9shop.Model.API.ModelDanhMuc;
        import e.datvo_000.jp9shop.Model.Object.DanhMuc;
import e.datvo_000.jp9shop.View.TrangChu.Menu.XLMenu;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 20/02/2019.
 */

public class PresenterXLMenu implements IPresenterXLMenu{
    String ten="";
    XLMenu view ;
    public PresenterXLMenu(XLMenu view) {
            this.view=view;
    }

    @Override
    public void layDSDanhmuc() throws ExecutionException, InterruptedException {
//        phải sửa lại connection string trong iis manager nếu xài windowauthention để kết nối -> sửa lại thành sqlserverAuthentication
        String duongdan=TrangChuActivity.SERVER+"danhmuc";
        String data="";
        List<DanhMuc>danhMucList = new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        data=downloadJSON.get();
       ModelDanhMuc modelDanhMuc = new ModelDanhMuc();
      danhMucList= modelDanhMuc.ParseJSONDanhMuc(data);
      view.HienThiDSDanhmuc(danhMucList);


    }

//    @Override
//    public AccessToken layNickFB() {
//
//        ModelDangNhap modelDangNhap = new ModelDangNhap();
//        AccessToken accessToken = modelDangNhap.LayTokenFB();
//
//
//        return accessToken;
//    }
}
