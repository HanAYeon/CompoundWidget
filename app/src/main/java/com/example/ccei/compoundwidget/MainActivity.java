package com.example.ccei.compoundwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageTextCompoundWidget comWidget, comWidget2;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comWidget = (ImageTextCompoundWidget)findViewById(R.id.compound_widget);
       // comWidget2 = (ImageTextCompoundWidget)findViewById(R.id.compound_widget2);

       /* ImageTextData data1 = new ImageTextData("지연", R.drawable.t_ara_icon_jiyeon);
        ImageTextData data2 = new ImageTextData("보람", R.drawable.t_ara_icon_boram);

        comWidget.setImageText(data1);
        comWidget2.setImageText(data2);*/

        comWidget.setOnIamgeTextClickListener(new ImageTextCompoundWidget.OnCompoundTARAListener(){
            @Override
            public void onImageTextClick(ImageTextCompoundWidget compoundWidget, ImageTextData data) {
                if(data != null) {
                    Toast.makeText(getApplicationContext(), "이름과 사진이 바뀝니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button changeBtn = (Button)findViewById(R.id.change_btn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    comWidget.setImageText(new ImageTextData("지연", R.drawable.t_ara_icon_jiyeon));
                } else {
                    comWidget.setImageText(new ImageTextData("보람", R.drawable.t_ara_icon_boram));
                }
                flag = !flag;
            }
        });
    }
}
