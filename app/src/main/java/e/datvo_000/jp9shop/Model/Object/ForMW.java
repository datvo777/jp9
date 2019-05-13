package e.datvo_000.jp9shop.Model.Object;

import java.util.List;

/**
 * Created by datvo_000 on 12/03/2019.
 */

public class ForMW {
    public List<String>key;
    public List<SanPham>spbanchays;
    public List<SanPham>spKMs;
    public List<SanPham>spNews;

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public List<SanPham> getSpbanchays() {
        return spbanchays;
    }

    public void setSpbanchays(List<SanPham> spbanchays) {
        this.spbanchays = spbanchays;
    }

    public List<SanPham> getSpKMs() {
        return spKMs;
    }

    public void setSpKMs(List<SanPham> spKMs) {
        this.spKMs = spKMs;
    }

    public List<SanPham> getSpNews() {
        return spNews;
    }

    public void setSpNews(List<SanPham> spNews) {
        this.spNews = spNews;
    }
}
