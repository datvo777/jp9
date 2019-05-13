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
import e.datvo_000.jp9shop.View.TrangChu.TimKiem.TimKiemActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 13/03/2019.
 */

public class AdapterTopNewNam extends RecyclerView.Adapter<AdapterTopNewNam.ViewHolder> {
    //Context là thành phần trong ứng dụng android cung cấp quyền truy cập thông tin về các trạng thái của ứng dụng đó. Nó cung cấp các Activities, Fragments và Services truy cập tới các file tài nguyên, hình ảnh, theme, style và các file nằm ngoài ứng dụng.
    Context context;
    List<SanPham> sanPhams;
    int layout;
    public AdapterTopNewNam(Context context, List<SanPham> sanPhams,int layout) {
        this.context = context ;
        this.sanPhams= sanPhams;
        this.layout = layout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView tvTen,tvGiaTien,tvGiamGia;
        LinearLayout linearLayoutTK;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgTopNewNam);
            tvTen = itemView.findViewById(R.id.tvTeudeTopNewNam);
            tvGiaTien= itemView.findViewById(R.id.tvGiaSPTopNewNam);
            linearLayoutTK=itemView.findViewById(R.id.linearTimKiem);

        }
    }
    @Override
    public AdapterTopNewNam.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // gọi ra một thể hiện của LayoutInflate để chuyển mã từ một file layout xml lên view.
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate(int resource, ViewGroup root, boolean attachToRoot) resource trỏ đến layout muốn inflate vào view,
        //Tham số thứ 2 là gốc của cả cây layout mà bạn muốn add view này vào. Khi attachToRoot được bật, view sẽ được gắn vào cây sau khi thực hiện inflate.
        View v = layoutInflater.inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
     return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterTopNewNam.ViewHolder holder, int position) {
        final SanPham sanPham = sanPhams.get(position);
        Picasso.with(context).load(TrangChuActivity.SERVER+"sanpham?imgName="+sanPham.getHinhAnh()).into(holder.imgHinh);
        holder.tvGiaTien.setText(TrangChuActivity.formatter.format(sanPham.getGiaTien())+" đ");
        holder.tvTen.setText(sanPham.getTen());
        holder.linearLayoutTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CTSPActivity.class);
                intent.putExtra("maSP",sanPham.getMa());
               // Log.d("maSPAdapter",String.valueOf(sanPham.getMa()) );
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }


}
