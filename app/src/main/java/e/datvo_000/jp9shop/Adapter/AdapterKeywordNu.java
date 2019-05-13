package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TimKiem.TimKiemActivity;

public class AdapterKeywordNu  extends RecyclerView.Adapter<AdapterKeywordNu.ViewHolder> {
    Context context;
    List<String> stringList;

    public AdapterKeywordNu(Context context, List<String>stringList)
    {
        this.context = context;
        this.stringList = stringList;
    }
    //chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tvKeywordNu);

        }

    }
    //chạy đầu tiên
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_keyword_nu,parent,false);

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
                intent.putExtra("gianhCho","nữ");
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

