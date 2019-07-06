package com.example.woopy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr1, container, false);
        LinearLayout ll = (LinearLayout)view.findViewById(R.id.the_frag);
        ll.setBackgroundColor(getResources().getColor(R.color.c3));


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void setText(String text){
        View view = getView();
        if(view != null) {
            EditText editText = getView().findViewById(R.id.text_from_user);
            editText.setText(text);
        }
    }

    public String getText() {
        View view = getView();
        EditText editText = null;
        if(view != null) {
            editText = getView().findViewById(R.id.text_from_user);
        }
        return editText.getText().toString();
    }

}
