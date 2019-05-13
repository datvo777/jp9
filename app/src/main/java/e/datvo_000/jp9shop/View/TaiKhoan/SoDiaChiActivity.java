package e.datvo_000.jp9shop.View.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Adapter.AdapterSDC;
import e.datvo_000.jp9shop.Model.DangNhap.ModelKhachHang;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DonHang.DiaChiGHActivity;
import e.datvo_000.jp9shop.View.DonHang.XacNhanDonHangActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class SoDiaChiActivity extends AppCompatActivity {
    RecyclerView recyclerViewSDC;
    Button btnThemDC;
    List<SoDiaChi>list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThemDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SoDiaChiActivity.this,DiaChiGHActivity.class);
                it.putExtra("from","SoDiaChiActivity");
                it.putExtra("chucnang","them");
                startActivity(it);
            }
        });

    }

    private void addControls() {
        setContentView(R.layout.layout_sodiachi);
        recyclerViewSDC = findViewById(R.id.recycleMyAddress);
        btnThemDC = findViewById(R.id.btnThemDC);
        addProcess();
    }

    private void addProcess() {
        if (TrangChuActivity.acc.getSdcList()==null)
        {
            Intent it = new Intent(this, DiaChiGHActivity.class);
            startActivity(it);
            return;
        }
        list = TrangChuActivity.acc.getSdcList();
        AdapterSDC adapterSDC = new AdapterSDC(this,list);
        recyclerViewSDC.setAdapter(adapterSDC);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);//sap xep theo thu tu
        recyclerViewSDC.setLayoutManager(layoutManager);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        TrangChuActivity.acc= ModelKhachHang.GetAccountByEmailKH(TrangChuActivity.acc.getEmail());
        startActivity(getIntent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = getIntent();
//        String from ="";
//       from = intent.getStringExtra("from");
//       if (from.contains("XacNhan"))
//       {
//           intent = new Intent(this, XacNhanDonHangActivity.class);
//           startActivity(intent);
//       }
//       else
//       {
//           intent = new Intent(this,TrangChuActivity.class);
//           startActivity(intent);
//       }

    }
}
