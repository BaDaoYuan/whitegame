package com.syx.main.activity.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements OnClickListener {

    private ListView lv_1;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private TextView times;
    private List<Integer> list;
    private List<Integer> arr;
    private int count = 0;
    private My_SubAdapter subAdapter;
    private String str;
    private AlertDialog create;
    private MediaPlayer mediaPlayer;
    private double start;
    public List<Integer> mList;
    public Random random;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    //	初始化参数
    private void initView() {
        lv_1 = (ListView) findViewById(R.id.lv_1);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4 = (Button) findViewById(R.id.btn_4);
        times = (TextView) findViewById(R.id.time);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);

        mList = new ArrayList<>();
        mList.add(R.raw.a);
        mList.add(R.raw.b);
        mList.add(R.raw.c);
        mList.add(R.raw.d);
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add((int)(Math.random()*4)+1);
        }
        arr = new ArrayList<Integer>();
        for (int i = 4; i >= 1; i--) {
            arr.add(i);
        }
        subAdapter = new My_SubAdapter(this, list,arr);
        lv_1.setAdapter(subAdapter);
        random = new Random();
    }

    //	按钮点击事件
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                if(count != 100){
                    game(1);
                }else{
                    win();
                }
                break;

            case R.id.btn_2:
                if(count != 100){
                    game(2);
                }else{
                    win();
                }
                break;

            case R.id.btn_3:
                if(count != 100){
                    game(3);
                }else{
                    win();
                }
                break;

            case R.id.btn_4:
                if(count != 100){
                    game(4);
                }else{
                    win();
                }
                break;

            default:
                break;
        }
    }

    //	点击结果方法
    private void game(int a) {
        count++;
        if(count == 1){
            start = System.currentTimeMillis();
            int music = random.nextInt(mList.size()+1);
            Log.d("123456789",""+music);
            mediaPlayer = MediaPlayer.create(this, mList.get(music));
            mediaPlayer.start();
        }
        if(list.get(list.size()-1) == a){
            arr.remove(arr.size()-1);
            arr.add(0,count+4);
            list.remove(list.size()-1);
            list.add(0, (int)(Math.random()*4)+1);
            subAdapter.notifyDataSetChanged();
        }else{
            lose();
        }
    }

    //	游戏结束弹窗
    @SuppressLint("WrongConstant")
    private void lose() {
        lv_1.setVisibility(-1);
        mediaPlayer.stop();
        create = new AlertDialog.Builder(this)
                .setTitle("游戏失败")
                .setNegativeButton("再来一局", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
//				初始化参数
                        init();
                    }

                }).create();
        create.show();
    }

    //	游戏胜利弹窗
    @SuppressLint({"CommitPrefEdits", "WrongConstant"})
    private void win() {
        double end = System.currentTimeMillis();
        double time = (double) ((double)end - start)/1000;
        lv_1.setVisibility(-1);
        mediaPlayer.stop();
        SharedPreferences preferences = getSharedPreferences("games", MODE_PRIVATE);
        String sum = preferences.getString("score", "0");
        if(Double.parseDouble(sum) > time){
            str = "       胜利！\r\n\r\n当前用时："+time+"秒";
        }else{
            Editor editor = preferences.edit();
            editor.putString("score", time+"");
            editor.commit();
            times.setText("当前记录："+time+"秒");
            str = "       新记录！\r\n\r\n您的用时为"+time+"秒";
        }
        create = new AlertDialog.Builder(this)
                .setTitle("游戏胜利")
                .setMessage(str)
                .setNegativeButton("再来一局", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
//				初始化参数
                        init();
                    }

                }).create();
        create.show();
    }

    //	初始化参数方法
    @SuppressLint("WrongConstant")
    private void init() {
        count = 0;
        lv_1.setVisibility(1);
        arr.clear();
        for (int i = 4; i >= 1; i--) {
            arr.add(i);
        }
        list.clear();
        for (int i = 0; i < 4; i++) {
            list.add((int)(Math.random()*4)+1);
        }
        subAdapter.notifyDataSetChanged();
    }

}
