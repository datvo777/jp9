package e.datvo_000.jp9shop.View.DonHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import e.datvo_000.jp9shop.Model.DangNhap.ModelKhachHang;
import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TaiKhoan.SoDiaChiActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 28/03/2019.
 */

public class XacNhanDonHangActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    RadioGroup radioGroupPTTT;
    RadioGroup radioGroupPTVC;
    RadioButton rbPTTT;
    RadioButton rbPTVC;
    HoaDon hoaDon = TrangChuActivity.hoaDon;
    String ptvc = "Giao hàng tiêu chuẩn", pttt;
    int tongCong;
    KhachHang kh = null;

    TextView tvGia, tvTongCong, tvDiaChi, tvTenKH, tvSDT, tvPhi;
    Button btnXacnhan, btnEdit;
    ModelHoaDon modelHoaDon = new ModelHoaDon();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay ra nut dc chon
                rbPTTT = findViewById(radioGroupPTTT.getCheckedRadioButtonId());
                if (rbPTTT == null) {
                    Toast.makeText(getApplicationContext(), "Mời bạn chọn phương thúc thanh toán", Toast.LENGTH_LONG).show();

                    return;
                }
                if (rbPTTT.getText().toString().contains("giao")) {
                    pttt = "Thanh toán khi giao hàng";
                } else {
                    pttt = "Thanh toán bằng thẻ";
                }
                hoaDon.setHinhthucGH(ptvc);
                hoaDon.setGhichu("");
                hoaDon.setTongTien(tongCong);
                hoaDon.setHinhthucTT(pttt);
                hoaDon.setEmail(TrangChuActivity.acc.getEmail());
                hoaDon.setNgayLap("");//phải set"" nếu để null ko convert qua date bên server dc
                hoaDon.setNgayGiao("");
       //         Log.d("time", Calendar.getInstance().getTime().toString());
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date();
//               hoaDon.setNgayGiao(dateFormat.format(date));
                hoaDon.setMaDiaChi(TrangChuActivity.sdcMacDinh.getMa());
                Intent it = new Intent(XacNhanDonHangActivity.this, XacThucActivity.class);
                startActivity(it);

            }
        });
        radioGroupPTVC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbPTVC = findViewById(radioGroupPTVC.getCheckedRadioButtonId());
                if (rbPTVC.getText().toString().contains("chuẩn"))//30.000 đ
                {
                    ptvc = "Giao hàng tiêu chuẩn";
                    tongCong = SetGiaTienTongCong() + 30000;
                    tvPhi.setText("30,000 đ");
                } else {
                    ptvc = "Giao hàng nhanh";
                    tongCong = SetGiaTienTongCong() + 50000;
                    tvPhi.setText("50,000 đ");
                }
                tvTongCong.setText(TrangChuActivity.formatter.format(tongCong )+ " đ");

                TrangChuActivity.hoaDon.setTongTien(tongCong);
                TrangChuActivity.hoaDon.setHinhthucGH(ptvc);

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(XacNhanDonHangActivity.this, SoDiaChiActivity.class);
                it.putExtra("from", "XacNhan");
                it.putExtra("chucnang", "edit");
                startActivity(it);
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {//bắt sự kiện cho item back
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish(); //end activity
        TrangChuActivity.acc = ModelKhachHang.GetAccountByEmailKH(TrangChuActivity.acc.getEmail());
        startActivity(getIntent());
    }

    private void addControls() {
        setContentView(R.layout.layout_xacnhan_donhang);
        toolbar = findViewById(R.id.toolbarXNDH);
        //  setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroupPTTT = findViewById(R.id.radioPTTT);
        radioGroupPTVC = findViewById(R.id.radioPTGH);
        tvGia = findViewById(R.id.tvGia);
        tvTongCong = findViewById(R.id.tvTongCong);
        btnXacnhan = findViewById(R.id.btnTiepTuc);
        tvDiaChi = findViewById(R.id.tvDiachi);
        tvSDT = findViewById(R.id.tvSDT);
        btnEdit = findViewById(R.id.btnNext);
        tvPhi = findViewById(R.id.tvPhi);
        tvTenKH = findViewById(R.id.tvTenKH);
        rbPTVC = findViewById(radioGroupPTVC.getCheckedRadioButtonId());

        setTTKH();
    }

    private void setTTKH() {
        //  Intent it = getIntent();

        // tenKH= it.getStringExtra("tenKH");//lay intent cua dcgh
        if (TrangChuActivity.acc != null && TrangChuActivity.acc.getSdcList().size() == 0) {

            Intent intent = new Intent(this, DiaChiGHActivity.class);
            intent.putExtra("from", "XacNhanDonHang");
            intent.putExtra("chucnang","themMoi");
            startActivity(intent);
            return;
        }
        kh = TrangChuActivity.acc;
        tvTenKH.setText(kh.getName());
        tvSDT.setText(kh.getPhone());
        String diachi, sdt;
        diachi = "";
        sdt = "";
        String tenKH = "";

        for (SoDiaChi sdc : kh.getSdcList()) {
            if (sdc.getMacdinh() == 1) {
                TrangChuActivity.sdcMacDinh = sdc;
                diachi = sdc.getAddress();
                tenKH = sdc.getName();
                sdt = sdc.getPhone();
            }
        }
        tvDiaChi.setText(diachi);
        tvTenKH.setText(tenKH);
        tvDiaChi.setText(diachi);
        tvSDT.setText(sdt);
        tongCong = SetGiaTienTongCong() + 30000;
        tvGia.setText(TrangChuActivity.formatter.format(SetGiaTienTongCong()) + " đ");
        tvTongCong.setText(TrangChuActivity.formatter.format(tongCong) + " đ");


    }

    private int SetGiaTienTongCong() {
        //cthdList=  TrangChuActivity.hoaDon.getCthds();
        int giaTien = 0;
        for (CTHD cthd : TrangChuActivity.hoaDon.getCthds()) {
            giaTien += cthd.getGiaTien();
        }
        return giaTien;
    }
}
