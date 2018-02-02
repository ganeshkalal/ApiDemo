package com.gkalal.apidemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
	
	private ItemRecyclerViewBinder mBinder;
	private List<GetUserResponse.Data> userList;
	private Context context;
	private HandleClickListener handleClickListener;
	
	public RecyclerViewAdapter(List<GetUserResponse.Data> userList, Context context,
			HandleClickListener handleClickListener) {
		this.userList = userList;
		this.context = context;
		this.handleClickListener = handleClickListener;
	}
	
	@Override
	public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		mBinder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
				R.layout.item_recyclerview, parent, false);
		return new ViewHolder(mBinder);
	}
	
	@SuppressLint("SetTextI18n") @Override
	public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
		try {
			GetUserResponse.Data data = userList.get(position);
			if (data != null) {
				mBinder.textViewFirstName.setText("First Name : " + data.getFirstName());
				mBinder.textViewLastName.setText("Last Name : " + data.getLastName());
				
				Glide.with(context).load(data.getAvatar()).into(mBinder.imageViewProfile);
				
				handleClickListener.onItemClick(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override public int getItemCount() {
		return userList.size();
	}
	
	@Override public long getItemId(int position) {
		return position;
	}
	
	interface HandleClickListener {
		void onItemClick(GetUserResponse.Data data);
	}
	
	class ViewHolder extends RecyclerView.ViewHolder {
		ItemRecyclerViewBinder mBinder;
		
		ViewHolder(ItemRecyclerViewBinder binder) {
			super(binder.getRoot());
			this.mBinder = binder;
		}
	}
}
