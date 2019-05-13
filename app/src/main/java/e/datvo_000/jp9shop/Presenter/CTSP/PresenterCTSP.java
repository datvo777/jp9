package e.datvo_000.jp9shop.Presenter.CTSP;

import android.util.Log;

import java.util.List;

import e.datvo_000.jp9shop.Model.ModelCTSP;
import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.View.ChiTietSP.ViewCTSPsl;

/**
 * Created by datvo_000 on 18/03/2019.
 */

public class PresenterCTSP implements IPresenterCTSP {
    ViewCTSPsl viewCTSPsl;
    ModelCTSP modelCTSP;
    public PresenterCTSP(ViewCTSPsl viewCTSPsl) {
        this.viewCTSPsl = viewCTSPsl;
        modelCTSP = new ModelCTSP();
    }

    @Override
    public List<CTSP> LayCTSPsl(int maSP) {
        List<CTSP> sanPhams = modelCTSP.GetListCTSP(maSP);
        if (sanPhams.size()>0)
        {
            viewCTSPsl.HienThiCTSPsl(sanPhams);
            viewCTSPsl.HienThiCTSPsize(sanPhams);
            viewCTSPsl.HiemThiCTSPmau(sanPhams);
        }
        else
        {
            Log.d("KcoCTSP","hjgjh");
            //viewCTSPsl.HienThiCTSPsl(sanPhams);
        }
        return sanPhams;

    }
}
