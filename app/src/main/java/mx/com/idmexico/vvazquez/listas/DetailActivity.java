package mx.com.idmexico.vvazquez.listas;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import mx.com.idmexico.vvazquez.listas.model.appDataSource;
import mx.com.idmexico.vvazquez.listas.model.modelListItem;
import mx.com.idmexico.vvazquez.listas.sql.MyDbHelper;

/**
 * Created by sistemas on 04/07/2016.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private appDataSource source;
    private ImageView imgView;
    private TextView tvApp, tvDev;
    private Button btnUpdate, btnUninstall, btnOpen;
    private ProgressBar pgrBar;
    private List<modelListItem> modelApp;
    private Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnUninstall = (Button) findViewById(R.id.btnUninstall);
        btnUninstall.setOnClickListener(this);
        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(this);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        imgView = (ImageView) findViewById(R.id.imgSrcDetail);
        tvApp = (TextView) findViewById(R.id.tvAppNameDetail);
        tvDev = (TextView) findViewById(R.id.tvDevNameDetail);
        pgrBar = (ProgressBar)findViewById(R.id.progressBar);
        source = new appDataSource(getApplicationContext());
        bundle = getIntent().getExtras();
        modelApp = source.getApp(bundle.getInt("appid"));
        imgView.setImageResource(modelApp.get(0).getRscId());
        tvApp.setText(modelApp.get(0).getAppName());
        tvDev.setText(modelApp.get(0).getDevName());
        btnUpdate.setText(modelApp.get(0).getIsInstalled() == 0? R.string.update: R.string.uptodate);
        btnUpdate.setEnabled(modelApp.get(0).getIsInstalled() == 0? true:false);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnUninstall:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage(R.string.uninstallmsg).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                source.deleteApp(modelApp.get(0).getId());
                                NotificationCompat.Builder mNotif= (NotificationCompat.Builder) new NotificationCompat
                                        .Builder(getApplicationContext())
                                        .setContentTitle(getString(R.string.uninstallnotif))
                                        .setContentText(String.format(getString(R.string.uninstallnotifmsg) +  modelApp.get(0).getAppName().toString()))
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_verified_user))
                                        .setSmallIcon(R.drawable.ic_action_report_problem);

                                NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                manager.notify(modelApp.get(0).getId(), mNotif.build());

                            }
                        },1000*5);
                    }
                })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();;
                break;
            case R.id.btnOpen:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url))));
                break;
            case R.id.btnUpdate:
                btnEnable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        source.updateApp(modelApp.get(0).getId(),modelApp.get(0).getIsInstalled()==0? 1:0);
                        NotificationCompat.Builder mNotif= (NotificationCompat.Builder) new NotificationCompat
                                .Builder(getApplicationContext())
                                .setContentTitle(getString(R.string.updatenotif))
                                .setContentText(String.format(getString(R.string.uptodate) +  modelApp.get(0).getAppName().toString()))
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_verified_user))
                                .setSmallIcon(R.drawable.ic_action_report_problem);

                        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(modelApp.get(0).getId(), mNotif.build());
                        btnUpdate.setText(R.string.uptodate);
                        btnUpdate.setEnabled(false);
                        btnEnable(true);
                    }
                },1000*10);

                break;
        }

    }

    private void btnEnable(boolean b)
    {
        btnOpen.setEnabled(b);
        btnUninstall.setEnabled(b);
    }
}
