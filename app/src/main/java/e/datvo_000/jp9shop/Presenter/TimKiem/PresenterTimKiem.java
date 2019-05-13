package e.datvo_000.jp9shop.Presenter.TimKiem;

import java.util.List;

import e.datvo_000.jp9shop.Model.API.ModelTimKiem;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.TimKiem.ViewTimKiem;

/**
 * Created by datvo_000 on 15/03/2019.
 */

public class PresenterTimKiem implements IPresenterTimKiem{
ViewTimKiem viewTimKiem;
ModelTimKiem modelTimKiem;

    public PresenterTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        this.modelTimKiem = new ModelTimKiem() ;
    }

    @Override
    public void TimkiemSP(String tukhoa) {
        List<SanPham>sanPhams = modelTimKiem.GetListSPTimKiem(tukhoa);
        if (sanPhams.size()>0)
        {
            viewTimKiem.TimKiemThanhCong(sanPhams);
        }
        else
        {
            viewTimKiem.TimKiemThatBai();
        }

    }

    @Override
    public void TimKiemSPNam(String tukhoa) {
        List<SanPham>sanPhams = modelTimKiem.GetListSPTimKiemNam(tukhoa);
        if (sanPhams.size()>0)
        {
            viewTimKiem.TimKiemThanhCong(sanPhams);
        }
        else
        {
            viewTimKiem.TimKiemThatBai();
        }
    }
    @Override
    public void TimKiemSPNu(String tukhoa) {
        List<SanPham>sanPhams = modelTimKiem.GetListSPTimKiemNu(tukhoa);
        if (sanPhams.size()>0)
        {
            viewTimKiem.TimKiemThanhCong(sanPhams);
        }
        else
        {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
