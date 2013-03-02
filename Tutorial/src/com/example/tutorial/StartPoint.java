package com.example.tutorial;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartPoint extends Activity implements View.OnClickListener{

	int counter;
	Button add, sub;
	TextView display;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_point);
        
        variablesReference();
        
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_point, menu);
        return true;
    }
	
    private void variablesReference() {
		// TODO Auto-generated method stub
        counter = 0;
        add = (Button) findViewById (R.id.bAdd_one);
        sub = (Button) findViewById (R.id.bSub_one);
        display = (TextView) findViewById (R.id.tvNumber);
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bAdd_one:
			counter++;
			
			break;
			
		case R.id.bSub_one:
			counter--;
			
			break;
			
		default:
			break;
		}
		
		display.setText(new Integer(counter).toString());
	}
}
