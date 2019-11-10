package jp.wings.braintraining;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView judgeView;
    TextView ptView;
    Button button;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    int value_opt1;
    int value_opt2;
    int value_opt3;
    int value_opt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        judgeView = (TextView) findViewById(R.id.judgeview);
        ptView = (TextView) findViewById(R.id.ptview);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);
        option1 = (Button)findViewById(R.id.option_1);


        //value_opt1 = Integer.parseInt(option1.getText().toString());
        option1.setOnClickListener(buttonOptListener_1);
        option2 = (Button)findViewById(R.id.option_2);
        //value_opt2 = Integer.parseInt()
        option2.setOnClickListener(buttonOptListener_2);
        option3 = (Button)findViewById(R.id.option_3);
        option3.setOnClickListener(buttonOptListener_3);
        option4 = (Button)findViewById(R.id.option_4);
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

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            init();
        }

    };


    View.OnClickListener buttonOptListener_1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button=(Button) view;
            int value;
            value = Integer.parseInt(option1.getText().toString());
            check(value);
        }
    };

    View.OnClickListener buttonOptListener_2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button=(Button) view;
            int value;
            value = Integer.parseInt(option2.getText().toString());
            check(value);
        }
    };

    View.OnClickListener buttonOptListener_3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button=(Button) view;
            int value;
            value = Integer.parseInt(option3.getText().toString());
            check(value);
        }
    };

    View.OnClickListener buttonOptListener_4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button=(Button) view;
            int value;
            value = Integer.parseInt(option4.getText().toString());
            check(value);
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
            judgeView.setText("入力中");
        }else if(cnt==1){
            if(result==answer){
                judgeView.setText("正解");
                pt=pt+1;
                ptView.setText(String.valueOf(pt)+"ポイント");
            }else{
                judgeView.setText("不正解");
            }
            result = 0;
            cnt=0;
            init();
        }else{
            judgeView.setText("Unexpected error!!!");
        }
    }
}