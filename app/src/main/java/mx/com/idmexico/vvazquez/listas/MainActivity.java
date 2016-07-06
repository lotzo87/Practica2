package mx.com.idmexico.vvazquez.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.idmexico.vvazquez.listas.adapter.itemListAdapter;
import mx.com.idmexico.vvazquez.listas.model.appDataSource;
import mx.com.idmexico.vvazquez.listas.model.modelListItem;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnAdd;
    private ListView listView;
    private appDataSource source;
    private static int REQUEST_CODE_ADD_ACTIVITY= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAddApp).setOnClickListener(this);
        fillData();
        listView.setOnItemClickListener(this);
    }

    public void fillData()
    {
        listView = (ListView) findViewById(R.id.listItems);
        source = new appDataSource(getApplicationContext());
        listView.setAdapter(new itemListAdapter(getApplicationContext(),source.getAllApp()));
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        View empty = findViewById(R.id.emptyList);
        ListView list = (ListView) findViewById(R.id.listItems);
        list.setEmptyView(empty);
    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(getApplicationContext(),AddAppActivity.class),REQUEST_CODE_ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE_ADD_ACTIVITY==requestCode && resultCode==RESULT_OK)
        {
           fillData();
        }else
            super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemListAdapter adapter= (itemListAdapter) parent.getAdapter();
        modelListItem modelAppList =adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("appid", modelAppList.getId());
        startActivity(intent);
    }

}
