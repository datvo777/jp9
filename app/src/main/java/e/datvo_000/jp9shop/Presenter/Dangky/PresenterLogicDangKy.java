package e.datvo_000.jp9shop.Presenter.Dangky;

import e.datvo_000.jp9shop.Model.DangNhap.ModelDangKy;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.View.DangNhap.IViewDangKy;

/**
 * Created by datvo_000 on 06/03/2019.
 */

public class PresenterLogicDangKy implements IPresenterDangKy{
    IViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(IViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(KhachHang kh) {
        String kiemtra = modelDangKy.DangKyThanhVien(kh);
        if(kiemtra.equals("true")){
            viewDangKy.DangKyThanhCong();
        }else{
            viewDangKy.DangKyThatBai(kiemtra);
        }
    }
}
