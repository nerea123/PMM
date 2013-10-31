package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

public class Check extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		final TextView t1=(TextView)findViewById(R.id.txtc);
		final TextView t2=(TextView)findViewById(R.id.tc);
		final CheckBox c1=(CheckBox)findViewById(R.id.mono);
		final CheckBox c2=(CheckBox)findViewById(R.id.netbeans);
		final CheckBox c3=(CheckBox)findViewById(R.id.eclipse);
		final CheckBox c4=(CheckBox)findViewById(R.id.as);
		final Button b=(Button)findViewById(R.id.bcheck);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				t2.setText("Usas: \n");
				if(c1.isChecked())
					t2.setText(t2.getText()+"      -"+c1.getText()+"\n");
				if(c2.isChecked())
					t2.setText(t2.getText()+"      -"+c2.getText()+"\n");
				if(c3.isChecked())
					t2.setText(t2.getText()+"      -"+c3.getText()+"\n");
				if(c4.isChecked())
					t2.setText(t2.getText()+"      -"+c4.getText()+"\n");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check, menu);
		return true;
	}

}
