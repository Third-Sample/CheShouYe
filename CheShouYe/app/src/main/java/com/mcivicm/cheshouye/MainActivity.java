package com.mcivicm.cheshouye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cheshouye.api.client.WeizhangClient;
import com.cheshouye.api.client.json.CarInfo;
import com.cheshouye.api.client.json.ProvinceInfoJson;
import com.cheshouye.api.client.json.WeizhangResponseJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private WeiZhangAdapter adapter = new WeiZhangAdapter(new ArrayList<String>(0));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Single.fromCallable(new Callable<List<ProvinceInfoJson>>() {
            @Override
            public List<ProvinceInfoJson> call() throws Exception {
                return WeizhangClient.getAllProvince();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturnItem(new ArrayList<ProvinceInfoJson>(0))
                .subscribe(new SingleObserver<List<ProvinceInfoJson>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ProvinceInfoJson> provinceInfoJsons) {
                        adapter.addData(new Gson().toJson(provinceInfoJsons));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
        Single.fromCallable(new Callable<WeizhangResponseJson>() {
            @Override
            public WeizhangResponseJson call() throws Exception {
                CarInfo car = new CarInfo();
                car.setChepai_no("粤B705A0");
                car.setChejia_no("053146");
                car.setEngine_no("2748");
                car.setRegister_no("");
                car.setCity_id(152);
                return WeizhangClient.getWeizhang(car);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeizhangResponseJson>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull WeizhangResponseJson weizhangResponseJson) {
                        adapter.addData(new Gson().toJson(weizhangResponseJson));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
