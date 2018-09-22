package com.jascal.flora.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.mvp.OAuthContract;
import com.jascal.flora.mvp.presenter.OAuthPresenter;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_annotation.OnClick;
import com.jascal.ophelia_api.Ophelia;

public class MainActivity extends BaseActivity implements OAuthContract.view {
    private OAuthContract.presenter presenter;

    @BindView(R.id.text)
    TextView textView;

    @OnClick(R.id.shots)
    void requestShots(View view) {
        presenter.getShots(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ophelia.bind(this);
        new OAuthPresenter(this);
    }

    @Override
    public void setPresenter(OAuthContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(String shots) {
        textView.setText(shots);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "get shots error:" + message, Toast.LENGTH_LONG).show();
    }
}
