package com.choxx.contactmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Registration extends Activity {

	EditText etuname,etupass,secAns;
	File sdcard,myfolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(android.R.drawable.ic_menu_edit);

		
		etuname=(EditText) findViewById(R.id.etuname);
		secAns=(EditText) findViewById(R.id.secAns);
		etupass=(EditText) findViewById(R.id.etupass);
		sdcard=Environment.getExternalStorageDirectory();
		myfolder=new File(sdcard,"Contact Manager");
		//if(!myfolder.exists())
			myfolder.mkdir();
		File dir=new File(sdcard.getAbsoluteFile()+"/Contact Manager/.abcd");
		File dir1=new File(sdcard.getAbsoluteFile()+"/Contact Manager/.security");
		File dir2=new File(sdcard.getAbsoluteFile()+"/Contact Manager/.eid");
		
		
		if(!(dir).exists())
		{
			
				Toast.makeText(this, "Fill details to get Start..", 2000).show();
			
			//i=new Intent(this,Registration.class);
			//startActivity(i);
		}
		else
		{
			Intent i=new Intent(this,Login.class);
			startActivity(i);
			finish();
		}
	}
	public void register(View v)
	{
		String u,p,s;
		u=etuname.getText().toString().trim();
		p=etupass.getText().toString();
		s=secAns.getText().toString().trim();
		
		
		try {
			FileOutputStream fos=new FileOutputStream(myfolder.getAbsolutePath()+"/"+".abcd");
			//fos.write(u.getBytes());
			FileOutputStream fos1=new FileOutputStream(myfolder.getAbsolutePath()+"/"+".security");
			FileOutputStream fos2=new FileOutputStream(myfolder.getAbsolutePath()+"/"+".eid");
			fos.write(p.getBytes());
			fos.flush();
			fos.close();
			fos1.write(s.getBytes());
			fos1.flush();
			fos1.close();
			fos2.write(u.getBytes());
			fos2.flush();
			fos2.close();
			//Toast.makeText(this, "File saved:"+myfolder.getAbsolutePath()+"/"+u, 1000).show();
			Toast.makeText(this, "Registeration Successfull", 2000).show();
			//Intent i=new Intent(this,AfterRegActivity.class);
			Intent i=new Intent(this,Login.class);
			startActivity(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Registeration Unsuccessfull. Please reopen the application again"+e, 2000).show();
			finish();
		}
	finish();
	}
	
}
