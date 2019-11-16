package jp.wings.braintraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultView;
    Button topButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultView = (TextView)findViewById(R.id.resultview);
        topButton = (Button)findViewById(R.id.topbutton);
        topButton.setOnClickListener(topButtonListener);

        //RIGHT_ANSWER_COUNTをキーにしてMainActivityから値を取得、取得できなかった場合は0を取得
        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        resultView.setText("こんかいのスコア："+score);
    }


    View.OnClickListener topButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intentTop = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentTop);
        }
    };
}
