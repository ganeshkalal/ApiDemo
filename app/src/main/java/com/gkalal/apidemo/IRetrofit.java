package com.gkalal.apidemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofit {
	
	String BASE_URL = "https://reqres.in";
	
	@GET("/api/users") Call<GetUserResponse> getUsers(@Query("page") int page);
}
