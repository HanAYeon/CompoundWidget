package com.example.ccei.compoundwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ccei on 2016-07-06.
 */
// 본 클래스는 LinearLayouyt 의 상위객체로 볼 수 있다.
public class ImageTextCompoundWidget extends LinearLayout {

    /*
    * AttributeSet attrs 는 app 와 관련된 것
    * init(attrs)의 attrs는 속성값
    */
    Context context;
    ImageView imageIcon;
    TextView textTitle;
    ImageTextData data;

    OnCompoundTARAListener impListener;
    /*
    자체 리스너를 선언
     */
    public interface OnCompoundTARAListener {
        public void onImageTextClick(ImageTextCompoundWidget compoundWidget, ImageTextData data);     //콜백메소드
    }

    public void setOnIamgeTextClickListener(OnCompoundTARAListener listener){
        impListener = listener;
    }


    /*
    복합위젯 결합을 위해 필요한 메소드
     */
    public ImageTextCompoundWidget(Context context) {
        super(context);
        this.context = context;
        init(null);
    }
    public ImageTextCompoundWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }
    public ImageTextCompoundWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    //AttributeSet : 호출한 IamgeTextCompoundWudget의 모든 속성값을 사용한다.
    private void init(AttributeSet attrs) {
        this.setOrientation(HORIZONTAL);        // Layout Param. 이 코드는 LinearLayour을 상속 받고 있기 때문에 사용 가능함
        View root = inflate(context, R.layout.image_view_compound_widget, this);                              // inflation : UI의 객체화 과정, 위젯 결합
                                                                                                                // / image+text 라는 자식 위에 linear 라는 부모 루트가 존재
        imageIcon = (ImageView)root.findViewById(R.id.iamge_icon);
        textTitle = (TextView)root.findViewById(R.id.text_title);

        imageIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(impListener != null) {
                    impListener.onImageTextClick(ImageTextCompoundWidget.this, data);
                }
            }// delecate 패던 : 위임한다.
        });
        if(attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageTextCompoundWidget);
            String memeberName = ta.getString(R.styleable.ImageTextCompoundWidget_membername);
            int imageResID = ta.getResourceId(R.styleable.ImageTextCompoundWidget_myimage, R.mipmap.ic_launcher);       // imageResID : 현재이미지의 위치를 알 수 있다
            imageIcon.setImageResource(imageResID);
            textTitle.setText(memeberName);
        }
    }

    /*
    UI에 데이터를 세팅시키기 위한 헬퍼메소드
     */
    public void setImageText(ImageTextData data) {
        this.data = data;
        imageIcon.setImageResource(data.iconID);
        textTitle.setText(data.title);
    }

   /* public void setTextTitle(String title) {
        if(data == null) {
            data = new ImageTextData();
        }
        data.title = title;
    }*/
}
