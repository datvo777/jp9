package e.datvo_000.jp9shop.View.DangNhap;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import e.datvo_000.jp9shop.Adapter.ViewPagerAdapter;
import e.datvo_000.jp9shop.Adapter.ViewPagerAdapterDangNhap;
import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 24/02/2019.
 */

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    android.support.v7.widget.Toolbar toolbar;
    @Override
//    phải là hàm onCreate 1tham số Bundle savedInstanceState mới lên dc
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        tabLayout = findViewById(R.id.tabDangNhap);
        viewPager = findViewById(R.id.viewpagerDangnhap);
        toolbar = findViewById(R.id.toolbarDN);

        setSupportActionBar(toolbar);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
//        refresh lại với nội dung mới cập nhật
        viewPagerAdapterDangNhap.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }
}
