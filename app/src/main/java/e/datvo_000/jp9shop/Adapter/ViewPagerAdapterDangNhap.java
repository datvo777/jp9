package e.datvo_000.jp9shop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import e.datvo_000.jp9shop.View.DangNhap.Fragment.FragmentDangNhap;
import e.datvo_000.jp9shop.View.DangNhap.Fragment.FragmentDangky;

/**
 * Created by datvo_000 on 24/02/2019.
 */

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangky fragmentDangky = new FragmentDangky();
                return fragmentDangky;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
//        dang nhap, dangky
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:

                return "Đăng nhập";
            case 1:

                return "Đăng ký";
            default:
                return null;
        }
    }
}
