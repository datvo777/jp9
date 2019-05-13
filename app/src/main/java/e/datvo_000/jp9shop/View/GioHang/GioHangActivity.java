package e.datvo_000.jp9shop.View.GioHang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Adapter.AdapterGioHang;
import e.datvo_000.jp9shop.Model.HoaDon.ModelCTHD;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DangNhap.DangNhapActivity;
import e.datvo_000.jp9shop.View.DonHang.XacNhanDonHangActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 26/03/2019.
 */

public class GioHangActivity extends AppCompatActivity implements ViewGioHang{
    TextView tvGiaTienTongCong;
    Button btnThanhToan;
    RecyclerView recyclerViewCTHD;
    ProgressDialog progressDialog;
    List<CTHD>cthdList = new ArrayList<>();
    ModelCTHD modelCTHD = new ModelCTHD();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();

        addEvents();
    }

    private void addControls() {
        setContentView(R.layout.layout_giohang);
        if ( TrangChuActivity.hoaDon!=null)
        {
            cthdList=  TrangChuActivity.hoaDon.getCthds();
        }
        tvGiaTienTongCong = findViewById(R.id.tvGiaTienTongCong);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        recyclerViewCTHD = findViewById(R.id.recyclerCTHD);
        progressDialog = new ProgressDialog(this);

        addProcess();

    }

    private void addProcess() {

        tvGiaTienTongCong.setText(TrangChuActivity.formatter.format( SetGiaTienTongCong())+" đ");
        if (tvGiaTienTongCong.getText().equals("0 đ"))
        {
            Intent it = new Intent(this,GioHangTrongActivity.class);
            startActivity(it);

        }
        btnThanhToan.setText("Thanh Toán ("+TongSLGH()+")");
        AdapterGioHang adapterGioHang = new AdapterGioHang(getApplicationContext(),cthdList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //phải set layout trước
        recyclerViewCTHD.setLayoutManager(layoutManager);
        recyclerViewCTHD.setAdapter(adapterGioHang);



       // adapterGioHang.notifyDataSetChanged();

    }
//su kien back ve
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private int SetGiaTienTongCong()
    {
        //cthdList=  TrangChuActivity.hoaDon.getCthds();
       int giaTien=0;
       for (CTHD cthd :cthdList)
       {
           giaTien+=  cthd.getGiaTien();
       }
       return giaTien;
    }
    private int TongSLGH()
    {
        int sl=0;
        for (CTHD cthd :cthdList)
        {
            sl+=  cthd.getSoluong();
        }
        return sl;
    }
    private void addEvents() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TrangChuActivity.acc ==null)
                {
                    Intent it = new Intent(GioHangActivity.this, DangNhapActivity.class);
                    startActivity(it);
                    return;
                }
                Intent iXacnhan = new Intent(getApplicationContext(), XacNhanDonHangActivity.class);
                startActivity(iXacnhan);
            }
        });
    }

    @Override
    public void ThemSLsp(CTHD cthd) {
        progressDialog.show();
        new Background(cthd).execute();

    }

    @Override
    public void TruSLsp(CTHD cthd) {
        progressDialog.show();
        new Background(cthd).execute();
        if (TrangChuActivity.hoaDon.getCthds().size()==0)
        {
            Intent it = new Intent(this, GioHangTrongActivity.class);
            startActivity(it);
        }

    }
    public  class  Background extends AsyncTask<Void,Void,Void>{
        int gia;
        int sl;
        CTHD cthd;

        public Background(CTHD cthd) {
            this.cthd = cthd;
        }

        @Override
        protected void onPreExecute() {

           // super.onPreExecute();
          //  progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           gia= SetGiaTienTongCong();
           sl=TongSLGH();
            modelCTHD.CapnhatCTHD(cthd);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {//tu bat try catch
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tvGiaTienTongCong.setText(TrangChuActivity.formatter.format(  gia)+" đ");
            btnThanhToan.setText("Thanh Toán ("+sl+")");
           progressDialog.cancel();

        }
    }
}
