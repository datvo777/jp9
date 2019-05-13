package e.datvo_000.jp9shop.View.ChiTietSP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Adapter.AdapterCTSPmau;
import e.datvo_000.jp9shop.Adapter.AdapterCTSPsize;
import e.datvo_000.jp9shop.Adapter.AdapterCTSPsl;
import e.datvo_000.jp9shop.Model.HoaDon.ModelCTHD;
import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.Model.ModelCTSP;
import e.datvo_000.jp9shop.Model.ModelSanPham;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.Presenter.CTSP.PresenterCTSP;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.GioHang.GioHangActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 17/03/2019.
 */

public class CTSPActivity extends AppCompatActivity implements ViewCTSPsl {
    TextView tenSP,giaSP,tvGioHang;
    ImageView imgHinhSP;
    RecyclerView recyclerViewCTSPsl;
    RecyclerView recyclerViewCTSPsize;
    Button btnThemGH;
    RecyclerView recyclerViewCTSPmau;
    PresenterCTSP presenterCTSP;
    ImageButton imgGioHang;
    int maSP,maCTSP;
    String tenMau,tenSize;
    List<CTSP>ctsps = new ArrayList<>();
    List<CTHD>cthds = new ArrayList<>();
    ModelHoaDon modelHoaDon = new ModelHoaDon();
    ModelCTHD modelCTHD = new ModelCTHD();
    ModelSanPham modelSanPham = new ModelSanPham();
    int slTongHoaDon=0;
    SanPham sp = new SanPham();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControl();
        addEvent();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tvGioHang.setText(String.valueOf(TrangChuActivity.CapNhapSLcthds()));
    }

    private void addEvent() {
        btnThemGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrangChuActivity.hoaDon = TrangChuActivity.LayHoaDon(getApplicationContext());
                tenMau = AdapterCTSPmau.tenMau;
                tenSize=AdapterCTSPsize.tenSize;
                if (tenMau.equals(""))
                {

                    Toast.makeText(getApplicationContext(),"Vui lòng chọn màu",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tenSize.equals(""))
                {

                    Toast.makeText(getApplicationContext(),"Vui lòng chọn size",Toast.LENGTH_SHORT).show();
                    return;
                }
                for (CTSP ctsp : ctsps)
                {
                    if (ctsp.getTenMau().equals(tenMau)&&ctsp.getTenSize().equals(tenSize))
                    {
                        maCTSP=ctsp.getMaCTSP();
                        if (ctsp.getSoluong()==0)
                        {
                            Toast.makeText(getApplicationContext(),"Sản phẩm đã hết hàng!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        break;
                    }
                }
                int sl=1;
                int check=0;
                String giaTien1SP= giaSP.getText().toString().split(" ")[0].replace(",","");
                CTHD cthd = new CTHD();
                CTSP ctsp ;
                if(TrangChuActivity.hoaDon!=null&&TrangChuActivity.hoaDon.getCthds().size()>0)// co gio hang
                {
                    cthds = TrangChuActivity.hoaDon.getCthds();
                    for (CTHD ct : cthds)
                    {
                        if (maCTSP==ct.getMaCTSP())// trùng ctsan pham
                        {
                            sl=ct.getSoluong()+1;
                            cthd=ct;
                            check=1;
                            break;
                        }
                    }
                    if (check==1)//co CTHD coCTSP nay r-->capnhatlai cthd cu
                    {
                        ctsp = ModelCTSP.GetCTSPByMaCTSP(maCTSP);
                        if (ctsp.getSoluong()<sl)
                        {
                            Toast.makeText(CTSPActivity.this,"Bạn đã mua đủ số lượng sản phẩm có màu "+tenMau+" size "+tenSize+"!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        cthd.setSoluong(sl);
                        cthd.setMaCTSP(maCTSP);
                        cthd.setMaHD(TrangChuActivity.hoaDon.getMa());
                        int gt = sl* Integer.parseInt(giaTien1SP);
                        cthd.setGiaTien(gt);
                        modelCTHD.CapnhatCTHD(cthd);
                    }
                    else {//them cthd moi
                        cthd.setSoluong(sl);
                        cthd.setMaCTSP(maCTSP);
                        cthd.setMaHD(TrangChuActivity.hoaDon.getMa());
                        cthd.setGiaTien(Integer.parseInt(giaTien1SP));
                        cthd.setTenSP(sp.getTen());
                        cthd.setTenMau(tenMau);
                        cthd.setTenSize(tenSize);
                        modelCTHD.ThemCTHoaDonSP(cthd);
                        cthds.add(cthd);
                    }
                }
                else //chua co sp
                {


                        if (TrangChuActivity.hoaDon==null||TrangChuActivity.hoaDon.getMa()==0)//chua co gi het
                        {
                            TrangChuActivity.hoaDon = new HoaDon(modelHoaDon.ThemHoaDon());//co ma hoa don
                        }

                       cthd.setSoluong(sl);
                       cthd.setMaCTSP(maCTSP);
                       cthd.setMaHD(TrangChuActivity.hoaDon.getMa());

                        cthd.setTenSP(sp.getTen());
                        cthd.setTenMau(tenMau);
                        cthd.setTenSize(tenSize);
                        //giaTienHD = Integer.parseInt(giaTien1SP);
                        cthd.setGiaTien(Integer.parseInt(giaTien1SP));
                        modelCTHD.ThemCTHoaDonSP(cthd);
                       cthds.add(cthd);

                }
                TrangChuActivity.hoaDon.setCthds(cthds);
                if (TrangChuActivity.acc !=null)//đã đăng nhập
                {
                    ModelHoaDon.UpdateHD(TrangChuActivity.acc.getEmail(),TrangChuActivity.hoaDon.getMa());
                    TrangChuActivity.hoaDon.setEmail(TrangChuActivity.acc.getEmail());
                }
                TrangChuActivity.SaveHoaDon(getApplicationContext(),TrangChuActivity.hoaDon);//luu hoa don
                Toast.makeText(getApplicationContext(),"Đã thêm thành công sản phẩm "+tenSP.getText().toString()+"\nSize :"+tenSize.toString()+"\nMau : "+tenMau.toString(),Toast.LENGTH_LONG).show();
                Log.d("mahoadonMoi",String.valueOf(TrangChuActivity.hoaDon.getMa()) );
                slTongHoaDon = TrangChuActivity.CapNhapSLcthds();
                tvGioHang.setText(String.valueOf(slTongHoaDon));

            }
        });
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);

            }
        });
    }

    private void addControl() {
        setContentView(R.layout.layout_ctsp);
        tenSP= findViewById(R.id.tvTenSanPham);
        giaSP=findViewById(R.id.tvGiaSanPham);
        imgHinhSP=findViewById(R.id.imgHinhSP);
        btnThemGH=findViewById(R.id.btnThemGioHang);
        imgGioHang=findViewById(R.id.imgGH);
        recyclerViewCTSPsl= findViewById(R.id.recyclerCTSPsl);
        recyclerViewCTSPsize=findViewById(R.id.recyclerCTSPsize);
        recyclerViewCTSPmau=findViewById(R.id.recyclerCTSPmau);
        tvGioHang = findViewById(R.id.tvGiohangCT);
        Intent intent = getIntent();
         maSP = intent.getIntExtra("maSP",1);
         sp = modelSanPham.GetSPById(maSP);
        addProcess();
        Log.d("maSPActivity",String.valueOf(maSP));
        presenterCTSP = new PresenterCTSP(this);
       ctsps=  presenterCTSP.LayCTSPsl(maSP);
       tvGioHang.setText(String.valueOf(TrangChuActivity.CapNhapSLcthds()));


    }

    private void addProcess() {
        tenSP.setText(sp.getTen());
        giaSP.setText(TrangChuActivity.formatter.format(sp.getGiaTien())+" đ");
        Picasso.with(this).load(TrangChuActivity.SERVER+"sanpham?imgName="+sp.getHinhAnh()).into(imgHinhSP);
    }


    @Override
    public void HienThiCTSPsl(List<CTSP> ctsp) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterCTSPsl adapterCTSPsl = new AdapterCTSPsl(getApplicationContext(),ctsp);
        recyclerViewCTSPsl.setLayoutManager(layoutManager);
        recyclerViewCTSPsl.setAdapter(adapterCTSPsl);
        adapterCTSPsl.notifyDataSetChanged();
    }

    @Override
    public void HienThiCTSPsize(List<CTSP> ctsps) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        AdapterCTSPsize adapterCTSPsize = new AdapterCTSPsize(getApplicationContext(),ctsps);
        recyclerViewCTSPsize.setLayoutManager(layoutManager);
        recyclerViewCTSPsize.setAdapter(adapterCTSPsize);
        adapterCTSPsize.notifyDataSetChanged();
    }

    @Override
    public void HiemThiCTSPmau(List<CTSP> ctsps) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        AdapterCTSPmau adapterCTSPmau = new AdapterCTSPmau(getApplicationContext(),ctsps);
        recyclerViewCTSPmau.setLayoutManager(layoutManager);
        recyclerViewCTSPmau.setAdapter(adapterCTSPmau);
        adapterCTSPmau.notifyDataSetChanged();
    }
}
