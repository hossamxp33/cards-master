package com.codesroots.mac.cards.DataLayer.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceHelper {

	private static SharedPreferences app_prefs;
	private static String app_ref = "userdetails";
	private static String photo = "photo";
	private static String Token = "auth";
	private static String UserId = "userid";
	private static String UserGroupId = "UserGroupId";
	private static String ROOMID = "ROOMID";
	private static String INCHAT = "INCHAT";
	private final String CURRENTLAT = "latitude";
	private final String CURRENTLONG = "longtude";
	private final String CURRENTCATEGRY = "CURRENTCATEGRY";
	private Context context;

	public PreferenceHelper(Context context) {
		this.context = context;
		try {
			app_prefs = context.getSharedPreferences(app_ref,
					Context.MODE_PRIVATE);
		}
		catch (NullPointerException e)
		{

		}
	}


	public void setCURRENTLAT(String currentlat) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLAT, currentlat);
		edit.apply();
	}

	public void setCURRENTLONG(String currentlong) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLONG, currentlong);
		edit.apply();
	}

	public void setCURRENTCATEGRY(String currentcategry) {

		Editor edit = app_prefs.edit();
		edit.putString(CURRENTCATEGRY, currentcategry);
		edit.apply();
	}

	public String getCURRENTCATEGRY() {
		return app_prefs.getString(CURRENTCATEGRY, null);
	}

	public String getCURRENTLAT() {
		return app_prefs.getString(CURRENTLAT, null);
	}

	public String getCURRENTLONG()  {
		return app_prefs.getString(CURRENTLONG, "0");
	}

	public static String getToken() {

		return app_prefs.getString(Token,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImV4cCI6MTU3Mzk2ODg3MH0.w45qrjLBSXaGfHRATa33EwGG-IfPkhKbnZSbflWRLSo");
	}

	public static void setToken(String API_TOKEN, Context context) {
		app_prefs = context.getSharedPreferences(app_ref,
				Context.MODE_PRIVATE);
		Editor edit = app_prefs.edit();
		edit.putString(Token, API_TOKEN);
		edit.apply();
	}

	public static int getUserId() {
		return app_prefs.getInt(UserId,0);
	}
	public static int getUserGroupId() {
		return app_prefs.getInt(UserGroupId,0);
	}
	public static void setROOMID(String roomid) {
		Editor edit = app_prefs.edit();
		edit.putString(ROOMID, roomid);
		edit.apply();
	}

	public static String getROOMID() {
		return app_prefs.getString(ROOMID,null);
	}

	public String getphoto() {
		return app_prefs.getString(photo,null);
	}
	public static Boolean getinchat() {
		return app_prefs.getBoolean(INCHAT,false);
	}

	public static void setUserId(int user_id) {
		Editor edit = app_prefs.edit();
		edit.putInt(UserId, user_id);
		edit.apply();
	}
	public static void setUserGroupId(int userGroupId) {
		Editor edit = app_prefs.edit();
		edit.putInt(UserGroupId, userGroupId);
		edit.apply();
	}

	public static void setINCHAT(boolean inchat) {
		Editor edit = app_prefs.edit();
		edit.putBoolean(INCHAT, inchat);
		edit.apply();
	}


	public void setphoto(String uri){
		Editor edit = app_prefs.edit();
		edit.putString(photo, uri);
		edit.apply();
	}


	public void clearRequestData() {

	}

}
