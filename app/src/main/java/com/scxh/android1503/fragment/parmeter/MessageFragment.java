package com.scxh.android1503.fragment.parmeter;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxh.android1503.R;

public class MessageFragment extends Fragment {
    private String message = "";
    public MessageFragment() {
        // Required empty public constructor
    }
    public static MessageFragment newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString("MESSAGE",msg);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            message = getArguments().getString("MESSAGE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_message_layout, container, false);
        TextView msgTxt = (TextView) v.findViewById(R.id.argment_show_msg_txt);

        /**获取从Activity传来的值*/


        msgTxt.setText(message);
        return v;
    }

}
