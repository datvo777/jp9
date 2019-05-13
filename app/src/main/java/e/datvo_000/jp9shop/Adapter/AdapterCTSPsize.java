package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 18/03/2019.
 */

public class AdapterCTSPsize extends RecyclerView.Adapter<AdapterCTSPsize.ViewHolder> {
    Context context;
    List<CTSP>ctsps;
    List<String>ctspsetSize;
    List<ViewHolder>viewHolderList= new ArrayList<>();
    public static String tenSize="";



    public AdapterCTSPsize(Context context,List<CTSP>ctsps) {

        this.context = context;
        this.ctsps = ctsps;
       Set<String> ctspset = new HashSet<>();//chuyển qua set để ko bị trùng
        for (CTSP ctsp:ctsps)
        {
            ctspset.add(ctsp.getTenSize());
        }
        ctspsetSize= new ArrayList<>(ctspset);

    }

    @Override
    public AdapterCTSPsize.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.custom_layout_recycler_ctsp_size,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterCTSPsize.ViewHolder holder, final int position) {
        holder.button.setText(ctspsetSize.get(position).toString());

        viewHolderList.add(holder);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (ViewHolder viewHolder : viewHolderList)
                {

                    viewHolder.button.setBackground(context.getDrawable(R.color.white));//cho tất cả nền trắng

                }

                v.setBackground(context.getDrawable(R.drawable.border_size_color));//bo tròn size dc chọn
                tenSize = holder.button.getText().toString();


            }
        });

    }

    @Override
    public int getItemCount() {
        return ctspsetSize.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.txtSize);

           // textView.setSelected(true);
        }
    }
}
