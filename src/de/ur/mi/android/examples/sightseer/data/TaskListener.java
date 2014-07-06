package de.ur.mi.android.examples.sightseer.data;

public interface TaskListener<T> {
	public void onRequestFinished(T res);
	
	public void onRequestFailed(int errorID, String message);
}
