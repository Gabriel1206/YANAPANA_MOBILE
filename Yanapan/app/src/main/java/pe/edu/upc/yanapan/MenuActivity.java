package pe.edu.upc.yanapan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import android.support.design.widget.NavigationView;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import static android.R.attr.fragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.usuario1);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setImageDrawable(roundedDrawable);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.visits) {
            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        boolean fragmentTransaction = false;
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
            return super.onOptionsItemSelected(item);


        } else if (id == R.id.checkin) {
            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
            return super.onOptionsItemSelected(item);

        } else if (id == R.id.inform) {
            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
        } else if (id == R.id.nav_share) {
            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
        } else if (id == R.id.nav_send) {
            Intent visits = new Intent(this, VisitActivity.class);
            startActivity(visits);
        }


        return super.onOptionsItemSelected(item);
    }
}
