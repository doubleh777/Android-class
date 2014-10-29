package org.nhnnext.android.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.widget.ImageButton;


public class MainActivity extends Activity implements OnClickListener {

	private TextView textView1;
	private EditText editText1;
	private ImageView imageView1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button1 = (Button) findViewById(R.id.button1);
    	button1.setOnClickListener(this);
    	
    	ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
    	imageButton1.setOnClickListener(this);
    	
    	textView1 = (TextView) findViewById(R.id.textView1);
    	editText1 = (EditText) findViewById(R.id.editText1);
    	imageView1 = (ImageView) findViewById(R.id.imageView1);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			Toast.makeText(getApplicationContext(), "버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
			textView1.setText(editText1.getText().toString() + "이 입력되었습니다.");
			imageView1.setImageResource(R.drawable.gyunbin);
			break;
		case R.id.imageButton1:
			Toast.makeText(getApplicationContext(), "이미지 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
			textView1.setText(editText1.getText().toString() + "이 입력되었습니다.");
			imageView1.setImageResource(R.drawable.singer);
			break;
		}
		
	}
	

}
