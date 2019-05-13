package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Model.ModelSanPham;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.HoaDon;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DonHang.CTDHActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class AdapterDH extends RecyclerView.Adapter<AdapterDH.ViewHolder> {
    List<HoaDon>list ;
    Context context;

    public AdapterDH(List<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterDH.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_donhang,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterDH.ViewHolder holder, final int position) {
        final HoaDon hd = list.get(position);
        holder.tvMaDH.setText(String.valueOf( hd.getMa()));
        holder.tvTrangThai.setText(hd.getTinhtrang());
        holder.tvSoSP.setText(String.valueOf(hd.getCthds().size()));
        holder.tvTongCong.setText(TrangChuActivity.formatter.format(hd.getTongTien())+" đ");
        if (holder.tvTrangThai.getText().toString().contains("nhận"))
        {
            holder.imgTrangThai.setImageResource(R.drawable.circle_blue_16px);
        }
        else if (holder.tvTrangThai.getText().toString().contains("Đang"))
        {
            holder.imgTrangThai.setImageResource(R.drawable.circle_yellow_16px);

        }
        else
        {
            holder.imgTrangThai.setImageResource(R.drawable.checked_16px);
        }
        List<SanPham>spList = new ArrayList<>();
        for (CTHD cthd:hd.getCthds()) {
            spList.add( ModelSanPham.GetSPByMaCTSP(cthd.getMaCTSP()));

        }
        AdapterHinhSP adapterHinhSP = new AdapterHinhSP(context,spList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false);
        holder.recyclerHinh.setLayoutManager(layoutManager);
        holder.recyclerHinh.setAdapter(adapterHinhSP);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, CTDHActivity.class);
                it.putExtra("position",position);//vitri cua donhang
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTongCong,tvSoSP,tvTrangThai,tvMaDH;
        ImageView imgTrangThai;
        LinearLayout linearLayout;
        RecyclerView recyclerHinh;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linearDonHang);
            tvMaDH = itemView.findViewById(R.id.tvMaDonHang);
            tvTongCong = itemView.findViewById(R.id.tvTongCong);
            tvSoSP = itemView.findViewById(R.id.tvSoSP);
            tvTrangThai=itemView.findViewById(R.id.tvTrangThai);
            imgTrangThai=itemView.findViewById(R.id.imgTrangThai);
            recyclerHinh = itemView.findViewById(R.id.recyclerHinhSP);
        }
    }
}
