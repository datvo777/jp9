package e.datvo_000.jp9shop.View.DangNhap.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import e.datvo_000.jp9shop.Model.DangNhap.ModelDangNhap;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 24/02/2019.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener {
    Button btnLoginFB;
    Button btnLogin;
    ModelDangNhap modelDangNhap ;
    EditText etEmail;
    EditText etMatKhau;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);
        modelDangNhap = new ModelDangNhap();
        btnLogin = view.findViewById(R.id.btnLoginDN);
        etEmail = view.findViewById(R.id.edEmailDN);
        etMatKhau = view.findViewById(R.id.edMatKhauDN);
        btnLogin.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
                case R.id.btnLoginDN:
                    String tendangnhap = etEmail.getText().toString();
                    Log.d("email",tendangnhap);
                    String matkhau = etMatKhau.getText().toString();
                    boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                    if(kiemtra){
                        Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                        startActivity(iTrangChu);
                    }else{
                        Toast.makeText(getActivity(),"Tên đăng nhập hoặc mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                    }
                    ;break;
            }


    }
}
