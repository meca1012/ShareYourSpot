package de.hska.shareyourspot.android.helper;

import de.hska.shareyourspot.android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class AlertHelper
{
	private int title;
	private int message;
	private AlertDialog.Builder alertDialogBuilder;
	
	public AlertHelper(Context context,int passwordsnotmatchingtitle, int passwordsnotmatchingtext)
	{
		this.alertDialogBuilder = new  AlertDialog.Builder(context);
		this.title = passwordsnotmatchingtitle;
		this.message = passwordsnotmatchingtext;
	}
	
	public void fireAlert()
	{
		alertDialogBuilder.setTitle(this.title);
		alertDialogBuilder.setMessage(this.message);
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
	
	
//	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//	alertDialogBuilder.setTitle(R.string.passwordsNotMatchingTitle);
//	alertDialogBuilder.setMessage(R.string.passwordsNotMatchingText);
//	AlertDialog alertDialog = alertDialogBuilder.create();
//	alertDialog.show();

