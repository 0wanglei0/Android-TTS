package com.s2icode.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private Button stop;
    private Button pause;
    private Button resume;
    private EditText speechText;
    private TextToSpeech textToSpeech;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);
        resume = findViewById(R.id.resume);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        resume.setOnClickListener(this);
        speechText = findViewById(R.id.speech_text);
        speechText.setText("证件编号：002345。\n" +
                "姓名：欧阳中和。\n" +
                "性别：男。\n" +
                "工种：后勤。\n" +
                "机构：德国ZDF媒体。\n" +
                "身份证号：110101199912129999。\n" +
                "有效期至：2022年12月31日。");
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override

            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {

                    //设置朗读语言

                    //这里要要注意一下初始化的步骤，这里是一个异步操作

                    int supported = textToSpeech.setLanguage(Locale.CHINA);

                    if ((supported != TextToSpeech.LANG_AVAILABLE) && (supported != TextToSpeech.LANG_COUNTRY_AVAILABLE)) {
                        Toast.makeText(MainActivity.this, "不支持当前语言！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textToSpeech.setLanguage(Locale.CHINA);
        textToSpeech.setSpeechRate(0.9f);
        textToSpeech.setPitch(0.1f);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            String text = speechText.getText().toString();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else if (v.getId() == R.id.pause) {
        } else if (v.getId() == R.id.stop) {
            textToSpeech.stop();
        } else if (v.getId() == R.id.resume) {

        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
        super.onDestroy();
    }

}