package com.example.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.diet.R.id.btn_breakfast;
import static com.example.diet.R.id.btn_lunch;


public class Fragment1 extends Fragment implements View.OnClickListener{

    private Button []meal=new Button[4];
    private BallView ballView;
    private int []id={R.id.btn_breakfast,R.id.btn_lunch,R.id.btn_dinner,R.id.btn_extra_meal};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_page,container,false);
        ballView=(BallView)view.findViewById(R.id.percentageBall);
        ballView.setProgress(0);
        for(int i=0;i<4;i++){
            meal[i]=(Button)view.findViewById(id[i]);
            meal[i].setOnClickListener(this);
        }
        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_breakfast:
                meal[0].setEnabled(false);
                for(int i=1;i<4;i++)
                    meal[i].setEnabled(true);
                break;
            case R.id.btn_lunch:
                meal[1].setEnabled(false);
                meal[0].setEnabled(true);
                meal[2].setEnabled(true);
                meal[3].setEnabled(true);
                break;
            case R.id.btn_dinner:
                meal[0].setEnabled(true);
                meal[1].setEnabled(true);
                meal[2].setEnabled(false);
                meal[3].setEnabled(true);
                break;
            case R.id.btn_extra_meal:
                meal[0].setEnabled(true);
                meal[1].setEnabled(true);
                meal[2].setEnabled(true);
                meal[3].setEnabled(false);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
