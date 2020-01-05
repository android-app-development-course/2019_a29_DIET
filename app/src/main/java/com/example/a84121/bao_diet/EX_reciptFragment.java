package com.example.a84121.bao_diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class EX_reciptFragment extends Fragment implements View.OnClickListener{

    private Button normal_recipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_recipe, null);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        normal_recipe=(Button) getActivity().findViewById(R.id.normal_recipe);
        normal_recipe.setOnClickListener(this);
    }
    public void onClick(View v){
        Intent intent=new Intent(getActivity(),Normal_Recipe.class);
        startActivity(intent);
    }
}
