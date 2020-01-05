package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment implements View.OnClickListener{

    private Button []meal=new Button[4];
    private BallView ballView;
    private int []id={R.id.btn_breakfast,R.id.btn_lunch,R.id.btn_dinner,R.id.btn_extra_meal};
    private String meal_selected;//which meal

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_page,container,false);
        ballView=(BallView)view.findViewById(R.id.percentageBall);
        ballView.setProgress(0);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i=0;i<4;i++){
            meal[i]=(Button)getActivity().findViewById(id[i]);
            meal[i].setOnClickListener(this);
        }

    }
    public void onClick(View v){
       Intent intent=new Intent(getActivity(),Activity_meal.class);

        switch (v.getId()){
            case R.id.btn_breakfast:
                meal[0].setEnabled(false);
                meal[1].setEnabled(true);
                meal[2].setEnabled(true);
                meal[3].setEnabled(true);
                meal_selected=meal[0].getText().toString();
                intent.putExtra("meal_name",meal_selected);
                startActivity(intent);
                break;
            case R.id.btn_lunch:
                meal[1].setEnabled(false);
                meal[0].setEnabled(true);
                meal[2].setEnabled(true);
                meal[3].setEnabled(true);
                meal_selected=meal[1].getText().toString();
                intent.putExtra("meal_name",meal_selected);
                startActivity(intent);
                break;
            case R.id.btn_dinner:
                meal[0].setEnabled(true);
                meal[1].setEnabled(true);
                meal[2].setEnabled(false);
                meal[3].setEnabled(true);
                meal_selected=meal[2].getText().toString();
                intent.putExtra("meal_name",meal_selected);
                startActivity(intent);
                break;
            case R.id.btn_extra_meal:
                meal[0].setEnabled(true);
                meal[1].setEnabled(true);
                meal[2].setEnabled(true);
                meal[3].setEnabled(false);
                meal_selected=meal[3].getText().toString();
                intent.putExtra("meal_name",meal_selected);
                startActivity(intent);
                break;
        }
    }
}
