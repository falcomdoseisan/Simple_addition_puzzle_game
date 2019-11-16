package jp.wings.braintraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultView;
    Button topButton;
    TextView highScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultView = (TextView)findViewById(R.id.resultview);
        highScoreView = (TextView)findViewById(R.id.highScoreview);
        topButton = (Button)findViewById(R.id.topbutton);
        topButton.setOnClickListener(topButtonListener);

        //RIGHT_ANSWER_COUNTをキーにしてMainActivityから値を取得、取得できなかった場合は0を取得
        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        resultView.setText("こんかいのスコア："+score);

        // ハイスコアの読み出し
        SharedPreferences prefs = getSharedPreferences("braintraining", Context.MODE_PRIVATE);
        int highscore = prefs.getInt("highScore", 0);

        // ハイスコアと今回のスコアを比較して、今回のスコアのほうが高ければhighscoreにその値を代入
        if(highscore<score){
            highscore = score;
            // TextViewに表示する
            highScoreView.setText("おめでとう！\nハイスコアこうしんだよ！！\nハイスコア："+highscore);
        }else {
            // TextViewに表示する
            highScoreView.setText("ハイスコア : " + highscore);
        }

        // トータルスコアを保存
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("highScore", highscore);
        editor.apply();
    }

    //Return top screen
    View.OnClickListener topButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intentTop = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentTop);
        }
    };
}
