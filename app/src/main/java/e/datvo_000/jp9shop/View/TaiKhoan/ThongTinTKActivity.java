package e.datvo_000.jp9shop.View.TaiKhoan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import e.datvo_000.jp9shop.CustomView.PasswordEditText;
import e.datvo_000.jp9shop.Model.DangNhap.ModelKhachHang;
import e.datvo_000.jp9shop.Model.Object.KhachHang;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.DonHang.DiaChiGHActivity;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class ThongTinTKActivity extends AppCompatActivity implements View.OnFocusChangeListener{
    TextView tvSave;
    LinearLayout linearMK;
    CheckBox cbMK;
    EditText etHoTen,etPhone,etEmail,etMKcu,etMKmoi,etNLMKmoi;
    TextInputLayout input_etMKcu,input_etMKmoi,input_etNLMKmoi;
    KhachHang acc = TrangChuActivity.acc;
    boolean isKiemtrahoten=true;
    boolean kiemtrathongtin=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {
        cbMK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    linearMK.setVisibility(View.VISIBLE);
                }
                else
                {
                    linearMK.setVisibility(View.GONE);
                }
            }
        });
        etMKmoi.setOnFocusChangeListener(this);
        etNLMKmoi.setOnFocusChangeListener(this);
        etMKcu.setOnFocusChangeListener(this);
        etEmail.setOnFocusChangeListener(this);
        etPhone.setOnFocusChangeListener(this);
        etHoTen.setOnFocusChangeListener(this);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemtrathongtin&&cbMK.isChecked()||isKiemtrahoten&&!cbMK.isChecked())
                {
                    acc.setName(etHoTen.getText().toString());

                    acc.setPhone(acc.getPhone().trim());//gui co khoang trang ko chịu ("099900    ")
                    if (cbMK.isChecked()&&kiemtrathongtin&&!etMKmoi.getText().toString().equals(""))
                    {
                        acc.setPassword(etMKmoi.getText().toString());
                    }

                   if (ModelKhachHang.EditAcc(acc))
                   {
                       Toast.makeText(ThongTinTKActivity.this,"Thay đổi tài khoản thành công",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(ThongTinTKActivity.this,"Thay đổi tài khoản thất bại",Toast.LENGTH_LONG).show();
                   }
                }
            }
        });

    }

    private void addControls() {
        setContentView(R.layout.layout_tttk);
        etHoTen = findViewById(R.id.etHotenTK);
        etEmail = findViewById(R.id.etEmailTK);
        etPhone =findViewById(R.id.etPhoneTK);
        etMKcu = findViewById(R.id.etMatKhauCu);
        etMKmoi=findViewById(R.id.etMatKhauMoi);
        etNLMKmoi=findViewById(R.id.etNhaplaiMK);
        cbMK = findViewById(R.id.cbMK);
        linearMK = findViewById(R.id.linearMK);
        tvSave = findViewById(R.id.tvSave);
        input_etMKcu=findViewById(R.id.inputMKcu);
        input_etMKmoi=findViewById(R.id.inputMKmoi);
        input_etNLMKmoi=findViewById(R.id.inputNLMKmoi);
        addProcess();

    }

    private void addProcess() {
        etHoTen.setText(acc.getName());
        etPhone.setText(acc.getPhone());
        etEmail.setText(acc.getEmail());

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.etHotenTK:
                //if(!b){
                String chuoi = ((EditText)view).getText().toString();
                if(chuoi.trim().equals("") ){

                    etHoTen.setError("Bạn chưa nhập mục này !");
                    isKiemtrahoten =false;
                    kiemtrathongtin = false;
                }else{
                    isKiemtrahoten=true;
                    kiemtrathongtin = true;
                }
                //   }
                break;
            case R.id.etPhoneDK:
                //  if(!b){
                String sdt="";

                sdt= ((EditText)view).getText().toString();

                if(sdt.trim().equals("") ){

                    etPhone.setError("Bạn chưa nhập mục này !");
                    kiemtrathongtin = false;
                }
                else{
                    boolean match ;
                    match =sdt.matches(DiaChiGHActivity.PARTERN_PHONE);
                    //Matcher matcher;
                    if (!match)
                    {


                        etPhone.setError("SĐT không đúng định dạng mời bạn nhập lại !");
                        kiemtrathongtin = false;

                    }
                    else {


                        kiemtrathongtin = true;
                    }

                }
                //   }
                break;

            case R.id.etEmailDK:
                // if(!b){

                chuoi = ((EditText)view).getText().toString();

                if(chuoi.trim().equals("") ){

                    etEmail.setError("Bạn chưa nhập mục này !");
                    kiemtrathongtin = false;
                }else{

                    Boolean kiemtraemail = chuoi.matches(DiaChiGHActivity.PARTERN_EMAIL);
                    if(!kiemtraemail){

                        etEmail.setError("Đây không phải là địa chỉ Email !");
                        kiemtrathongtin = false;
                    }else{

                        kiemtrathongtin = true;
                    }
                }
                // }
                break;
            case R.id.etMatKhauCu:
                chuoi = ((PasswordEditText)view).getText().toString();



                if(chuoi.trim().equals("") ){
                    input_etMKcu.setErrorEnabled(true);
                    input_etMKcu.setError("Bạn chưa nhập mục này !");
                    kiemtrathongtin = false;
                }else {

                    if (!acc.getPassword().equals(chuoi)) {
                        input_etMKcu.setErrorEnabled(true);
                        input_etMKcu.setError("Bạn đã nhập sai mật khẫu cũ !");
                        kiemtrathongtin = false;
                    } else {
                        input_etMKcu.setErrorEnabled(false);
                        input_etMKcu.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;
            case R.id.etMatKhauMoi:
                chuoi = ((PasswordEditText)view).getText().toString();


                boolean kt = chuoi.matches(PasswordEditText.MATCHER_PATTERN);
                if(chuoi.trim().equals("") ){
                    input_etMKmoi.setErrorEnabled(true);

                    input_etMKmoi.setError("Bạn chưa nhập mục này !");
                    kiemtrathongtin = false;
                }else {

                    if (!kt) {
                        input_etMKmoi.setErrorEnabled(true);
                        input_etMKmoi.setError("Mật khẩu phải có ít nhất 6 ký tự , 1 chữ hoa và 1 số");
                        kiemtrathongtin = false;
                    } else {
                        input_etMKmoi.setErrorEnabled(false);
                        kiemtrathongtin = true;
                        input_etMKmoi.setError("");
                    }
                }

                break;

            case R.id.etNhaplaiMK:
                // if(!b){
                chuoi = ((EditText)view).getText().toString();
                String matkhau = etMKmoi.getText().toString();
                if(!chuoi.equals(matkhau)){
                    input_etNLMKmoi.setErrorEnabled(true);
                    input_etNLMKmoi.setError("Mật khẩu không trùng khớp !");
                    kiemtrathongtin = false;
                }else{
                    input_etNLMKmoi.setErrorEnabled(false);
                    input_etNLMKmoi.setError("");
                    kiemtrathongtin = true;
                }
                // }

                break;

        }
    }
}
