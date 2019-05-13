package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TimKiem.TimKiemActivity;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class AdapterKeywordNam extends RecyclerView.Adapter<AdapterKeywordNam.ViewHolder> {
    Context context;
    List<String> stringList;
    public AdapterKeywordNam(Context context, List<String>stringList)
    {
        this.context = context;
         this.stringList = stringList;
    }
    //chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tvKeywordNam);

        }

    }
    //chạy đầu tiên
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_recycler_keyword_nam,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //chạy thứ 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TimKiemActivity.class);
                intent.putExtra("gianhCho","nam");
                intent.putExtra("tukhoa",holder.textView.getText().toString());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
