package com.jf.luckydollar;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyrewardActivity extends AppCompatActivity {
    private ListView myreward_listview;
    private ArrayList<HashMap<String,String>> data;
    private static Handler handler=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreawrd);
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==0){
                    Toast.makeText(MyrewardActivity.this, "BT pressed", Toast.LENGTH_SHORT).show();
                }

            }
        };
        initView();
    }
    void initView(){
        myreward_listview=(ListView)findViewById(R.id.myreward_listview);
        MyAdapter adapter=new MyAdapter(this);
        myreward_listview.setAdapter(adapter);
    }



    static class ViewHolder
    {
        public ImageView myrewadrs_image;
        public TextView myrewadrs_goods_name,myrewadrs_goods_num,myrewadrs_status_detail,myrewadrs_company_datail,myrewadrs_ordernum_detail,
                myrewadrs_luckynum_detail,myrewadrs_total_detail,myrewadrs_bought_datail,myrewadrs_time_detail;
        public Button myrewadrs_goods_share_bt;
        public RelativeLayout relativeLayout;

    }public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
//        public int getCount() {
//            return data.size();
//        }
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_myrewards,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);

                holder.myrewadrs_image = (ImageView) convertView.findViewById(R.id.myrewadrs_image);
                holder.myrewadrs_goods_name = (TextView)convertView.findViewById(R.id.myrewadrs_goods_name);
                holder.myrewadrs_goods_num = (TextView)convertView.findViewById(R.id.myrewadrs_goods_num);
                holder.myrewadrs_status_detail = (TextView)convertView.findViewById(R.id.myrewadrs_status_detail);
                holder.myrewadrs_company_datail = (TextView)convertView.findViewById(R.id.myrewadrs_company_datail);
                holder.myrewadrs_ordernum_detail=(TextView)convertView.findViewById(R.id.myrewadrs_ordernum_detail);
                holder.myrewadrs_luckynum_detail = (TextView)convertView.findViewById(R.id.myrewadrs_luckynum_detail);
                holder.myrewadrs_total_detail = (TextView)convertView.findViewById(R.id.myrewadrs_total_detail);
                holder.myrewadrs_bought_datail = (TextView)convertView.findViewById(R.id.myrewadrs_bought_datail);
                holder.myrewadrs_time_detail=(TextView)convertView.findViewById(R.id.myrewadrs_time_detail);
                holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.whole_item_myrewards);
                holder.myrewadrs_goods_share_bt=(Button)convertView.findViewById(R.id.myrewadrs_goods_share_bt);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);


            Log.d("debug", "getview");
            holder.myrewadrs_image.setImageResource(R.drawable.example);
            holder.myrewadrs_goods_name.setText("Apple watch 1st gen Sport MLC62JA ROSE");
            holder.myrewadrs_goods_num.setText("You bought 1 entries");
            holder.myrewadrs_status_detail.setText("Delivered");
            holder.myrewadrs_company_datail.setText("xxx");
            holder.myrewadrs_ordernum_detail.setText("xxxxxxxx ");
            holder.myrewadrs_luckynum_detail.setText("123123123");
            holder.myrewadrs_bought_datail.setText("12 numbers");
            holder.myrewadrs_time_detail.setText("2017-01-15 10:10:00");
            holder.myrewadrs_goods_share_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 0;
                    Bundle bundle = new Bundle();
//                    bundle.putString("courseid", data.get(p).get("kc_id"));  //往Bundle中存放数据
//                    bundle.putInt("position",position);
                    msg.setData(bundle);//mes利用Bundle传递数据
                    handler.sendMessage(msg);
                }
            });
            final int p = position;

            return convertView;
        }
    }
}
