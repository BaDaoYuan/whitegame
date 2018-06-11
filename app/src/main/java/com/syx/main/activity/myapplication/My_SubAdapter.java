package com.syx.main.activity.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class My_SubAdapter extends BaseAdapter{
	
	Context context;
	List<Integer> list;
	List<Integer> arr;
	int count = 0;
	
	public My_SubAdapter(Context context, List<Integer> list,List<Integer> arr) {
		this.context = context;
		this.list = list;
		this.arr = arr;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({"ResourceAsColor", "SetTextI18n"})
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_main_add, null);
			holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
			holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
			holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
			holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_1.setBackgroundResource(R.drawable.shape_text_white_false);
		holder.tv_2.setBackgroundResource(R.drawable.shape_text_white_false);
		holder.tv_3.setBackgroundResource(R.drawable.shape_text_white_false);
		holder.tv_4.setBackgroundResource(R.drawable.shape_text_white_false);
		switch (list.get(position)) {
		case 1:
			holder.tv_1.setBackgroundResource(R.drawable.shape_text_white_true);
			holder.tv_1.setText(""+arr.get(position));
			break;
		case 2:
			holder.tv_2.setBackgroundResource(R.drawable.shape_text_white_true);
			holder.tv_2.setText(""+arr.get(position));
			break;
		case 3:
			holder.tv_3.setBackgroundResource(R.drawable.shape_text_white_true);
			holder.tv_3.setText(""+arr.get(position));
			break;
		case 4:
			holder.tv_4.setBackgroundResource(R.drawable.shape_text_white_true);
			holder.tv_4.setText(""+arr.get(position));
			break;

		default:
			break;
		}
		return convertView;
	}
	
	class ViewHolder{
		TextView tv_1;
		TextView tv_2;
		TextView tv_3;
		TextView tv_4;
	}
}
