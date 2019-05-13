package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import e.datvo_000.jp9shop.Model.Object.ForMW;
import e.datvo_000.jp9shop.R;

/**
 * Created by datvo_000 on 10/03/2019.
 */

public class AdapterNam extends RecyclerView.Adapter<AdapterNam.ViewHolder> {
    Context context;
    List<ForMW> forMWS;

    public AdapterNam(Context context, List<ForMW> forMWS)
    {
        this.context = context;
        this.forMWS = forMWS;
    }
    //chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {

     public    ImageView imgBanner;
       public RecyclerView recyclerViewKey,recyclerViewSPBanChay,recyclerViewSPKM,recyclerViewNew;
        public ViewHolder(View itemView) {
            super(itemView);

            recyclerViewKey = itemView.findViewById(R.id.recyclerTuKhoaNam);
            recyclerViewSPBanChay = itemView.findViewById(R.id.recyclerTopSellerNam);
            
            recyclerViewNew = itemView.findViewById(R.id.recyclerTopNewNam);
            imgBanner = itemView.findViewById(R.id.imgBannerNam);
        }
    }
    //chạy đầu tiên
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_recylerview_nam,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //chạy thứ 3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForMW forMW = forMWS.get(position);
        //hot key
        AdapterKeywordNam adapterKeywordNam = new AdapterKeywordNam(context,forMW.getKey());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager (context,2,GridLayoutManager.HORIZONTAL,false);//spanCount:số dòng
        holder.recyclerViewKey.setLayoutManager(layoutManager);
        holder.recyclerViewKey.setAdapter(adapterKeywordNam);
       // adapterKeywordNam.notifyDataSetChanged();//notifyDataSetChanged only works if you use the add(), insert(), remove(), and clear() on the Adapter.

        //topnew
        AdapterTopNewNam adapterTopNewNam = new AdapterTopNewNam(context,forMW.getSpNews(),R.layout.custom_layout_recycler_topnew_nam);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager (context,2,GridLayoutManager.HORIZONTAL,false);
          holder.recyclerViewNew.setLayoutManager(layoutManager1);
        holder.recyclerViewNew.setAdapter(adapterTopNewNam);
        //adapterTopNewNam.notifyDataSetChanged();
        //sell
        AdapterTopSellerNam adapterTopSellerNam = new AdapterTopSellerNam(context,forMW.getSpbanchays());
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager (context,1,GridLayoutManager.HORIZONTAL,false);
        holder.recyclerViewSPBanChay.setLayoutManager(layoutManager2);
        holder.recyclerViewSPBanChay.setAdapter(adapterTopSellerNam);
       // adapterTopSellerNam.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return forMWS.size();
    }


}
