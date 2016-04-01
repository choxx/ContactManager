package com.choxx.contactmanager;

import java.util.ArrayList;

import android.R.color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ViewContact extends Activity {

	EditText etFname,etLname,etHome,etOffice,etAddr,etEmail,etOrg,etNote;
	SQLiteDatabase db;
	ImageButton ibEdit,ibSave,ibDel,ibHomeCall,ibOffCall;
	String del,f1,l1,h1,o1,a1,e1,or1,n1;
	ArrayList <String>listFname,listLname,listHome,listOffice,listAddr,listEmail,listOrg,listNote;
	
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		ActionBar actionbar=getActionBar();
		actionbar.setIcon(R.drawable.ic_view_contact);
		etFname=(EditText) findViewById(R.id.etFname);
		//etLname=(EditText) findViewById(R.id.etLname);
		etHome=(EditText) findViewById(R.id.etHome);
		ibDel=(ImageButton) findViewById(R.id.ibDel);
		ibHomeCall=(ImageButton) findViewById(R.id.ibHomeCall);
		ibOffCall=(ImageButton) findViewById(R.id.ibOffCall);
		etOffice=(EditText) findViewById(R.id.etOffice);
		etAddr=(EditText) findViewById(R.id.etAddr);
		etEmail=(EditText) findViewById(R.id.etEmail);
		etOrg=(EditText) findViewById(R.id.etOrg);
		etNote=(EditText) findViewById(R.id.etNote);
		etFname.setShadowLayer(10, 1, -3, Color.RED);
		///etLname.setShadowLayer(10, 1, -3, Color.RED);
		ibEdit=(ImageButton) findViewById(R.id.ibEdit);
		ibSave=(ImageButton) findViewById(R.id.ibSave);
		ibSave.setVisibility(4);
		
		
		Bundle b=getIntent().getExtras();
		int pos=b.getInt("pos");
		String searchListNum=b.getString("num");
		
		
		db=openOrCreateDatabase("cmdb", MODE_PRIVATE, null);
		
		
		
		//-----------------for displaying contact---------------
		
		if(b.toString().contains("pos"))	//to check whether bundle's received key contains "pos"-------------
		{
		Cursor c0=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listFname=new ArrayList<String>();
		while(c0.moveToNext())
		{
			listFname.add(c0.getString(0));
		}
		String f=(String)listFname.get(pos);
		actionbar.setTitle(f);
		etFname.setText(f);
		
		Cursor c1=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listLname=new ArrayList<String>();
		while(c1.moveToNext())
		{
			listLname.add(c1.getString(1));
		}
		String l=(String)listLname.get(pos);
		//etLname.setText(l);
		
		Cursor c2=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listHome=new ArrayList<String>();
		while(c2.moveToNext())
		{
			listHome.add(c2.getString(2));
		}
		String h=(String)listHome.get(pos);
		etHome.setText(h);
		
		Cursor c3=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listOffice=new ArrayList<String>();
		while(c3.moveToNext())
		{
			listOffice.add(c3.getString(3));
		}
		String office=(String)listOffice.get(pos);
		etOffice.setText(office);
		
		Cursor c4=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listAddr=new ArrayList<String>();
		while(c4.moveToNext())
		{
			listAddr.add(c4.getString(4));
		}
		String addr=(String)listAddr.get(pos);
		etAddr.setText(addr);
		
		Cursor c5=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listEmail=new ArrayList<String>();
		while(c5.moveToNext())
		{
			listEmail.add(c5.getString(5));
		}
		String email=(String)listEmail.get(pos);
		etEmail.setText(email);
		
		Cursor c6=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listOrg=new ArrayList<String>();
		while(c6.moveToNext())
		{
			listOrg.add(c6.getString(6));
		}
		String org=(String)listOrg.get(pos);
		etOrg.setText(org);
		
		Cursor c7=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		listNote=new ArrayList<String>();
		while(c7.moveToNext())
		{
			listNote.add(c7.getString(7));
		}
		String note=(String)listNote.get(pos);
		etNote.setText(note);
		}//if closed----------
		
		
		
		else	//to display the details of contact in search list------------------
			if(b.toString().contains("num"))	//to check whether bundle's received key contains "num"-------------
			{
				
				
				Cursor c0=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				//Cursor c0=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
				listFname=new ArrayList<String>();
				while(c0.moveToNext())
				{
					listFname.add(c0.getString(0));
				}
				String f=(String)listFname.get(0);
				actionbar.setTitle(f);
				etFname.setText(f);
				
				
				Cursor c1=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listLname=new ArrayList<String>();
				while(c1.moveToNext())
				{
					listLname.add(c1.getString(1));
				}
				String l=(String)listLname.get(0);
				//etLname.setText(l);
				
				Cursor c2=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listHome=new ArrayList<String>();
				while(c2.moveToNext())
				{
					listHome.add(c2.getString(2));
				}
				String h=(String)listHome.get(0);
				etHome.setText(h);
				
				Cursor c3=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listOffice=new ArrayList<String>();
				while(c3.moveToNext())
				{
					listOffice.add(c3.getString(3));
				}
				String office=(String)listOffice.get(0);
				etOffice.setText(office);
				
				Cursor c4=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listAddr=new ArrayList<String>();
				while(c4.moveToNext())
				{
					listAddr.add(c4.getString(4));
				}
				String addr=(String)listAddr.get(0);
				etAddr.setText(addr);
				
				Cursor c5=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listEmail=new ArrayList<String>();
				while(c5.moveToNext())
				{
					listEmail.add(c5.getString(5));
				}
				String email=(String)listEmail.get(0);
				etEmail.setText(email);
				
				Cursor c6=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listOrg=new ArrayList<String>();
				while(c6.moveToNext())
				{
					listOrg.add(c6.getString(6));
				}
				String org=(String)listOrg.get(0);
				etOrg.setText(org);
				
				Cursor c7=db.rawQuery("select * from addcontact WHERE home='"+searchListNum+"'",null);
				listNote=new ArrayList<String>();
				while(c7.moveToNext())
				{
					listNote.add(c7.getString(7));
				}
				String note=(String)listNote.get(0);
				etNote.setText(note);
				
				//Toast.makeText(this, "contains num", 2000).show();
			}
		
		
		del=etHome.getText().toString();	// to delete the contact storing in a string
		
		
		//--------------------- editing contact---------------
		ibEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// just hiding the edit and save buttons or vice versa
				ibEdit.setVisibility(4);
				ibSave.setVisibility(0);
				
				
				 
				
			}
		});
		
		
		//----------------saving contact after editing---------
		ibSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("delete from addcontact WHERE home='"+del+"'");
				
				f1=etFname.getText().toString();
				 l1="";
				 h1=etHome.getText().toString().trim();
				 n1=etNote.getText().toString().trim();
				 o1=etOffice.getText().toString().trim();
				 a1=etAddr.getText().toString();
				 e1=etEmail.getText().toString();
				 or1=etOrg.getText().toString();
				
				
				db.execSQL("insert into addcontact values('"+f1+"','"+l1+"','"+h1+"','"+o1+"','"+a1+"','"+e1+"','"+or1+"','"+n1+"')");
				Toast.makeText(ViewContact.this, "Saved", 2000).show();
				ibSave.setVisibility(4);
				ibEdit.setVisibility(0);
			}
		});
		
		
		
		//---------------Deleting contact-----------
		ibDel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder b=new AlertDialog.Builder(ViewContact.this);
				b.setTitle("Confirmation");
				b.setMessage("Are you sure you want to Delete??");
				b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						db.execSQL("delete from addcontact WHERE home='"+etHome.getText().toString()+"'");
						
						startActivity(new Intent(ViewContact.this,AfterRegActivity.class));
						finish();
						Toast.makeText(ViewContact.this, "Deleted Successfully..", 2000).show();
							
					}
				});
				b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					return;	
					}
				});
				b.show();	
				
				
				
			}
		});
		
		
		//-----------------calling the number------------
		
		ibHomeCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(etHome.getText().length()>0)
				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+etHome.getText().toString())));
			}
		});
		
		ibOffCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(etOffice.getText().length()>0)
				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+etOffice.getText().toString())));
			}
		});
		
	}// onCreate closed
	

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(this,AfterRegActivity.class));
	}

	
}// class closed
