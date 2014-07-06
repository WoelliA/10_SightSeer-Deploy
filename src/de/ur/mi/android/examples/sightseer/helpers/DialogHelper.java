package de.ur.mi.android.examples.sightseer.helpers;

import de.ur.mi.android.examples.sightseer.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {
	
	public static  ProgressDialog getProgressDialog(Context context, String dialogTitle, String dialogText) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(dialogTitle);
		progressDialog.setMessage(dialogText);
		return progressDialog;
	}
	
	public static AlertDialog getErrorDialog(Context context, String title, String msg, boolean cancelable) {
		String confirm = context.getResources().getString(R.string.error_dialog_confirm);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage(msg);
		alertDialogBuilder.setPositiveButton(confirm, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		return alertDialogBuilder.create();
	}

}
