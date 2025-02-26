package jp.wings.braintraining;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.VideoView;
import android.net.Uri;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.widget.VideoView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //Variables initialization
    TextView textView;
    TextView judgeView;
    TextView ptView;
    TextView timeView;
    Typeface customFont;
    Button button;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    final long START_TIME = 30000;
    int answer;
    int answer_before;

    int opt1;
    int opt2;
    int opt3;
    int opt4;
    int cnt=0;
    int result;
    int pt=0;
    int ope1;
    int ope2;
    boolean timerRunning=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //font setting
        customFont = Typeface.createFromAsset(getAssets(), "KodomoRounded.otf");

        //text setting
        textView = (TextView) findViewById(R.id.textview);
        textView.setTypeface(customFont);
        judgeView = (TextView) findViewById(R.id.judgeview);
        judgeView.setTypeface(customFont);
        timeView=(TextView) findViewById(R.id.timeview);
        timeView.setTypeface(customFont);
        ptView = (TextView) findViewById(R.id.ptview);
        ptView.setTypeface(customFont);

        //button setting
        button = (Button) findViewById(R.id.button);
        button.setTypeface(customFont);
        button.setOnClickListener(buttonListener);
        option1 = (Button)findViewById(R.id.option_1);
        option1.setTypeface(customFont);
        option1.setOnClickListener(buttonOptListener_1);
        option2 = (Button)findViewById(R.id.option_2);
        option2.setTypeface(customFont);
        option2.setOnClickListener(buttonOptListener_2);
        option3 = (Button)findViewById(R.id.option_3);
        option3.setTypeface(customFont);
        option3.setOnClickListener(buttonOptListener_3);
        option4 = (Button)findViewById(R.id.option_4);
        option4.setTypeface(customFont);
        option4.setOnClickListener(buttonOptListener_4);
    }





    // タイマー設定
    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (timerRunning == false) {
                init();
                pt=0;
                judgeView.setText(" + ="+String.valueOf(answer));
                //カウントダウンタイマー(30s,1sごと)
                CountDownTimer countDownTimer = new CountDownTimer(START_TIME, 1000) {
                    @Override
                    //秒数表示
                    public void onTick(long millisUntilFinished) {
                        int time = (int) millisUntilFinished / 1000;
                        timeView.setText(time + "びょう");
                        timerRunning = true;
                    }

                    @Override
                    //カウントダウンタイマー終了時の処理
                    public void onFinish() {
                        timeView.setText("しゅうりょう！！");
                        judgeView.setText("おつかれさま");
                        judgeView.setTextColor(Color.rgb(255,255,255));
                        timerRunning = false;
                        //Screen transition
                        Intent intentResult = new Intent(getApplicationContext(), ResultActivity.class);
                        //RIGHT_ANSWER_COUNTをキーにして,ptをResultActivityに渡す
                        intentResult.putExtra("RIGHT_ANSWER_COUNT", pt);
                        startActivity(intentResult);
                    }
                }.start();
            }
        }
    };

    //optionボタンを押した際カウントダウンタイマーを押したらcheck(value)メソッドを実行する
    View.OnClickListener buttonOptListener_1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(timerRunning == true) {
                Button button = (Button) view;
                int value;
                value = Integer.parseInt(option1.getText().toString());
                check(value);
            }
        }
    };

    View.OnClickListener buttonOptListener_2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(timerRunning == true) {
                Button button = (Button) view;
                int value;
                value = Integer.parseInt(option2.getText().toString());
                check(value);
            }
        }
    };

    View.OnClickListener buttonOptListener_3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(timerRunning == true) {
                Button button = (Button) view;
                int value;
                value = Integer.parseInt(option3.getText().toString());
                check(value);
            }
        }
    };

    View.OnClickListener buttonOptListener_4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(timerRunning == true) {
                Button button = (Button) view;
                int value;
                value = Integer.parseInt(option4.getText().toString());
                check(value);
            }
        }
    };

    // 初期設定（答えと選択肢の設定）
    public void init() {
        answer = (int) (Math.random() * 11);
        while(answer !=opt1+opt2){
            opt1 = (int) (Math.random() * 11);
            opt2 = (int) (Math.random() * 11);
        }
        opt3 = (int) (Math.random() * 11);
        opt4 = (int) (Math.random() * 11);
        int optArray[] ={opt1,opt2,opt3,opt4};

        //Fisher-Yates algorithm
        for(int i=optArray.length-1;i>0;i--){
            int r = (int)(Math.random()*(i+1));
            int tmp = optArray[i];
            optArray[i] = optArray[r];
            optArray[r] = tmp;
        }

        //答えとオプションボタンの設定
        textView.setText(String.valueOf(answer));
        option1.setText(String.valueOf(optArray[0]));
        option2.setText(String.valueOf(optArray[1]));
        option3.setText(String.valueOf(optArray[2]));
        option4.setText(String.valueOf(optArray[3]));
    }

    //optionボタンを選択した際に動作するメソッド
    public void check(int val){
        result = result + val;
        if(cnt==0){  //optionボタンの1回目の選択
            cnt= cnt+1;
            ope1 = val;
            //judgeView.setText("にゅうりょくちゅう");
            judgeView.setText(String.valueOf(ope1)+"+ ="+String.valueOf(answer));
            judgeView.setTextColor(Color.rgb(255,255,255));
        }else if(cnt==1){  //optionボタンの2回目の選択
            answer_before = answer;
            init();
            ope2 = val;
            if(result==answer_before){  //正解の時
                judgeView.setText(String.valueOf(ope1)+"+"+String.valueOf(ope2)+"="+String.valueOf(answer_before)+"\nせいかい！！！\n"+" + ="+String.valueOf(answer));
                //#99EE99
                judgeView.setTextColor(Color.rgb(153, 238, 153));
                pt=pt+1;
                ptView.setText(String.valueOf(pt)+"ポイント");
            }else{  //不正解の時
                judgeView.setText(String.valueOf(ope1)+"+"+String.valueOf(ope2)+"="+String.valueOf(answer_before)+"\nふせいかい...\n"+" + ="+String.valueOf(answer));
                //#FFBBFF
                judgeView.setTextColor(Color.rgb(255,187,255));
            }
            result = 0;
            cnt=0;
        }else{  //例外が生じた際
            judgeView.setText("よきせぬエラーです。アプリかいはつしゃにれんらくしてください。");
            judgeView.setTextColor(Color.rgb(255,255,255));
        }
    }
}