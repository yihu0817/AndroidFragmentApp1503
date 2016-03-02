package com.scxh.android1503.ui.searchdialog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.scxh.android1503.R;

public class SearchDialogActivity extends Activity implements View.OnClickListener{
    private EditText mSearchEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_dialog_layout);

        mSearchEdit = (EditText) findViewById(R.id.com_search_edit);
        mSearchEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.com_search_edit:
                Log.e("tag","search>>>>>>>>>>>");
                onSearchRequested();
                break;
        }
    }
}
