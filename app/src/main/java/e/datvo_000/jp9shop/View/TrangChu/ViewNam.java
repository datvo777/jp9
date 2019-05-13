package e.datvo_000.jp9shop.View.TrangChu;

        import java.util.List;

        import e.datvo_000.jp9shop.Model.Object.SanPham;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public interface ViewNam {
    void HienThiDs(List<String> keyword,List<SanPham> sanPhamsNew,List<SanPham> sanPhamsSell);
}
