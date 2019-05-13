package e.datvo_000.jp9shop.View.DonHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class CTTTActivity extends AppCompatActivity {
    TextView tvPTTT,tvPTVC,tvTongCong,tvGia,tvPhi;
    HoaDon hd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addControls() {
        setContentView(R.layout.layout_cttt);
        tvGia = findViewById(R.id.tvGia);
        tvPTTT = findViewById(R.id.tvPTTT);
        tvPTVC=findViewById(R.id.tvPTVC);
        tvTongCong=findViewById(R.id.tvTongCong);
        tvPhi =findViewById(R.id.tvPhi);
        addProcess();

    }

    private void addProcess() {
        Intent it = getIntent();
        hd = ModelHoaDon.ParseHoaDon(it.getStringExtra("hd"));
        int gia =SetGiaTienTongCong();
        tvGia.setText(TrangChuActivity.formatter.format( gia)+" đ");
        int phi = hd.getTongTien() - gia;
        tvPhi.setText(TrangChuActivity.formatter.format(phi)+" đ");
        tvTongCong.setText(TrangChuActivity.formatter.format( hd.getTongTien())+" đ");
        tvPTVC.setText(hd.getHinhthucGH());
        tvPTTT.setText(hd.getHinhthucTT());
    }

    private void addEvents() {

    }
    private int SetGiaTienTongCong()
    {
        //cthdList=  TrangChuActivity.hoaDon.getCthds();
        int giaTien=0;
        for (CTHD cthd :hd.getCthds())
        {
            giaTien+=  cthd.getGiaTien();
        }
        return giaTien;
    }
}
