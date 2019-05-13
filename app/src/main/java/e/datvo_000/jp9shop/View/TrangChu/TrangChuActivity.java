package e.datvo_000.jp9shop.View.TrangChu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

//import com.facebook.AccessToken;
//import com.facebook.FacebookActivity;
//import com.facebook.FacebookSdk;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.login.LoginManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import e.datvo_000.jp9shop.Adapter.ExpandAdapter;
import e.datvo_000.jp9shop.Adapter.ViewPagerAdapter;
import e.datvo_000.jp9shop.Model.DangNhap.ModelDangNhap;
import e.datvo_000.jp9shop.Model.DangNhap.ModelKhachHang;
import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.DanhMuc;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.Presenter.Trangchu.XuliMenu.PresenterXLMenu;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DangNhap.DangNhapActivity;
import e.datvo_000.jp9shop.View.DonHang.DonHangActivity;
import e.datvo_000.jp9shop.View.GioHang.GioHangActivity;
import e.datvo_000.jp9shop.View.TaiKhoan.SoDiaChiActivity;
import e.datvo_000.jp9shop.View.TaiKhoan.ThongTinTKActivity;
import e.datvo_000.jp9shop.View.TrangChu.Menu.XLMenu;
import e.datvo_000.jp9shop.View.TrangChu.TimKiem.TimKiemActivity;

/**
 * Created by datvo_000 on 10/02/2019.
 * Activity là base concreate class thể hiện 1 màn hình trong android. FragmentActivity extends Activity do đó nó là activity kèm thêm quản lí fragment,
 * AppCompatActivity extends FragmentActivity do đó nó là FragmentActivity kèm thêm 1 số method compat để tương thích với cả api android cũ,giao diện material,appcompat-v7 library
 */

public class TrangChuActivity extends AppCompatActivity implements XLMenu {
    android.support.v7.widget.Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterXLMenu presenterXLMenu;
    ModelDangNhap modelDangNhap;
    MenuItem menuItemDN;
    EditText etTimKiem;
    ModelKhachHang modelKhachHang = new ModelKhachHang();
    public static DecimalFormat formatter;//lớp được sử dụng để định dạng số(thập phân) sử dụng một mẫu định dạng chỉ định của mình
    public static HoaDon hoaDon;
    public String email = "";
    public static KhachHang acc;
    public static SoDiaChi sdcMacDinh;
    String ten = "";
    public static List<HoaDon> donHangs;

    Menu menu;
    public static final String SERVER = "http://192.168.1.58/shop/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trangchu_layout);

        hoaDon = LayHoaDon(this.getApplicationContext());
        addControl();
        addEvent();
        formatter = new DecimalFormat("###,###");

        presenterXLMenu = new PresenterXLMenu(this);
        try {
            presenterXLMenu.layDSDanhmuc();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        modelDangNhap = new ModelDangNhap();


    }

