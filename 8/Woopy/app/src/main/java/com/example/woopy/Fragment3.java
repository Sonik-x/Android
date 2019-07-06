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

    View theView = null;
    String theText = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        theView = inflater.inflate(R.layout.fr1, container, false);
        LinearLayout ll = (LinearLayout)theView.findViewById(R.id.the_frag);
        ll.setBackgroundColor(getResources().getColor(R.color.c3));
        setText(theText);


        return theView;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void setText(String text){
        if(theView != null) {
            EditText editText = theView.findViewById(R.id.text_from_user);
            editText.setText(text);
        }
        theText = text;
    }

    public String getText() {
        EditText editText = null;
        if(theView != null) {
            editText = theView.findViewById(R.id.text_from_user);
        }
        return editText.getText().toString();
    }

}
