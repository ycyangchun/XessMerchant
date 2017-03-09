package com.golive.xess.merchant.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseActivity;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerSplashComponent;
import com.golive.xess.merchant.di.modules.SplashModule;
import com.golive.xess.merchant.model.api.NoNetworkException;
import com.golive.xess.merchant.personter.SplashContract;
import com.golive.xess.merchant.personter.SplashPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity implements SplashContract.View {


    @BindView(R.id.textView)
    TextView textView;
    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        DaggerSplashComponent.builder()
                .netComponent(XessApp.get(this).getNetComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);
//        presenter.getSplash("");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    public void showOnFailure(Throwable throwable) {
        if ( throwable instanceof NoNetworkException) {
            //'no network'
            Toast.makeText(this,"No Network Connection",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Some Other Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void successLoad() {
    }
}
