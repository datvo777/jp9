package e.datvo_000.jp9shop.View.DangNhap.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import e.datvo_000.jp9shop.CustomView.PasswordEditText;
import e.datvo_000.jp9shop.Model.DangNhap.ModelDangNhap;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.Presenter.Dangky.PresenterLogicDangKy;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DangNhap.IViewDangKy;
import e.datvo_000.jp9shop.View.DonHang.DiaChiGHActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 24/02/2019.
 */

public class FragmentDangky extends android.support.v4.app.Fragment  implements IViewDangKy,View.OnClickListener,View.OnFocusChangeListener  {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText etHoTen,etMatKhau,etNhapLaiMatKhau,etDiaChiEmail,etPhone;
  ModelDangNhap modelDangNhap;
    TextInputLayout input_etHoTen;
    TextInputLayout input_etMatKhau;
    TextInputLayout input_etNhapLaiMatKhau;
    TextInputLayout input_etDiaChiEmail;
    TextInputLayout input_etPhone;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);
        modelDangNhap = new ModelDangNhap();
        btnDangKy =  view.findViewById(R.id.btnRegister);
        etHoTen =  view.findViewById(R.id.etHoTenDK);
        etMatKhau =  view.findViewById(R.id.etMatkhauDK);
        etNhapLaiMatKhau =  view.findViewById(R.id.etNLMatkhauDK);
        etDiaChiEmail =  view.findViewById(R.id.etEmailDK);
      etPhone =  view.findViewById(R.id.etPhoneDK);
        input_etHoTen =  view.findViewById(R.id.layout_etHoTenDK);
        input_etMatKhau = (TextInputLayout) view.findViewById(R.id.layout_etMK);
        input_etNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.layout_etNLMK);
        input_etDiaChiEmail = (TextInputLayout)view.findViewById(R.id.layout_etEmail);
        input_etPhone = view.findViewById(R.id.layout_etPhone);
        presenterLogicDangKy = new PresenterLogicDangKy(this);
        Typeface font = Typeface.createFromAsset( getActivity().getAssets(), "Roboto-Regular.ttf");
        etHoTen.setTypeface(font);

        btnDangKy.setOnClickListener(this);
        etHoTen.setOnFocusChangeListener(this);
        etNhapLaiMatKhau.setOnFocusChangeListener(this);
        etDiaChiEmail.setOnFocusChangeListener(this);
        etPhone.setOnFocusChangeListener(this);
        etMatKhau.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(),"Đăng ký thành công !",Toast.LENGTH_SHORT).show();
        modelDangNhap.KiemTraDangNhap(getContext() ,etDiaChiEmail.getText().toString(),etMatKhau.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                }catch (Exception e){

                }finally {
                    Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(intent);
                    //TERMINATED
                }
            }
        });
        thread.start();
    }

    @Override
    public void DangKyThatBai(String thongbao) {
        Toast.makeText(getActivity(),thongbao,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnRegister:
                btnDangKy();
                ;break;
        }
    }

    private void btnDangKy(){
        String hoten = etHoTen.getText().toString();
        String email = etDiaChiEmail.getText().toString();
        String matkhau = etMatKhau.getText().toString();
     //   String nhaplaimatkhau = etNhapLaiMatKhau.getText().toString();
        String phone = etPhone.getText().toString();


        if(kiemtrathongtin) {
            KhachHang kh= new KhachHang();
            kh.setName(hoten);
            kh.setEmail(email);
            kh.setPassword(matkhau);
            kh.setPhone(phone);



            presenterLogicDangKy.ThucHienDangKy(kh);
        }else{
            Log.d("kiemtra","Dang ky that bai ");
        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.etHoTenDK:
                //if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") ) {
                        input_etHoTen.setErrorEnabled(true);
                        input_etHoTen.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }
                     else if (!chuoi.trim().contains(" "))
                        {
                            input_etHoTen.setErrorEnabled(true);
                            input_etHoTen.setError("Bạn cần nhập đầy đủ họ và tên !");
                            kiemtrathongtin = false;
                        }
                    else{
                        input_etHoTen.setErrorEnabled(false);
                        input_etHoTen.setError("");
                        kiemtrathongtin = true;
                    }
             //   }
                break;
            case R.id.etPhoneDK:
              //  if(!b){
                    String sdt="";

                    sdt= ((EditText)view).getText().toString();

                    if(sdt.trim().equals("") ){
                        input_etPhone.setErrorEnabled(true);
                        input_etPhone.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }
                    else{
                        boolean match ;
                        match =sdt.matches(DiaChiGHActivity.PARTERN_PHONE);
                        //Matcher matcher;
                        if (!match)
                        {

                            input_etPhone.setErrorEnabled(true);
                            input_etPhone.setError("SĐT không đúng định dạng mời bạn nhập lại !");
                            kiemtrathongtin = false;

                        }
                        else {

                            input_etPhone.setErrorEnabled(false);
                            input_etPhone.setError("");
                            kiemtrathongtin = true;
                        }

                    }
             //   }
                break;

            case R.id.etEmailDK:
               // if(!b){

                     chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") ){
                        input_etDiaChiEmail.setErrorEnabled(true);
                        input_etDiaChiEmail.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = chuoi.matches(DiaChiGHActivity.PARTERN_EMAIL);
                        if(!kiemtraemail){
                            input_etDiaChiEmail.setErrorEnabled(true);
                            input_etDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            input_etDiaChiEmail.setErrorEnabled(false);
                            input_etDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
               // }
                break;

            case R.id.etMatkhauDK:
                 chuoi = ((PasswordEditText)view).getText().toString();
                

                boolean kt = chuoi.matches(PasswordEditText.MATCHER_PATTERN);
                if(chuoi.trim().equals("") ){
                    input_etMatKhau.setErrorEnabled(true);
                    input_etMatKhau.setError("Bạn chưa nhập mục này !");
                    kiemtrathongtin = false;
                }else {

                    if (!kt) {
                        input_etMatKhau.setErrorEnabled(true);//mở hiển thị lỗi
                        input_etMatKhau.setError("Mật khẩu phải có ít nhất 6 ký tự , 1 chữ hoa và 1 số");
                        kiemtrathongtin = false;
                    } else {
                        input_etMatKhau.setErrorEnabled(false);
                        input_etMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;

            case R.id.etNLMatkhauDK:
               // if(!b){
                     chuoi = ((EditText)view).getText().toString();
                    String matkhau = etMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_etNhapLaiMatKhau.setErrorEnabled(true);

                        input_etNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        input_etNhapLaiMatKhau.setErrorEnabled(false);
                        input_etNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
               // }

                break;

        }
    }
}
