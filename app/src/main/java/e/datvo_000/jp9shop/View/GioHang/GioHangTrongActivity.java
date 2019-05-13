package e.datvo_000.jp9shop.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class GioHangTrongActivity extends AppCompatActivity {
    Button btnMuaNgay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang_trong);
        btnMuaNgay=findViewById(R.id.btnMuaNgay);
        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), TrangChuActivity.class);
        startActivity(it);
    }
}
