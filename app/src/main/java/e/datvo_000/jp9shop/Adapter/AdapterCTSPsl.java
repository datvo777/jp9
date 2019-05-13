package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.CTSP;
import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 17/03/2019.
 */

public class AdapterCTSPsl extends RecyclerView.Adapter<AdapterCTSPsl.ViewHolder> {
    Context context;
    List<CTSP> stringList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView ;
        public ViewHolder(View itemView) {

            super(itemView);
            textView= itemView.findViewById(R.id.txtSLSanPham);


        }
    }
    public AdapterCTSPsl(Context context, List<CTSP>stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_ctsp_sl,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str=   "Màu : "+stringList.get(position).getTenMau()+" , Size : "+stringList.get(position).getTenSize()+" còn "+stringList.get(position).getSoluong()+" sản phẩm";
            holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
       return stringList.size();
    }


}
