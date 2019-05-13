package e.datvo_000.jp9shop.View.DonHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import e.datvo_000.jp9shop.Adapter.AdapterCTDH;
import e.datvo_000.jp9shop.Model.DangNhap.ModelSDC;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class CTDHActivity  extends AppCompatActivity {
    HoaDon hd;
    TextView tvTongCong, tvMaDH,tvDiaChi,tvTrangThai,tvTen,tvPhone,tvNgayLap,tvNgayGiao,tvCTTT;
    LinearLayout linearLayout;
    RecyclerView recyclerCTDH;

    ImageView imgTrangThai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();

    }

    private void addEvents() {
        tvCTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectMapper objectMapper = new ObjectMapper();
                Intent it = new Intent(CTDHActivity.this,CTTTActivity.class);
                try {
                    it.putExtra("hd",objectMapper.writeValueAsString(hd));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                startActivity(it);
            }
        });

    }

    private void addControls() {
        setContentView(R.layout.layout_ctdh);
        linearLayout = findViewById(R.id.linearCTTT);
        tvDiaChi = findViewById(R.id.tvDiachi);
        tvTongCong = findViewById(R.id.tvTC);
        tvMaDH = findViewById(R.id.tvMaDH);
        tvPhone=findViewById(R.id.tvSDT);
        tvTen = findViewById(R.id.tvName);
        tvTrangThai=findViewById(R.id.tvTrangThai);
        tvCTTT = findViewById(R.id.tvCTTT);
        tvNgayLap = findViewById(R.id.tvNgayLap);
        tvNgayGiao =findViewById(R.id.tvNgayGiao);
        recyclerCTDH=findViewById(R.id.recyclerCTDH);
        imgTrangThai = findViewById(R.id.imgTrangThai);
        addProcess();
    }

    private void addProcess() {
        Intent intent = getIntent();
        int position =  intent.getIntExtra("position",0);
        hd = TrangChuActivity.donHangs.get(position);
        SoDiaChi sdc = ModelSDC.GetSDCByMa(hd.getMaDiaChi());
        tvDiaChi.setText(sdc.getAddress());
        tvTrangThai.setText(hd.getTinhtrang());
        tvTen.setText(sdc.getName());
        tvPhone.setText(sdc.getPhone());
        tvMaDH.setText(String.valueOf(hd.getMa()));
        tvTongCong.setText(TrangChuActivity.formatter.format( hd.getTongTien())+" đ");


        tvNgayLap.setText(hd.getNgayLap().replace("T","  "));
        if (tvTrangThai.getText().toString().contains("nhận"))//tiếp nhận đh
        {
            imgTrangThai.setImageResource(R.drawable.circle_blue_16px);
            tvNgayGiao.setVisibility(View.GONE);
        }
        else if (tvTrangThai.getText().toString().contains("Đang"))//đang giao
        {
         imgTrangThai.setImageResource(R.drawable.circle_yellow_16px);
            tvNgayGiao.setVisibility(View.GONE);

        }
        else//đã giao
        {
            imgTrangThai.setImageResource(R.drawable.checked_16px);
            tvNgayGiao.setText(hd.getNgayGiao().replace("T","  "));
            tvNgayGiao.setVisibility(View.VISIBLE);
        }


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerCTDH.setLayoutManager(layoutManager);
        AdapterCTDH adapterCTDH = new AdapterCTDH(this,hd.getCthds());
        recyclerCTDH.setAdapter(adapterCTDH);


    }
}
