package com.meilishuo.linegrideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meilishuo.linegrideview.view.LineGridView;

public class MainActivity extends AppCompatActivity {

    private LineGridView gridView;
    int[] resId = {R.drawable.icon_home_addgoods,R.drawable.icon_home_cash,R.drawable.icon_home_goods,R.drawable.icon_home_mircoblog,
                    R.drawable.icon_home_order,R.drawable.icon_home_supplier,R.drawable.icon_home_wxpay};
    String[] titles = {"微信","微信","微信","微信","微信","微信","微信"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (LineGridView) findViewById(R.id.line_grideview);
        gridView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return resId.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            Holder holder = null;
            if(convertView == null){
                convertView = View.inflate(MainActivity.this, R.layout.item_layout, null);
                holder = new Holder();
                holder.iv = ((ImageView) convertView.findViewById(R.id.iv));
                holder.tv = ((TextView) convertView.findViewById(R.id.tv));
                convertView.setTag(holder);
            } else {
                holder = ((Holder) convertView.getTag());
            }
            holder.iv.setImageResource(resId[i]);
            holder.tv.setText(titles[i]);
            return convertView;
        }
    }

    static class Holder{
        ImageView iv;
        TextView tv;
    }

}
