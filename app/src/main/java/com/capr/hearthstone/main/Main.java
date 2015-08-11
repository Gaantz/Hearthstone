package com.capr.hearthstone.main;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.capr.hearthstone.R;
import com.capr.hearthstone.adapters.AdapterInfo;
import com.capr.hearthstone.beans.Info;
import com.capr.hearthstone.observables.HearthstoneObservable;
import com.capr.hearthstone.service.Hearthstone;
import com.capr.hearthstone.util.UtilAPI;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class Main extends ActionBarActivity {

    private AdapterInfo adapterInfo;
    private ListView listView;
    private Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

/*        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(UtilAPI.HEARTHSTONE_API_URL).build();
                    Hearthstone service = restAdapter.create(Hearthstone.class);
                    Info info = service.getSInfo();

                    for (String clase : info.getClasses())
                    {
                        Log.e("RESULTADO",clase);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();*/

/*        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(UtilAPI.HEARTHSTONE_API_URL).build();
        Hearthstone service = restAdapter.create(Hearthstone.class);
        service.getAInfo(new Callback<Info>() {
            @Override
            public void success(Info info, Response response) {
                for (String clase : info.getClasses())
                {
                    Log.e("RESULTADO",clase);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });*/

        listView = (ListView) findViewById(R.id.lista);

        HearthstoneObservable.getInfo().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(
                        new Action1<Info>() {
                            @Override
                            public void call(Info info) {
                                Main.this.info = info;
                                adapterInfo = new AdapterInfo(Main.this,Main.this.info.getTypes());
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
                                listView.setAdapter(adapterInfo);
                            }
                        }
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_clases) {
            adapterInfo = new AdapterInfo(Main.this,Main.this.info.getClasses());
        }
        else if (id == R.id.menu_sets) {
            adapterInfo = new AdapterInfo(Main.this,Main.this.info.getSets());
        }

        listView.setAdapter(adapterInfo);

        return super.onOptionsItemSelected(item);
    }
}
