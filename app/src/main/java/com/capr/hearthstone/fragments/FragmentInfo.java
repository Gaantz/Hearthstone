package com.capr.hearthstone.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.capr.hearthstone.beans.Info;
import com.capr.hearthstone.service.Hearthstone;
import com.capr.hearthstone.util.UtilAPI;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by CRISTIAN on 10/08/2015.
 */
public class FragmentInfo extends Fragment {


    public static FragmentInfo newInstance(){
        return new FragmentInfo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInfo().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(
                        new Action1<Info>() {
                            @Override
                            public void call(Info info) {
                                Log.e("INFO",info.getPatch());
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("INFO",throwable.getMessage());
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                Log.e("INFO","COMPLETE");
                            }
                        }
                );
    }

    public Observable<Info> getInfo(){
        return Observable.create(new Observable.OnSubscribe<Info>() {
            @Override
            public void call(Subscriber<? super Info> subscriber) {
                try {
                    RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(UtilAPI.HEARTHSTONE_API_URL).build();
                    Hearthstone service = restAdapter.create(Hearthstone.class);
                    Info info = service.getSInfo();

                    subscriber.onNext(info);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
