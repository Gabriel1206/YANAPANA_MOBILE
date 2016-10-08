package pe.edu.upc.yanapan.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import pe.edu.upc.yanapan.R;

/**
 * Created by Juan on 07/10/2016.
 */

public class Tab2 extends Fragment  {

    Spinner disabiltys;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //disabiltys = (Spinner) disabiltys.findViewById(R.id.sp);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.disabilitys,android.R.layout.simple_spinner_item);
       // disabiltys.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab2, container, false);

    }

}
