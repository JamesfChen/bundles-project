package com.jamesfchen.bundle1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

//import com.jamesfchen.ibc.router.IBCRouter;
//import com.jamesfchen.ibc.router.UriBuilder;

//@Route(path = "/bundle1/sayme")
public class SayMeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button tv = new Button(this);
        tv.setText(getPackageName()+" bundle1 say me ");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        tv.setAllCaps(false);
        setContentView(tv,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. Simple jump within application (Jump via URL in 'Advanced usage')
//                ARouter.getInstance().build("/bundle2/main").navigation();
//                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("jamesfchen://www.jamesfchen.com/hotel/main"));
//                startActivity(intent);
                // 2. Jump with parameters
//                    ARouter.getInstance().build("/login/1")
//                            .withLong("key1", 666L)
//                            .withString("key3", "888")
//                            .navigation();
//                UriBuilder uriBuilder =new  UriBuilder();
//                uriBuilder.setUri("b://bundle2/sayhi");
//                IBCRouter.open(SayMeActivity.this,uriBuilder);
//
            }
        });
    }

}
