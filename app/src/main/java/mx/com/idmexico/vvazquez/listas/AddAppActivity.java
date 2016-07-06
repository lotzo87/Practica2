package mx.com.idmexico.vvazquez.listas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import mx.com.idmexico.vvazquez.listas.model.appDataSource;
import mx.com.idmexico.vvazquez.listas.model.modelListItem;

/**
 * Created by sistemas on 04/07/2016.
 */
public class AddAppActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText app, name, desc;
    private CheckBox chkInastal;
    private static boolean img = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        app = (EditText) findViewById(R.id.etApp);
        name = (EditText)findViewById(R.id.etName);
        desc = (EditText)findViewById(R.id.etDesc);
        chkInastal = (CheckBox) findViewById(R.id.chkInstal);
    }

    @Override
    public void onClick(View v) {
        if ((!app.getText().toString().trim().equals(""))&& (!name.getText().toString().trim().equals("")) && (!desc.getText().toString().trim().equals("")))
        {
            int id = img?(int)R.drawable.ic_toggle_check_box: (int)R.drawable.ic_navigation_cancel;
            img = !img;
            modelListItem modelAppItem = new modelListItem(0,id,app.getText().toString(), name.getText().toString(), desc.getText().toString(), chkInastal.isChecked()?1:0);
            appDataSource source = new appDataSource(getApplicationContext());
            source.saveApp(modelAppItem);
            clearText();
            setResult(RESULT_OK, new Intent());
            finish();
        }
    }

    public void clearText()
    {
        app.setText("");
        name.setText("");
        desc.setText("");
    }
}
