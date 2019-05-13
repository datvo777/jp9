package e.datvo_000.jp9shop.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import e.datvo_000.jp9shop.Adapter.AdapterNam;
import e.datvo_000.jp9shop.Adapter.AdapterNu;
import e.datvo_000.jp9shop.Model.Object.ForMW;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.Presenter.Trangchu.Nu.PresenterNu;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.ViewNam;

/**
 * Created by datvo_000 on 15/02/2019.
 */

public class FragmentNu extends Fragment  implements ViewNam {
    RecyclerView recyclerView;
    List<ForMW> forMWList;
    PresenterNu presenterNu;
    AdapterNu adapterNu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_nu,container,false);
        recyclerView =view.findViewById(R.id.recyclerNam);
        forMWList= new ArrayList<>();
        presenterNu = new PresenterNu(this);

        presenterNu.LayDS();

        return view;

    }

    @Override
    public void HienThiDs(List<String> keyword, List<SanPham> sanPhamsNew, List<SanPham>sanPhamsSell) {
        ForMW forMW = new ForMW();
        forMW.setKey(keyword);
        forMW.setSpNews(sanPhamsNew);
        forMW.setSpbanchays(sanPhamsSell);
        forMWList.add(forMW);
        adapterNu = new AdapterNu(getContext(),forMWList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapterNu);
        //adapterNu.notifyDataSetChanged();
    }




}