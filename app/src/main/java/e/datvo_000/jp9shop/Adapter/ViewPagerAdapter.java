package e.datvo_000.jp9shop.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.View.TrangChu.Fragment.FragmentNu;
import e.datvo_000.jp9shop.View.TrangChu.Fragment.FragmentNam;

/**
 * Created by datvo_000 on 15/02/2019.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment>list = new ArrayList<>();
    List<String>titleFragment = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        list.add(new FragmentNam());
        list.add(new FragmentNu());

        titleFragment.add("Nam");
        titleFragment.add("Nữ");

}

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
