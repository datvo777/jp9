package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class AdapterHinhSP extends RecyclerView.Adapter<AdapterHinhSP.ViewHolder> {
    Context context ;
    List<SanPham>list;

    public AdapterHinhSP(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_hinhsp,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         SanPham sp = list.get(position);
        Picasso.with(holder.imgHinhSP.getContext()).load(TrangChuActivity.SERVER+"sanpham?imgName="+sp.getHinhAnh()).into(holder.imgHinhSP);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP=itemView.findViewById(R.id.imgHinhSanPham);
        }
    }
}
