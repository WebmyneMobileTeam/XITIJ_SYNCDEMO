package com.xitij.android.syncdemojumpsum.helpers;

/**
 * @author jatin
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Functions {

	public static float convertDpToPixel(float dp, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 *
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static void logD(final String tag, final String logMsg) {
		if (Constants.LOGGING_ENABLED)
			if (logMsg.length() > 4000) {
				Log.d(tag, logMsg.substring(0, 4000));
				logD(tag, logMsg.substring(4000));
			} else
				Log.d(tag, logMsg);
	}

	public static void displayMessage(Context ctx,String msg){

		Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();

	}

	public static void fireIntent(Activity activity,Class cls){

		Intent i = new Intent(activity,cls);
		activity.startActivity(i);

	}


	public static Typeface setFontBold(Context context) {
		Typeface font = Typeface.createFromAsset(context.getAssets(),
				"fonts/HelveticaNeue-CondensedBold.otf");
		return font;
	}

	public static Typeface setFont(Context context) {
		Typeface font = Typeface.createFromAsset(context.getAssets(),
				"fonts/HelveticaNeue.otf");
		return font;
	}

	public static boolean emailValidation(String email) {
		boolean validEmailAddress = true;
		if (email.length() == 0) {
			validEmailAddress = false;
		}

		else {
			if (!email.contains(".") || !email.contains("@")) {
				validEmailAddress = false;
			} else {
				int index1 = email.indexOf("@");
				String subStringType = email.substring(index1);
				int index2 = index1 + subStringType.indexOf(".");
				if (index1 == 0 || index2 == 0) {
					validEmailAddress = false;
				} else {
					String typeOf = email.substring(index1, index2);
					if (typeOf.length() < 1) {
						validEmailAddress = false;
					}
					String typeOf2 = email.substring(index2);
					if (typeOf2.length() < 2) {
						validEmailAddress = false;
					}
				}

			}
		}

		return validEmailAddress;
	}

	public static String md5(String input) {

		String md5 = null;

		if (null == input)
			return null;

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}


}