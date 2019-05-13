package e.datvo_000.jp9shop.View.DonHang;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import e.datvo_000.jp9shop.Model.DangNhap.ModelKhachHang;
import e.datvo_000.jp9shop.Model.DangNhap.ModelSDC;
import e.datvo_000.jp9shop.Model.Object.SoDiaChi;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.GioHang.GioHangActivity;
import e.datvo_000.jp9shop.View.TaiKhoan.SoDiaChiActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class DiaChiGHActivity extends AppCompatActivity {
    EditText etHo,etTen,etSDT,etDiachi,etEmail;
   int maDC,macdinh;
   public static final String PARTERN_PHONE = "(09|07|03|05|08){1}([0-9]{8})";//Regular Expression biểu thức chính quy,một chuỗi các kí tự miêu tả một bộ các chuỗi ki tự khác, theo những quy tắc và cú pháp nhất định.
    public static final   String PARTERN_EMAIL=  "[a-zA-Z0-9\\+\\.\\_\\%\\-]{1,256}" +  //[+]
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + //sau @ là ít nhất 2 kí tự
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" + //(.[])xuất hiện 1 hoặc nhìu lần
            ")+"; //+ 1 hoặc nhiều kí tự ,() : gom nhóm
    Button btnLuu,btnThem;
    boolean check= true;
    String from;
    String chucnang;
    //Pattern pattern;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen=etHo.getText().toString()+" " +etTen.getText().toString();
                String sdt =etSDT.getText().toString();
                String diachi=etDiachi.getText().toString();
                if (etHo.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập họ",Toast.LENGTH_LONG).show();
                    etHo.requestFocus();
                    return;
                }

                if (etTen.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập tên",Toast.LENGTH_LONG).show();
                    etTen.requestFocus();
                    return;
                }
                if (etSDT.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập SĐT",Toast.LENGTH_LONG).show();
                    etSDT.requestFocus();
                    return;
                }
                if (etDiachi.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập địa chỉ",Toast.LENGTH_LONG).show();
                    etDiachi.requestFocus();
                    return;
                }

                if (check==false)
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đúng định dạng SĐT",Toast.LENGTH_LONG).show();
                    etSDT.requestFocus();
                    return;
                }
                SoDiaChi sdc= new SoDiaChi();

                sdc.setName(hoTen);
                sdc.setEmail(TrangChuActivity.acc.getEmail());
                sdc.setAddress(diachi);
                sdc.setPhone(sdt);

                if (chucnang.equals("edit"))
                {
                    sdc.setMa(maDC);
                    sdc.setMacdinh(macdinh);
                    ModelSDC.EditSDC(sdc);
                }
                else if (chucnang.equals("themMoi"))
                {
                    sdc.setMacdinh(1);
                    ModelSDC.AddSDC(sdc);
                }
                else
                {
                    ModelSDC.AddSDC(sdc);
                }
                TrangChuActivity.acc=  ModelKhachHang.GetAccountByEmailKH(TrangChuActivity.acc.getEmail());

                if (from.contains("XacNhan"))
                {
                    Intent it = new Intent(DiaChiGHActivity.this,XacNhanDonHangActivity.class);
                    startActivity(it);
                }
                else if (from.contains("SoDiaChi"))
                {
                    Intent it = new Intent(DiaChiGHActivity.this,SoDiaChiActivity.class);
                    startActivity(it);
                }



            }
        });
        etSDT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String sdt =  etSDT.getText().toString();
                boolean match ;
                match =sdt.matches(PARTERN_PHONE);
                //Matcher matcher;
                if (!match)
                {

                    etSDT.setError("SĐT không đúng định dạng ");
                    check =false;

                }
                else {

                    check=true;
                }

            }
        });
    //    btnThem.setOnClickListener();
//        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                String email = etEmail.getText().toString();
//                boolean match ;
//                match = email.matches(PARTERN_EMAIL);
//                if (!match)
//                {
//                    etEmail.setError("Email không đúng định dạng");
//                    check=false;
//                }
//                else
//                {
//                    check =true;
//                }
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//
//       if (from.contains("XacNhanDonHang"))
//       {
//           Intent i = new Intent(this, GioHangActivity.class);
//           startActivity(i);
//       }
//       else if (from.contains("SoDiaChi"))
//       {
//           Intent i = new Intent(this, SoDiaChiActivity.class);
//           startActivity(i);
//       }
//    }

    private void addControls() {
        setContentView(R.layout.layout_diachigiaohang);
        etHo = findViewById(R.id.etHo);
        etTen=findViewById(R.id.etTen);
        etDiachi=findViewById(R.id.etDiachi);
        etSDT=findViewById(R.id.etSDT);
      //  etEmail=findViewById(R.id.etEmail);
        btnLuu=findViewById(R.id.btnLuu);
     //   btnThem=findViewById(R.id.btnThem);
        addProcess();

    }

    private void addProcess() {
        Intent it = getIntent();
       from= it.getStringExtra("from");
       chucnang=it.getStringExtra("chucnang");

        if (chucnang.equals("edit"))
        {

            SoDiaChi sdc = ModelSDC.ParseJSONsdc(it.getStringExtra("sdc"));
            maDC =sdc.getMa();
            macdinh=sdc.getMacdinh();
            etHo.setText(sdc.getName().split(" ")[0]);
            etTen.setText(sdc.getName().split(" ")[1]);
            etSDT.setText(sdc.getPhone());
            etDiachi.setText(sdc.getAddress());

        }
    }

}
