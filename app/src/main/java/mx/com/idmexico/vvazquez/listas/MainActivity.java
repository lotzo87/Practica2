package mx.com.idmexico.vvazquez.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.idmexico.vvazquez.listas.adapter.itemListAdapter;
import mx.com.idmexico.vvazquez.listas.model.appDataSource;
import mx.com.idmexico.vvazquez.listas.model.modelListItem;


public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView listView;
    private boolean img = false;
    private appDataSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
