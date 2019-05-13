package e.datvo_000.jp9shop.View.TrangChu.TimKiem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

import e.datvo_000.jp9shop.Adapter.AdapterTopNewNam;
import e.datvo_000.jp9shop.Model.Object.SanPham;
import e.datvo_000.jp9shop.Presenter.TimKiem.PresenterTimKiem;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TimKiem.ViewTimKiem;

/**
 * Created by datvo_000 on 14/03/2019.
 */

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem , android.support.v7.widget.SearchView.OnQueryTextListener{
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterTimKiem presenterTimKiem;
    String tukhoa="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControl();
        Intent intent = getIntent();
        tukhoa = intent.getStringExtra("tukhoa");
        String gianhCho="";
         gianhCho = intent.getStringExtra("gianhCho");
        Log.d("tukoa",tukhoa);
        presenterTimKiem = new PresenterTimKiem(this);
        if(gianhCho.equalsIgnoreCase("nam"))
        {
            presenterTimKiem.TimKiemSPNam(tukhoa);
        }
        else if (gianhCho.equalsIgnoreCase("nữ"))
        {
            presenterTimKiem.TimKiemSPNu(tukhoa);
        }
        else
        {
            presenterTimKiem.TimkiemSP(tukhoa);
        }

    }



    private void addControl() {
        setContentView(R.layout.layout_timkiem);
        recyclerView = findViewById(R.id.recyclerTimkiem);
        toolbar = findViewById(R.id.tbTimKiem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //ẩn title

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);//dùng để gắn Menu XML Resource vào ứng dụng
        MenuItem itsearch = menu.findItem(R.id.itSearch);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView ) MenuItemCompat.getActionView(itsearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQuery(tukhoa,false);
        searchView.clearFocus(); //method to unfocus it
       // searchView.setMaxWidth(toolbar.getMeasuredWidth());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void TimKiemThanhCong(List<SanPham>sanPhams) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopNewNam adapterTopNewNam = new AdapterTopNewNam(this,sanPhams,R.layout.custom_linearlayout_timkiem);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopNewNam);
    }

    @Override
    public void TimKiemThatBai() {
        Toast.makeText(this,"Sản phẩm không tồn tại !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("query",query);
        presenterTimKiem.TimkiemSP(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
