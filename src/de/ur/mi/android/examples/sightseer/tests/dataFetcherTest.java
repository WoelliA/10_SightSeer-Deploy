package de.ur.mi.android.examples.sightseer.tests;

import java.util.ArrayList;

import org.junit.Test;

import de.ur.mi.android.examples.sightseer.data.DataFetcher;
import de.ur.mi.android.examples.sightseer.data.IDataFetcherListener;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import junit.framework.TestCase;

public class dataFetcherTest extends TestCase {

	public dataFetcherTest(String name) {
		super(name);
	}

	@Test
	public void dataFetcherTest() {
		DataFetcher df = new DataFetcher(new IDataFetcherListener() {

			@Override
			public void onError(int error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDataFetched(ArrayList<PointOfInterest> locations) {
				// TODO Auto-generated method stub

			}
		});
		df.startGetResults();
	};
}