    @Override
    public void onBackPressed() {
        //ko cho back
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());//Return the intent that started this activity.
    }

    private void addEvent() {
        etTimKiem.clearFocus();
        etTimKiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // Log.d("nhan","vo");
                Intent iTimKiem = new Intent(TrangChuActivity.this, TimKiemActivity.class);
                iTimKiem.putExtra("tukhoa", etTimKiem.getText().toString());//android.text.SpannableString cannot be cast to java.lang.String
                iTimKiem.putExtra("gianhCho", "");
                startActivity(iTimKiem);
                return true;


            }
        });
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);
        etTimKiem = findViewById(R.id.etTimKiem);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);//This method sets the toolbar as the app bar for the activity

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());//Return the FragmentManager for interacting with fragments associated with this fragment's activity.
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);//liên kết tablayout với viewpager

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);//This drawable shows a Hamburger icon when drawer is closed and an arrow when drawe
        // is open. It animates between these two states as the drawer opens. @StringRes int openDrawerContentDescRes to describe the "open drawer" action
        drawerLayout.addDrawerListener(drawerToggle);//Listener to notify when drawer events occur.
        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();//đồng bộ trạng thái
    }

    //    sự kiện click vào menu ... sẽ tạo ra menu_trangchu
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //tạo menu từ file xml
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;
        //lấy ra menuitem giohang nằm trong menu_trangchu
        MenuItem iGH = menu.findItem(R.id.giohang);
        //lấy ra view chứa textview giohang
        View giaoDienGioHang = MenuItemCompat.getActionView(iGH);

        TextView tvGH = (TextView) giaoDienGioHang.findViewById(R.id.tvGH);

        giaoDienGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });


        menuItemDN = menu.findItem(R.id.itDangNhap);

        MenuItem menuItemDH = menu.findItem(R.id.itDonHang);
        menuItemDH.setVisible(false);
        MenuItem menuItemSDC = menu.findItem(R.id.itSDC);
        menuItemSDC.setVisible(false);
        MenuItem menuItemDX = menu.findItem(R.id.itDangxuat);
        email = modelDangNhap.LayCacheEmailDangNhap(this);
        if (!email.equals(""))//đã đăng nhập
        {
            //lên server lay
            acc = modelKhachHang.GetAccountByEmailKH(email);
            donHangs = ModelHoaDon.GetDonHangByEmailKH(email);
            if (donHangs.size() != 0) {

                menuItemDH.setVisible(true);
            }
            menuItemSDC.setVisible(true);
            menuItemDN.setTitle(acc.getName());

            HoaDon hd = ModelHoaDon.GetHDByEmailKH(email);
            HoaDon h = LayHoaDon(this);
            if (hd.getMa()!=0)//email co hd tren server
            {
                hoaDon = hd;

                if (h.getMa()!=0&&h.getEmail().equals("null"))//trong may co hoa don luc chua dang nhap
                {
                    ModelHoaDon.XoaHD(h.getMa());//xóa hóa đơn trên server
                    XoaHoaDon(this);//xóa hóa đơn có sẵn trong máy
                }
            }
            else if (h.getMa()!=0&&h.getEmail().equals("null"))//có hóa đơn trong máy thì cập nhật email
            {

                hoaDon.setEmail(email);
                ModelHoaDon.UpdateHD(email, hoaDon.getMa());
                SaveHoaDon(this, hoaDon);
                //hoaDon=LayHoaDon(this);
            }


            menuItemDX.setVisible(true);

        } else {
//            HoaDon hd = null;
//            hd = LayHoaDon(this);
            menuItemDX.setVisible(false);
        }

        tvGH.setText(String.valueOf(CapNhapSLcthds()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                //accessToken ==null&&
                if (acc == null) {
                    Intent itDangnhap = new Intent(this, DangNhapActivity.class);
                    startActivity(itDangnhap);
                    // overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                } else {
                    Intent itDangnhap = new Intent(this, ThongTinTKActivity.class);
                    startActivity(itDangnhap);
                }
                break;
            case R.id.itDangxuat:

                modelDangNhap.XoaTTDN(this);
                acc = null;

                if (hoaDon != null)//xóa hóa đơn trong máy
                {
                    XoaHoaDon(this);
                    hoaDon = null;
                }
                onRestart();

                break;
            case R.id.itSDC:
                Intent it = new Intent(this, SoDiaChiActivity.class);
                startActivity(it);
                break;
            case R.id.itDonHang:
                Intent itDH = new Intent(this, DonHangActivity.class);
                startActivity(itDH);
                break;


        }
        return true;
    }

    @Override
    public void HienThiDSDanhmuc(List<DanhMuc> danhMucs) {
        List<String> gianhCho = new ArrayList<>();
        gianhCho.add("Nam");
        gianhCho.add("Nữ");
        ExpandAdapter expandAdater = new ExpandAdapter(this, danhMucs, gianhCho);
        expandableListView.setAdapter(expandAdater);
        expandableListView.setGroupIndicator(null);// làm mất cái dấu ^ có sẵn của expandableListView
        // expandAdater.notifyDataSetChanged();
    }

    public static HoaDon LayHoaDon(Context context) {
        HoaDon hd = new HoaDon();
        SharedPreferences sharedPreferences = context.getSharedPreferences("hoadon", Context.MODE_PRIVATE);
        String hoaDon = sharedPreferences.getString("hd", "{}");
        if (hoaDon.equals("{}")) {
            return hd;
        }


        hd = ModelHoaDon.ParseHoaDon(hoaDon);
        return hd;
    }

    public static void XoaHoaDon(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("hoadon", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();//clear
    }

    public static void SaveHoaDon(Context context, HoaDon hoaDon) {
        ObjectMapper objectMapper = new ObjectMapper();
        String hd = "";
        try {
            hd = objectMapper.writeValueAsString(hoaDon);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("hoadon", Context.MODE_PRIVATE); //chỉ có trong ứng dụng mới xài dc
        SharedPreferences.Editor editor = sharedPreferences.edit(); //Tạo đối tượng editor  để cho phép chỉnh sửa dữ liệu:
        editor.putString("hd", hd);//đưa dữ liệu vào
        editor.commit();//lưu file dangnhap.xml vào data/data/[application package name]/shared_prefs/shared_preferences_name.xml
    }

    public static int CapNhapSLcthds() {

        int soluong = 0;
        if (TrangChuActivity.hoaDon == null) return soluong;
        for (CTHD cthd : TrangChuActivity.hoaDon.getCthds()) {
            soluong += cthd.getSoluong();
        }
        return soluong;
    }
}
