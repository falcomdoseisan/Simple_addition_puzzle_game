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
    TextView textView;
    TextView judgeView;
    TextView ptView;
    TextView timeView;
    Typeface customFont;
    VideoView videoView;
    Button button;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    int value_opt1;
    int value_opt2;
    int value_opt3;
    int value_opt4;
    final long START_TIME = 30000;
    long mTimeLeftInMillis = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //font設定
        customFont = Typeface.createFromAsset(getAssets(), "KodomoRounded.otf");

        textView = (TextView) findViewById(R.id.textview);
        textView.setTypeface(customFont);
        judgeView = (TextView) findViewById(R.id.judgeview);
        judgeView.setTypeface(customFont);
        timeView=(TextView) findViewById(R.id.timeview);
        timeView.setTypeface(customFont);
        ptView = (TextView) findViewById(R.id.ptview);
        ptView.setTypeface(customFont);
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

        //TextView textView = (TextView) findViewById(R.id.textview);
        //textView.setText("GoodMorning!!Android!!");
        //textView.setText(String.valueOf(43*21));
    }


    int answer;
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

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (timerRunning == false) {
                init();
                pt=0;
                //judgeView.setText("にゅうりょくちゅう");
                judgeView.setText(" + ="+String.valueOf(answer));
                //videoView.setVideoPath("C:\\Users\\ueshi\\Desktop\\プログラミング\\Java\\脳トレ\\素材\\321.");
                //videoView.setVideoPath("C:\\Users\\ueshi\\Videos\\321.3gp");
 //               videoView.setVideoPath(Environment.getExternalStorageDirectory().toString() + "");
 //               videoView.start();
                //カウントダウンタイマー
                CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int time = (int) millisUntilFinished / 1000;
                        timeView.setText(time + "びょう");
                        timerRunning = true;
                    }

                    @Override
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

        textView.setText(String.valueOf(answer));
        option1.setText(String.valueOf(optArray[0]));
        option2.setText(String.valueOf(optArray[1]));
        option3.setText(String.valueOf(optArray[2]));
        option4.setText(String.valueOf(optArray[3]));
    }

    public void check(int val){
        result = result + val;
        if(cnt==0){
            cnt= cnt+1;
            ope1 = val;
            //judgeView.setText("にゅうりょくちゅう");
            judgeView.setText(String.valueOf(ope1)+"+ ="+String.valueOf(answer));
            judgeView.setTextColor(Color.rgb(255,255,255));
        }else if(cnt==1){
            ope2 = val;
            if(result==answer){
                judgeView.setText(String.valueOf(ope1)+"+"+String.valueOf(ope2)+"="+String.valueOf(answer)+"\nせいかい！！！");
                //#99EE99
                judgeView.setTextColor(Color.rgb(153, 238, 153));
                pt=pt+1;
                ptView.setText(String.valueOf(pt)+"ポイント");
            }else{
                judgeView.setText(String.valueOf(ope1)+"+"+String.valueOf(ope2)+"="+String.valueOf(answer)+"\nふせいかい...");
                //#FFBBFF
                judgeView.setTextColor(Color.rgb(255,187,255));
            }
            result = 0;
            cnt=0;
            init();
        }else{
            judgeView.setText("よきしないエラーです。アプリかいはつしゃにれんらくしてください。");
            judgeView.setTextColor(Color.rgb(255,255,255));
        }
    }
}