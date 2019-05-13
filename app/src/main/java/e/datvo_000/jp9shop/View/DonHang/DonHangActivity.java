package e.datvo_000.jp9shop.View.DonHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import e.datvo_000.jp9shop.Adapter.AdapterDH;
import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class DonHangActivity extends AppCompatActivity {
RecyclerView recyclerDH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        setContentView(R.layout.layout_donhang);
        recyclerDH = findViewById(R.id.recyclerDH);
        addProcess();

    }

    private void addProcess() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        AdapterDH adapterDH = new AdapterDH(ModelHoaDon.GetDonHangByEmailKH(TrangChuActivity.acc.getEmail()),this);
        recyclerDH.setLayoutManager(layoutManager);
        recyclerDH.setAdapter(adapterDH);
    }
}
