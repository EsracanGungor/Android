package com.esragungor.retrofitjava.service;


import com.esragungor.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("prices?key=0da1db964e169c325abd71608e6935a1")
    Observable<List<CryptoModel>> getData();




}
