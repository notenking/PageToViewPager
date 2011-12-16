package me.test.pagestack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class PageStackTestProjectActivity extends FragmentActivity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_layout);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.content, new FragmentA());
		ft.commit();
    }
   
}