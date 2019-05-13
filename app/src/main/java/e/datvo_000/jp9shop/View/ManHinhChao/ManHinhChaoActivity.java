package e.datvo_000.jp9shop.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import e.datvo_000.jp9shop.R;
import e.datvo_000.jp9shop.View.TrangChu.TrangChuActivity;

/**
 * Created by datvo_000 on 09/02/2019.
 */

public class ManHinhChaoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mhchao_layout);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                }catch (Exception e){

                }finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this,TrangChuActivity.class);
                    startActivity(intent);


                }
            }
        });
        thread.start();
        //overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }
}
