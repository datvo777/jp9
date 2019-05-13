package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 20/03/2019.
 */

public class AdapterCTSPmau extends RecyclerView.Adapter<AdapterCTSPmau.ViewHolder> {
    Context context;
    List<CTSP>ctsps;
    List<String>mau;
    List<ViewHolder>viewHolderList= new ArrayList<>();
    public static String tenMau="";

    public AdapterCTSPmau(Context context, List<CTSP> ctsps) {
        this.context = context;
        this.ctsps = ctsps;
        Set<String>mauSet = new HashSet<>();
        for (CTSP ctsp:ctsps)
        {
            mauSet.add(ctsp.getTenMau());
        }
        mau= new ArrayList<>(mauSet);

    }

    @Override
    public AdapterCTSPmau.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_layout_recycler_ctsp_mau,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterCTSPmau.ViewHolder holder, final int position) {
            String Mau = mau.get(position).toString();
            viewHolderList.add(holder);
            if (Mau.equals("Đen"))
            {
                holder.button.setBackgroundResource(R.color.black);
                holder.button.setText(Mau);//để lấy tên màu

            }
            else if (Mau.equals("Trắng"))
            {
                holder.button.setBackgroundResource(R.color.white);
                holder.button.setText(Mau);
            }
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (ViewHolder viewHolder : viewHolderList)
                    {

                        viewHolder.linearLayout.setBackground(context.getDrawable(R.drawable.border_none));//cho border xám

                    }

                    holder.linearLayout.setBackground(context.getDrawable(R.drawable.border_mau)); //border đen
                    tenMau = holder.button.getText().toString();


                }
            });

    }

    @Override
    public int getItemCount() {
        return mau.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {

            super(itemView);
            button = itemView.findViewById(R.id.btnMau);
            linearLayout = itemView.findViewById(R.id.linearMau);

        }
    }
}
