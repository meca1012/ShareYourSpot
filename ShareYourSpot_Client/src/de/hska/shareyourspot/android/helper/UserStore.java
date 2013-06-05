package de.hska.shareyourspot.android.helper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import de.hska.shareyourspot.android.activites.MainActivity;
import de.hska.shareyourspot.android.domain.User;



public class UserStore {

	private User user;
	private final String FILENAME = "SYSappUser";
	//private String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	public UserStore(){}
	
	public void storeUser(Context ctx, User user) throws IOException
	{
		String xmlStream = serialize(user);		
		
		FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		fos.write(xmlStream.getBytes());
		fos.close();
	}
	
	public User getUser(Context ctx) throws IOException
	{
		int ch;
		StringBuffer fileContent = new StringBuffer("");
		FileInputStream fis;
		try {
		    fis = ctx.openFileInput(FILENAME);
		    try {
		        while( (ch = fis.read()) != -1)
		            fileContent.append((char)ch);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}

		String data = new String(fileContent);
		return (User)deserialize(data);
	}
	
	public void deleteUser(Context ctx)
	{
		try 
		{
		    ctx.deleteFile(FILENAME);
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		   
	}
	
	public void logout(Context ctx)
	{
		 	this.deleteUser(ctx);
	        Intent intent = new Intent(ctx, MainActivity.class);
	        intent.putExtra("finish", true);
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
	        ctx.startActivity(intent);
	}
	
	private String serialize(Object obj) {
		Serializer serializer = new Persister();
		String output = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			serializer.write(obj, os);
			output = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	private Object deserialize(String xmlStr) {
		Serializer serializer = new Persister();
		Object user = null;
		try {
			System.out.println(xmlStr);
			user = serializer.read(User.class, xmlStr);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Problem with SimpleXML");
		}
		finally
		{
			return user;
			
		}
	}
}
