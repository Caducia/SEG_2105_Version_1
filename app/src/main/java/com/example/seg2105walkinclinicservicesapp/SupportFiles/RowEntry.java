package com.example.seg2105walkinclinicservicesapp.SupportFiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seg2105walkinclinicservicesapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class RowEntry extends LinearLayout {

    public static final int MAX_LENGTH = 2;//The maximum amount of text fields available
    private String[] values; //The values to be plugged into the different elements
    private String[] options;

    private TextView textView;
    private EditText editText;
    private Spinner spinner;
    private CheckBox checkBox;

    private Context contextHolder;

    public RowEntry(Context context) {
        super(context);
    }

    public RowEntry(Context context, String[] values, String[] options) {
        super(context);
        contextHolder = context;
        if(values.length == MAX_LENGTH& values != null){//Only assign the values if they are the correct lemgth
            this.values = values;
            this.options = options;
        }else{
            return;
        }

        setOrientation(HORIZONTAL);

        LayoutInflater.from(context).inflate(R.layout.row_entry, this, true);//Instantiates an XML layout in object form so that it can be used in java




    }

    public RowEntry(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RowEntry(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RowEntry(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        this.textView = (TextView) findViewById(R.id.text_view);
        this.editText = (EditText) findViewById(R.id.numeric_entry);
        this.spinner = (Spinner) findViewById(R.id.option_spinner);
        this.checkBox = (CheckBox) findViewById(R.id.selection_checkbox);

        this.textView.setText(values[0]);
        if(options != null & options.length > 0){
            this.spinner.setAdapter(new ArrayAdapter<String>(contextHolder, android.R.layout.simple_spinner_dropdown_item, options));
        }
        this.editText.setText(values[1]);

    }


}
