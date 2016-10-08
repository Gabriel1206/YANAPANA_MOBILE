package pe.edu.upc.yanapan.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import pe.edu.upc.yanapan.R;
import pe.edu.upc.yanapan.tabs.Tab1;
import pe.edu.upc.yanapan.tabs.Tab2;
import pe.edu.upc.yanapan.tabs.Tab3;

public class VisitActivity extends FragmentActivity {

    private FragmentTabHost tabHost;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Personal"),
                Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Visitation Data"),
                Tab2.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Photo"),
                Tab3.class, null);
    }
}
