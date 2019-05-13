package e.datvo_000.jp9shop.Presenter.Trangchu.Nu;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.Model.TrangChu.Nam.ModelNam;
import e.datvo_000.jp9shop.Model.TrangChu.Nu.ModelNu;
import e.datvo_000.jp9shop.View.TrangChu.ViewNam;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class PresenterNu implements IPresenterNu {
    ViewNam viewNam;
    ModelNu modelNu;

    public PresenterNu(ViewNam viewNam) {
        this.viewNam = viewNam;
        this.modelNu = new ModelNu();
    }

    @Override
    public void LayDS() {
        List<String> keys = modelNu.GetListKeyNu();
        List<SanPham>sanPhams= modelNu.GetListTopNew();
        List<SanPham>sanPhamListSell= modelNu.GetListTopSell();
        viewNam.HienThiDs(keys,sanPhams,sanPhamListSell);
    }


}
