package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import e.datvo_000.jp9shop.Model.ModelSanPham;
import e.datvo_000.jp9shop.Model.Object.CTHD;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class AdapterCTDH extends RecyclerView.Adapter<AdapterCTDH.ViewHolder> {
    Context context;
    List<CTHD>cthdList;

    public AdapterCTDH(Context context, List<CTHD> cthdList) {
        this.context = context;
        this.cthdList = cthdList;
    }

    @Override
    public AdapterCTDH.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_ctdh,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterCTDH.ViewHolder holder, int position) {
        CTHD cthd = cthdList.get(position);
        holder.tvGiaTien.setText(TrangChuActivity.formatter.format( cthd.getGiaTien())+" đ");
        holder.tvTenSP.setText(cthd.getTenSP());
        holder.tvSL.setText("x" +String.valueOf(cthd.getSoluong()));
        holder.tvSize.setText(cthd.getTenSize());
        holder.tvMau.setText(cthd.getTenMau());
        SanPham sp = ModelSanPham.GetSPByMaCTSP(cthd.getMaCTSP());
        Picasso.with(holder.imgHinhSP.getContext()).load(TrangChuActivity.SERVER+"sanpham?imgName="+sp.getHinhAnh()).into(holder.imgHinhSP);

    }

    @Override
    public int getItemCount() {
        return cthdList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMau,tvSize,tvTenSP,tvSL,tvGiaTien;
        ImageView imgHinhSP;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTienSP);
            tvMau =itemView.findViewById(R.id.tvMau);
            tvSize=itemView.findViewById(R.id.tvSize);
            tvSL =itemView.findViewById(R.id.tvSL);
            tvTenSP=itemView.findViewById(R.id.tvTenSP);
            imgHinhSP = itemView.findViewById(R.id.imgAnhSP);

        }
    }
}
