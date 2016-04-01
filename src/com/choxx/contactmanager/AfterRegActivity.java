package com.choxx.contactmanager;

import java.sql.Array;
import java.util.ArrayList;


import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class AfterRegActivity extends Activity {

	ArrayList<String> list,numlist, templist,tempnum;
	String cMenu[]={"Edit","Delete"};
	ListView contList;
	String al[];
	
	ImageButton ibSearch;
	EditText etSearch;
	String srch[];
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_reg);
		list=new ArrayList<String>();
		numlist=new ArrayList<String>();
		templist=new ArrayList<String>();
		tempnum=new ArrayList<String>();
		contList=(ListView)findViewById(R.id.contList);
		
		ibSearch=(ImageButton) findViewById(R.id.ibSearch);
		
		ActionBar actionbar=getActionBar();
		actionbar.setTitle("Menu");
		actionbar.setIcon(android.R.drawable.ic_menu_call);
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bgg));
		
		
		ibSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				etSearch.setVisibility(View.VISIBLE);
				etSearch.setBackgroundColor(Color.WHITE);
				ibSearch.setVisibility(View.INVISIBLE);
			}
		});
		etSearch=(EditText) findViewById(R.id.etSearch);
		
		db=openOrCreateDatabase("cmdb", MODE_PRIVATE, null);
		db.execSQL("create table if not exists addcontact(fname char(15),lname char(15),home varchar(12) primary key,office int(11) ,addr varchar(40),emailid varchar(20),"
				
				+"org char(20),note varchar(50)  )");
		
		Cursor c=db.rawQuery("select * from addcontact ORDER BY fname ASC",null);
		while(c.moveToNext())
		{
			list.add(c.getString(0));
			numlist.add(c.getString(2));
		}
		contList.setAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1,list));
		
		
		//-----------to open contact view---------
		contList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) 
			{
				Intent i=new Intent(AfterRegActivity.this,ViewContact.class);
				i.putExtra("pos", pos);
				
				startActivity(i);
			}
		});
		
		
		//-----------------Searching------------------
		etSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				 
				
				templist.clear();
				tempnum.clear();
				Cursor c1=db.rawQuery("select * from addcontact WHERE fname LIKE '"+s+"%' ORDER BY fname",null);
				while(c1.moveToNext())
				{
					templist.add(c1.getString(0));
					tempnum.add(c1.getString(2));
				}
				
				contList.setAdapter(new SearchAdapter(AfterRegActivity.this, android.R.layout.simple_list_item_1, templist));
				
				contList.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) 
					{
						
						Intent i=new Intent(AfterRegActivity.this,ViewContact.class);
						i.putExtra("num", tempnum.get(pos));
						
						startActivity(i);
					}
				});
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				 
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				 
				
			}
		});
				
	}// oncreate closed

	
	private class SearchAdapter extends ArrayAdapter
	{

		public SearchAdapter(Context context, int resource,ArrayList<String> templist) {
			super(context, resource, templist);
			 
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 
			
			LayoutInflater li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = li.inflate(R.layout.row,parent,false);
			
			TextView tv  = (TextView)row.findViewById(R.id.clname);
			TextView tvd = (TextView)row.findViewById(R.id.clnum);
			
			tv.setText(templist.get(position));
			tvd.setText(tempnum.get(position));
			
			return row;
		}//end of getView
	}
	
	
	
	
	private class MyAdapter extends ArrayAdapter
	{

		public MyAdapter(Context context, int resource,ArrayList<String> list) {
			super(context, resource, list);
			 
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 
			
			LayoutInflater li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = li.inflate(R.layout.row,parent,false);
			
			TextView tv  = (TextView)row.findViewById(R.id.clname);
			TextView tvd = (TextView)row.findViewById(R.id.clnum);
			
			tv.setText(list.get(position));
			tvd.setText(numlist.get(position));
			
			return row;
		}//end of getView
	}	// class myAdapter closed

	
	public void add(View v)
	{
		startActivity(new Intent(this,Add.class));
	}
	
	
	public void change(View v)
	{
		startActivity(new Intent(this,ResetPassword.class));
		
	}
	
	
	public void exit(View v)
	{
		
		AlertDialog.Builder b=new AlertDialog.Builder(this);
		b.setTitle("Confirmation");
		b.setMessage("Are you sure you want to log out??");
		b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 
				Toast.makeText(AfterRegActivity.this, "Logging Out...", 2000).show();
				
				finish();	
			}
		});
		b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 
			return;	
			}
		});
		b.show();	
		
		
	}// exit() closed
	
	@Override
	protected void onPause() {
		 
		super.onPause();
		finish();
	}
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		Toast.makeText(this, "Logged out..", 2000).show();
		finish();
	}
	
	
	
	
}// CLASS closed
