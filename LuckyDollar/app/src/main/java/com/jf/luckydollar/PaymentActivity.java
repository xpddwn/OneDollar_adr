package com.jf.luckydollar;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    int flag = 0;
    Button payment_confirmpay_bt;
    RadioGroup payment_radio_group;
    private static Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
//                    Intent intent = new Intent();
//                    intent.setClass(getActivity(), Winner_detail.class);
//                    startActivity(intent);
                    if (flag == 1){
                        Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Log.d("debug", "支付成功");
                    }
                    else{
                        Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        Log.d("debug", "支付失败");
                    }

                }
            }
        };
        initView();
    }

    protected void initView() {
        payment_radio_group = (RadioGroup) findViewById(R.id.payment_radio_group);
        payment_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.payment_radio_bt_1) {
                    flag = 1;
                } else
                    flag = 0;
            }

        });
        payment_confirmpay_bt = (Button) findViewById(R.id.payment_confirmpay_bt);
        payment_confirmpay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
    }
}
