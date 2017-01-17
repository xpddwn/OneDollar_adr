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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewsActivity extends AppCompatActivity {

    private static Handler handler=null;
    ListView lv_winner;
    private ArrayList<HashMap<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        initView();
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==0){
                    Intent intent = new Intent();


                    intent.setClass(getApplicationContext(), Winner_detail.class);
                    startActivity(intent);
                }

            }
        };
    }
    private void initView()
    {
        lv_winner = (ListView)findViewById(R.id.reviews_listview);

        MyAdapter adapter = new MyAdapter(this);
        lv_winner.setAdapter(adapter);
    }
    static class ViewHolder
    {
        public ImageView winner_avatar;
        public TextView winner_date,winner_goods_name,winner_comment,winner_name,winner_goods_authenticity;
        public RelativeLayout relativeLayout;

    }
    public class MyAdapter extends BaseAdapter {
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
                convertView = mInflater.inflate(R.layout.item_winner,null);
                //映射 eg：holder.img = (ImagView)convertView.findViewById(R.id.img);
                //holder.itemtablelayout = (TableLayout)convertView.findViewById(R.id.selcourse_tablelayout);
                holder.winner_avatar = (ImageView) convertView.findViewById(R.id.winner_avatar);
                holder.winner_date = (TextView)convertView.findViewById(R.id.winner_date);
                holder.winner_goods_name = (TextView)convertView.findViewById(R.id.winner_goods_name);
                holder.winner_comment = (TextView)convertView.findViewById(R.id.winner_comment);
                holder.winner_name = (TextView)convertView.findViewById(R.id.winner_name);
                holder.winner_goods_authenticity=(TextView)convertView.findViewById(R.id.winner_goods_authenticity);
                holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.winner_item_whole);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            //赋值
            //eg:holder.img.setText()
            //Typeface iconfont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
            //holder.course_icon.setTypeface(iconfont);


            Log.d("debug", "getview");
            holder.winner_avatar.setImageResource(R.drawable.example);
            holder.winner_date.setText("2016-12-12");
            holder.winner_goods_name.setText("Apple watch 2nd gen Sport MLC62JA ROSE");
            holder.winner_comment.setText("Coach Newest Edition, Plus you could check the authenticity at counter");
            holder.winner_goods_authenticity.setText("Plus you could check the authenticity at ");
            holder.winner_name.setText("Uesr ");
            final int p = position;
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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
            return convertView;
        }
    }

}
