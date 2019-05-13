package e.datvo_000.jp9shop.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import e.datvo_000.jp9shop.Model.ModelCTSP;
import e.datvo_000.jp9shop.Model.ModelSanPham;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.ChiTietSP.CTSPActivity;
import e.datvo_000.jp9shop.View.GioHang.GioHangActivity;
import e.datvo_000.jp9shop.View.GioHang.GioHangTrongActivity;
import e.datvo_000.jp9shop.View.GioHang.ViewGioHang;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 26/03/2019.
 */

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {
    Context context;
    List<CTHD>cthds;
    ViewGioHang viewGioHang;
    // This object helps you save/restore the open/close state of each view
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public AdapterGioHang(Context context, List<CTHD> cthds,ViewGioHang viewGioHang) {
        this.context = context;
        this.cthds = cthds;
        this.viewGioHang = viewGioHang;
    }

    @Override
    public AdapterGioHang.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_giohang,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    CTHD cthd;
    @Override
    public void onBindViewHolder(final AdapterGioHang.ViewHolder holder, final int position) {
         cthd = new CTHD();
                cthd = cthds.get(position);
        holder.tvMau.setText(cthd.getTenMau());
        holder.tvTenSP.setText(cthd.getTenSP());
        holder.tvSize.setText(cthd.getTenSize());
        holder.tvSL.setText(String.valueOf(cthd.getSoluong()));
        final CTSP ctsp = ModelCTSP.GetCTSPByMaCTSP(cthd.getMaCTSP());
       final SanPham sp = ModelSanPham.GetSPById(ctsp.getMaSP());

        holder.tvGiaSP.setText(TrangChuActivity.formatter.format(sp.getGiaTien())+" đ");//gia tien 1 sp
        Picasso.with(holder.imgHinhSP.getContext()).load(TrangChuActivity.SERVER+"sanpham?imgName="+sp.getHinhAnh()).into(holder.imgHinhSP);
        //. tới một cái thành phần trong viewholder mới lấy dc context
        viewBinderHelper.bind(holder.swipeRevealLayout,String.valueOf(cthd.getMaCTSP()));
        if (cthd.getSoluong()==1)
        {
            holder.btnMinus.setImageResource(R.drawable.trash_24);
        }

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ProgressDialog.show(context.gtApplicationContext(), "", "");
                if (ctsp.getSoluong()==cthd.getSoluong())
                {
                    Toast.makeText(v.getContext(),"Sản phẩm đã hết hàng !",Toast.LENGTH_LONG).show();
                    return;
                }
               CTHD ct =TrangChuActivity.hoaDon.getCthds().get(position);
                int giaTienSP = ct.getGiaTien();
                int giaTien1SP=giaTienSP/ct.getSoluong();
                ct.setSoluong(ct.getSoluong()+1);
                holder.tvSL.setText(String.valueOf(ct.getSoluong()));
                ct.setGiaTien (giaTienSP+giaTien1SP);
                TrangChuActivity.SaveHoaDon(context,TrangChuActivity.hoaDon);
                viewGioHang.ThemSLsp(ct);
                if (ct.getSoluong()==2)
                {
                    holder.btnMinus.setImageResource(R.drawable.minus_24);
                }

            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ProgressDialog.show(v.getContext(), "", "");

                CTHD ct =TrangChuActivity.hoaDon.getCthds().get(position);
                if (ct.getSoluong()==1)
                {
                    cthds.remove(ct);

                    ct.setSoluong(0);
                    notifyDataSetChanged();

                }
                else
                {
                    if (ct.getSoluong()==2)
                    {
                        holder.btnMinus.setImageResource(R.drawable.trash_24);
                    }
                    int giaTienSP = ct.getGiaTien();
                    int giaTien1SP=giaTienSP/ct.getSoluong();
                    ct.setSoluong(ct.getSoluong()-1);
                    holder.tvSL.setText(String.valueOf(ct.getSoluong()));
                    ct.setGiaTien( giaTienSP-giaTien1SP);
                   // holder.tvGiaSP.setText(ct.getGiaTien());


                }
                TrangChuActivity.SaveHoaDon(context,TrangChuActivity.hoaDon);

                viewGioHang.TruSLsp(ct);
                if (cthds.size()==0)
                {
                    TrangChuActivity.XoaHoaDon(context);
                }

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CTHD ct =TrangChuActivity.hoaDon.getCthds().get(position);
                ct.setSoluong(0);
                cthds.remove(ct);
                notifyDataSetChanged();
                TrangChuActivity.SaveHoaDon(context,TrangChuActivity.hoaDon);
                viewGioHang.TruSLsp(ct);
                if (cthds.size()==0)
                {
                    TrangChuActivity.XoaHoaDon(context);
                }

            }
        });
        holder.imgHinhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), CTSPActivity.class);
                //gửi intent trong adapter
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                // requires the FLAG_ACTIVITY_NEW_TASK flag
                it.putExtra("maSP",sp.getMa());
                context.startActivity(it);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cthds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSP,tvGiaSP,tvSize,tvMau,tvSL;
        ImageButton btnMinus,btnAdd;
        SwipeRevealLayout swipeRevealLayout;
        ImageView imgHinhSP;
        Button btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvGiaSP= itemView.findViewById(R.id.tvGiaTienSP);
            tvSize=itemView.findViewById(R.id.tvSize);
            tvSL= itemView.findViewById(R.id.tvSL);
            tvTenSP=itemView.findViewById(R.id.tvTenSP);
            tvMau = itemView.findViewById(R.id.tvMau);
            btnMinus=itemView.findViewById(R.id.btnMinus);
            btnAdd=itemView.findViewById(R.id.btnAdd);
            imgHinhSP=itemView.findViewById(R.id.imgAnhSP);
            btnDelete=itemView.findViewById(R.id.btnRedDelete);
            swipeRevealLayout=itemView.findViewById(R.id.swipe);

        }
    }
}
