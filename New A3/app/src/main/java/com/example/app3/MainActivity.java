package com.example.app3;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.OrientationListener;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.app3.TitlesFragment.ListSelectionListener;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static android.widget.ListPopupWindow.MATCH_PARENT;


public class MainActivity extends AppCompatActivity implements ListSelectionListener {

    private android.support.v4.app.FragmentManager mFragmentManager;
    private static final String TAG = "MainActivity";
    public static String[] phoneListArray;
    public static TypedArray phoneImages;
    private TitlesFragment phoneListFragment;
    private FrameLayout phoneFrameLayout, imageFrameLayout;
    private ImagesFragment imagesFragment = new ImagesFragment();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phoneListArray = getResources().getStringArray(R.array.phones);
        phoneImages = getResources().obtainTypedArray(R.array.phoneImages);
        setContentView(R.layout.activity_main);

        // Get references to the TitleFragment and to the QuotesFragment
        phoneFrameLayout = (FrameLayout) findViewById(R.id.phoneFragmentContainer);
        imageFrameLayout = (FrameLayout) findViewById(R.id.imageFragmentContainer);

        ImageView imageView = findViewById(R.id.appBarImage);
        imageView.setImageResource(R.drawable.buypic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //phoneListFragment = (TitlesFragment) getFragmentManager().findFragmentById(R.id.phoneFragment);
        mFragmentManager = getSupportFragmentManager();

//        phoneListFragment = (TitlesFragment) mFragmentManager.findFragmentByTag("TitlesFragment");
//        imagesFragment = (ImagesFragment) mFragmentManager.findFragmentByTag("ImagesFragment");

        android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.phoneFragmentContainer, new TitlesFragment());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();


        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }
    private void setLayout() {

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == ORIENTATION_PORTRAIT) {
            // Determine whether the QuoteFragment has been added
            if (!imagesFragment.isAdded()) {

                // Make the TitleFragment occupy the entire layout
                phoneFrameLayout.setVisibility(View.VISIBLE);
                phoneFrameLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        phoneFrameLayout.getLayoutParams();
                params.setMargins(MATCH_PARENT, 195, MATCH_PARENT, MATCH_PARENT);
                phoneFrameLayout.setLayoutParams(params);

                imageFrameLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {
                phoneFrameLayout.setVisibility(View.GONE);
                //.setLayoutParams(new CoordinatorLayout.LayoutParams(0, MATCH_PARENT));

                imageFrameLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            }
        }
        else
        {

            // Make the TitleLayout take 1/3 of the layout's width
            phoneFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(0, MATCH_PARENT, 1));

            // Make the QuoteLayout take 2/3's of the layout's width
            imageFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT,2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option1) {
            String permission = "edu.uic.cs478.s19.kaboom";
            String TOAST_INTENT = "edu.uic.cs478.s19.broadcast";
            Intent i = new Intent(TOAST_INTENT);
            sendBroadcast(i, permission);
        }
        if (id == R.id.option_2) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }
    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {
        // If the QuoteFragment has not been added, add it now
        if (!imagesFragment.isAdded()) {

            // Start a new FragmentTransaction
            // UB 2/24/2019 -- Now must use compatible version of FragmentTransaction
            android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.imageFragmentContainer,
                    imagesFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (imagesFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            imagesFragment.showImageAtIndex(index);

        }
    }
}
