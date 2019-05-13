package e.datvo_000.jp9shop.View.DonHang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import e.datvo_000.jp9shop.Model.HoaDon.ModelHoaDon;
import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

public class XacThucActivity extends AppCompatActivity {
    int interval=20;
    Timer timer;
    String ma="";
    Button btnXacnhan;
    String email =TrangChuActivity.acc.getEmail();

    EditText et1,et2,et3,et4;
    TextView tvGuilai,tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String chuoi= et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString();


                if (chuoi.equals(ma))
                {
                   // Toast.makeText(getApplicationContext(),"Xác minh thành công",Toast.LENGTH_LONG).show();
                    if( ModelHoaDon.CompleteHD(TrangChuActivity.hoaDon))
                    {
                        Toast.makeText(getApplicationContext(),"Đặt hàng thành công",Toast.LENGTH_LONG).show();
                        TrangChuActivity.XoaHoaDon(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(),TrangChuActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Có lỗi khi đặt hàng , mời bạn thử lại sau!",Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sai mã xác minh ! "
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });
        tvGuilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HenGio();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Cảnh báo")
                .setMessage("Bạn có chắc muốn thoát không ? - Đơn hàng sẽ không được xác nhận")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {//nut tích cực -nut yes
                    public void onClick(DialogInterface dialog, int which) {
                     //   finish();
                        XacThucActivity.super.onBackPressed();//back ve man hinh truoc
                    }
                })
                .setNegativeButton(android.R.string.no, null)//khong lam gi het
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void addControls() {
        setContentView(R.layout.layout_xacthuc);
        tvGuilai=findViewById(R.id.tvGuilai);
        btnXacnhan=findViewById(R.id.btnXacNhan);
        tvEmail=findViewById(R.id.tvEmailXT);
        et1= findViewById(R.id.digit1);
        et2 = findViewById(R.id.digit2);
        et3 = findViewById(R.id.digit3);
        et4 = findViewById(R.id.digit4);

        addProcess();
    }

    private void addProcess() {
        tvGuilai.setText(String.valueOf(interval)+"s");
        tvEmail.setText(email);

        HenGio();
    }

    public void HenGio()
    {
        interval=20;
        ma=ModelHoaDon.Xacthuc(email);
        int delay = 1000;
        int period = 1000;
       timer = new Timer();

        timer.schedule(new TimerTask() {

            public void run() {

                 --interval;
                 //de theard tac dong den ui
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        if (interval == 0)
                        {
                            timer.cancel();
                            tvGuilai.setText("Gửi lại");
                            tvGuilai.setEnabled(true);
                        }
                        else
                        {
                            tvGuilai.setText(String.valueOf(interval)+"s");
                        }

                    }
                });

            }
        }, delay,period);//delay: mới vô delay,period: tuần hoàn
    }
}
