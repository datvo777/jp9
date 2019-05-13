package e.datvo_000.jp9shop.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Model.DangNhap.ModelSDC;
import e.datvo_000.jp9shop.Model.ModelCTSP;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DonHang.DiaChiGHActivity;
import e.datvo_000.jp9shop.View.TaiKhoan.SoDiaChiActivity;

public class AdapterSDC extends RecyclerView.Adapter<AdapterSDC.ViewHolder> {
    Context context;
    List<SoDiaChi> list;


    CheckedTextView ctvMacDinh;
    TextView tvMacDinh;

    public AdapterSDC(Context context, List<SoDiaChi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_sodiachi, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SoDiaChi sdc = list.get(position);
        holder.tvDiaChi.setText(sdc.getAddress());
        holder.tvHoten.setText(sdc.getName());
        holder.tvSDT.setText(sdc.getPhone());

        if (sdc.getMacdinh() == 1) {
            holder.ctvMD.setChecked(true);
            ctvMacDinh = holder.ctvMD;

            holder.tvMD.setText("Mặc định");
            tvMacDinh = holder.tvMD;
            holder.tvMD.setTextColor(Color.BLACK);
        } else {
            holder.ctvMD.setChecked(false);
            holder.tvMD.setText("Đặt làm địa chỉ mặc định");
            holder.tvMD.setTextColor(Color.GRAY);

        }
        holder.ctvMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctvMacDinh == holder.ctvMD)return;
                ctvMacDinh.setChecked(false);
                tvMacDinh.setText("Đặt làm địa chỉ mặc định");
                tvMacDinh.setTextColor(Color.GRAY);

                for (SoDiaChi dc : list) {
                    if (dc.getMacdinh() == 1) {
                        dc.setMacdinh(0);
                        ModelSDC.EditSDC(dc);
                        break;
                    }
                }
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.show();
                sdc.setMacdinh(1);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
                        } catch (Exception e) {
                        } finally {
                            ModelSDC.EditSDC(sdc);
                            progressDialog.cancel();
                        }
                    }
                });
                thread.start();
                holder.ctvMD.setChecked(true);
                holder.tvMD.setText("Mặc định");
                tvMacDinh = holder.tvMD;
                holder.tvMD.setTextColor(Color.BLACK);
                ctvMacDinh = holder.ctvMD;
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 1) {
                    Toast.makeText(context, "Phải có ít nhất 1 địa chỉ trong sổ địa chỉ", Toast.LENGTH_LONG).show();
                    return;
                }
                if (holder.ctvMD.isChecked())
                {
                    Toast.makeText(context, "Không thể xóa địa chỉ mặc định", Toast.LENGTH_LONG).show();
                    return;
                }
                new AlertDialog.Builder(context)
                        .setTitle("Cảnh báo")
                        .setMessage("Bạn có chắc muốn xóa địa chỉ này không ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {//nut tích cực -nut yes
                            public void onClick(DialogInterface dialog, int which) {
                                ModelSDC.DeleteSDC(sdc.getMa());
                                list.remove(sdc);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)//khong lam gi het
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                notifyDataSetChanged();
                Toast.makeText(context,"Xóa địa chỉ thành công !",Toast.LENGTH_LONG);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, DiaChiGHActivity.class);
                it.putExtra("from", "SoDiaChi");
                it.putExtra("chucnang", "edit");
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    it.putExtra("sdc", objectMapper.writeValueAsString(sdc));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                context.startActivity(it);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHoten, tvSDT, tvDiaChi, tvMD;
        CheckedTextView ctvMD;
        Button btnEdit;
        ImageView imgXoa;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHoten = itemView.findViewById(R.id.tvHoTenDC);
            tvDiaChi = itemView.findViewById(R.id.tvDiachi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            tvMD = itemView.findViewById(R.id.tvMacDinh);
            ctvMD = itemView.findViewById(R.id.ctvMacDinh);
            imgXoa = itemView.findViewById(R.id.imgXoa);
            btnEdit = itemView.findViewById(R.id.btnNext);
        }
    }
}
