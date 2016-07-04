package mx.com.idmexico.vvazquez.listas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import mx.com.idmexico.vvazquez.listas.R;
import mx.com.idmexico.vvazquez.listas.model.modelListItem;

/**
 * Created by sistemas on 29/06/2016.
 */
public class itemListAdapter extends ArrayAdapter<modelListItem> {

    public itemListAdapter(Context context, List<modelListItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);

        ImageView imgSrc = (ImageView) convertView.findViewById(R.id.imgSrc);
        TextView tvAppName = (TextView) convertView.findViewById(R.id.tvAppName);
        TextView tvDevName = (TextView) convertView.findViewById(R.id.tvDevName);
        TextView tvInstalled = (TextView) convertView.findViewById(R.id.tvInstalled);
        modelListItem modelList = getItem(position);
        imgSrc.setImageResource(modelList.getRscId());
        tvAppName.setText(modelList.getAppName());
        tvDevName.setText(modelList.getDevName());
        tvInstalled.setText(modelList.getIsInstalled()==1?R.string.isInstalled:R.string.isNotInstalled);

        return convertView;
    }
}
