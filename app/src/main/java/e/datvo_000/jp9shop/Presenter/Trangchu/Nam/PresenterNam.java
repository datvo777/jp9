package e.datvo_000.jp9shop.Presenter.Trangchu.Nam;

import android.view.View;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.Model.TrangChu.Nam.ModelNam;
import e.datvo_000.jp9shop.View.TrangChu.ViewNam;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class PresenterNam implements IPresenterNam {
    ViewNam viewNam;
    ModelNam modelNam;

    public PresenterNam(ViewNam viewNam) {
        this.viewNam = viewNam;
        this.modelNam = new ModelNam();
    }

    @Override
    public void LayDS() {
        List<String> keys = modelNam.GetListKeyNam();
        List<SanPham>sanPhams= modelNam.GetListTopNew();
        List<SanPham>sanPhamListSell= modelNam.GetListTopSell();
        viewNam.HienThiDs(keys,sanPhams,sanPhamListSell);
    }


}
