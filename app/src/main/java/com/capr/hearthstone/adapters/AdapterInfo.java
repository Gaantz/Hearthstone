package com.capr.hearthstone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CRISTIAN on 11/08/2015.
 */
public class AdapterInfo extends BaseAdapter {

    private Context context;
    private ArrayList<String> names;
    private LayoutInflater inflater;

    public AdapterInfo(Context mContext, ArrayList<String> tipo_dtos) {
        this.context = mContext;
        this.names = tipo_dtos;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        String name = names.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new Holder();

            holder.name = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.name.setText(name);

        return convertView;
    }

    static class Holder {
        TextView name;
    }
}
