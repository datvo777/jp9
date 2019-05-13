package e.datvo_000.jp9shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import e.datvo_000.jp9shop.Model.API.ModelDanhMuc;
import e.datvo_000.jp9shop.Model.Object.DanhMuc;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TimKiem.TimKiemActivity;

/**
 * Created by datvo_000 on 21/02/2019.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String>gianhCho;
    List<DanhMuc> danhmucss;
    ViewHolderMenu viewHolderMenu;
    HashMap<String,List<DanhMuc>>dataChild= new HashMap<>();

    public ExpandAdapter(Context context, List<DanhMuc> danhmucss,List<String>gianhCho){
        this.context = context;
        this.danhmucss = danhmucss;
        this.gianhCho=gianhCho;
        dataChild.put(gianhCho.get(0),danhmucss);
        dataChild.put(gianhCho.get(1),danhmucss);
        ModelDanhMuc modelDanhMuc = new ModelDanhMuc();
    }

    @Override
    public int getGroupCount() {
        return gianhCho.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        return dataChild.get(gianhCho.get(vitriGroupCha)).size() ;
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return gianhCho.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return dataChild.get(gianhCho.get(vitriGroupCha)).get(vitriGroupCon);
    }

    //Nam or Nu
    @Override
    public long getGroupId(int vitriGroupCha) {
        return vitriGroupCha;
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return vitriGroupCon;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtTen;
        ImageView hinhMenu;
        LinearLayout linearLayout;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;
        if(viewGroupCha == null){
            viewHolderMenu = new ViewHolderMenu();

            viewGroupCha = LayoutInflater.from(context).inflate(R.layout.group_cha_layout,viewGroup,false);

            viewHolderMenu.txtTen =  viewGroupCha.findViewById(R.id.txtDanhmuc);
            viewHolderMenu.hinhMenu =  viewGroupCha.findViewById(R.id.imMenuPlus);

            viewGroupCha.setTag(viewHolderMenu);//lưu lại  giá trị của view
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();// doAction(1); // 1 for button1, 2 for button2, etc.->doAction(v.getTag());
        }
        viewHolderMenu.txtTen.setText(gianhCho.get(vitriGroupCha));
        int demsanphamcon = getChildrenCount(vitriGroupCha);

        if(demsanphamcon > 0){
            viewHolderMenu.hinhMenu.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.hinhMenu.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.remove_black);
            viewGroupCha.setBackgroundResource(R.color.gray);

        }else{
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.add_black);
            viewGroupCha.setBackgroundResource(R.color.white);
        }



        return viewGroupCha;
    }


    @Override
    public View getChildView(final int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCon = view;
        if(viewGroupCon == null){
            viewHolderMenu = new ViewHolderMenu();
            viewGroupCon = LayoutInflater.from(context).inflate(R.layout.group_con_layout,null);
            viewHolderMenu.linearLayout = viewGroupCon.findViewById(R.id.linearGroupCon);

            viewHolderMenu.txtTen =  viewGroupCon.findViewById(R.id.txtdm);


            viewGroupCon.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCon.getTag();
        }
     final    DanhMuc dm = (DanhMuc) getChild(vitriGroupCha,vitriGroupCon);
       viewHolderMenu.txtTen.setText(dm.getTen());
       viewHolderMenu.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it = new Intent(context, TimKiemActivity.class);
               it.putExtra("tukhoa",dm.getTen());
               it.putExtra("gianhCho",gianhCho.get(vitriGroupCha));
               context.startActivity(it);
           }
       });


        return viewGroupCon;
    }



    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


}
