package com.gkalal.apidemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gkalal.apidemo.IRetrofit.BASE_URL;

public class MainActivity extends AppCompatActivity
		implements RecyclerViewAdapter.HandleClickListener {
	
	private MainActivityBinder mBinder;
	private RecyclerViewAdapter mAdapter;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
			
			callApi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void callApi() {
		try {
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(HttpLoggingInterceptor.Level.BODY);
			OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
			httpClient.addInterceptor(logging);
			
			Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.client(httpClient.build())
					.build();
			
			IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
			
			mBinder.shimmerFrameLayout.startShimmerAnimation();
			mBinder.recyclerView.setVisibility(View.GONE);
			
			iRetrofit.getUsers(2).enqueue(new Callback<GetUserResponse>() {
				@Override public void onResponse(Call<GetUserResponse> call,
						Response<GetUserResponse> response) {
					try {
						mBinder.shimmerFrameLayout.stopShimmerAnimation();
						mBinder.shimmerFrameLayout.setVisibility(View.GONE);
						mBinder.recyclerView.setVisibility(View.VISIBLE);
						if (response.isSuccessful()) {
							populateData(response.body().getData());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				@Override public void onFailure(Call<GetUserResponse> call, Throwable t) {
					try {
						mBinder.shimmerFrameLayout.stopShimmerAnimation();
						mBinder.shimmerFrameLayout.setVisibility(View.GONE);
						mBinder.recyclerView.setVisibility(View.VISIBLE);
						Log.d("OnFailure", t.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void populateData(List<GetUserResponse.Data> dataList) {
		try {
			if (dataList != null) {
				mAdapter = new RecyclerViewAdapter(dataList, MainActivity.this, this);
				mAdapter.setHasStableIds(true);
				mBinder.recyclerView.setAdapter(mAdapter);
				mBinder.recyclerView.setHasFixedSize(true);
				mBinder.recyclerView.setLayoutManager(new LinearLayoutManager(this));
				mAdapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override public void onItemClick(GetUserResponse.Data data) {
	
	}
}
