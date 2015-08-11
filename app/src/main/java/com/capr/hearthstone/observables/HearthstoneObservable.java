package com.capr.hearthstone.observables;

import com.capr.hearthstone.beans.Info;
import com.capr.hearthstone.service.Hearthstone;
import com.capr.hearthstone.util.UtilAPI;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by CRISTIAN on 11/08/2015.
 */
public class HearthstoneObservable {

    public static Observable<Info> getInfo(){
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
}
