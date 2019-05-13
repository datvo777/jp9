package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.ChiTietSP.CTSPActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 14/03/2019.
 */
//bắt buộc phải sử dụng ViewHolder để cải thiện performance.
//Mục đích sử dụng ViewHolder để tái sử dụng View nhằm tránh việc tạo View mới và findViewById quá nhiều
public class AdapterTopSellerNam extends RecyclerView.Adapter<AdapterTopSellerNam.ViewHolder> {
    Context context;
    List<SanPham> sanPhams;
    public AdapterTopSellerNam(Context context, List<SanPham> sanPhams) {
        this.context = context ;
        this.sanPhams= sanPhams;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView tvTen,tvGiaTien;
        LinearLayout linear ;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgTopSellNam);
            tvTen = itemView.findViewById(R.id.tvTeudeTopSellNam);
            tvGiaTien= itemView.findViewById(R.id.tvGiaSPTopSellNam);
            linear =itemView.findViewById(R.id.linearTopSell);
        }
    }
    @Override
    //phương thức dùng để tạo view mới cho RecyclerView. Nếu RecyclerView đã cached lại View thì phương thức này sẽ không gọi.
    public AdapterTopSellerNam.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // gọi ra một thể hiện của LayoutInflate để chuyển mã từ một file layout xml lên view.
        //inflate(int resource, ViewGroup root, boolean attachToRoot) resource trỏ đến layout muốn inflate vào view,
        //Tham số thứ 2 là gốc của cả cây layout mà bạn muốn add view này vào.
        // Khi attachToRoot được bật, view sẽ được gắn vào cây sau khi thực hiện inflate.
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_recyler_topsell_nam,parent,false);
        AdapterTopSellerNam.ViewHolder viewHolder = new AdapterTopSellerNam.ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTopSellerNam.ViewHolder holder, int position) {
      final   SanPham sanPham = sanPhams.get(position);
        Picasso.with(context).load(TrangChuActivity.SERVER+"sanpham?imgName="+sanPham.getHinhAnh()).into(holder.imgHinh);
        holder.tvGiaTien.setText(TrangChuActivity.formatter.format( sanPham.getGiaTien())+" đ");
        holder.tvTen.setText(sanPham.getTen());
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CTSPActivity.class);
                intent.putExtra("maSP",sanPham.getMa());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return sanPhams.size();
    }
}
