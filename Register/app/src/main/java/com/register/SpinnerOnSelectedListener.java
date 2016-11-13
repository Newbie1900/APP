package com.register;

import android.view.View;
import android.widget.AdapterView;

public class SpinnerOnSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
        //System.out.println(selected);
        parent.setVisibility(View.VISIBLE);/////
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("nothingSelected");
    }
}
