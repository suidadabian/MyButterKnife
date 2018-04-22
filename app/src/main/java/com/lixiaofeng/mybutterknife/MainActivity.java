package com.lixiaofeng.mybutterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaofeng.mbk_annotation.BindView;
import com.lixiaofeng.mbk_annotation.OnClick;
import com.lixiaofeng.mbk_api.ViewFinder;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFinder.inject(this);
        mTextView.setText("hape");
    }


    @OnClick(R.id.btn)
    void onClick(View view) {
        Toast.makeText(this, "more hape", Toast.LENGTH_LONG).show();
    }
}
