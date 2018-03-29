package com.g4bootcamp.nurseassistant;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
       PatientsFragment.OnFragmentInteractionListener,PatientList.OnFragmentInteractionListener,
        Account.OnFragmentInteractionListener, Settings.OnFragmentInteractionListener,
        Help.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nvDrawer;
    public Toolbar mToolBar;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        final android.app.ActionBar actionBar = getActionBar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mToolBar = findViewById(R.id.nav_actionbar);

        nvDrawer = (NavigationView) findViewById(R.id.nav_view);


        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.TVusername);
        name.setText(Resourses.getNursename());

        setupDrawerContent(nvDrawer);

        showInitialFragment();





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_patients:
                fragmentClass = PatientsFragment.class;
                break;
            case R.id.nav_account:
                fragmentClass = Account.class;
                break;
            case R.id.nav_settings:
                fragmentClass = Settings.class;
                break;
            case R.id.nav_help:
                fragmentClass = Help.class;
                break;
            default:
                fragmentClass = PatientsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    public void alertDialogFloor(){

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setTitle("Filter by floor");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Filter",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!input.getText().toString().equals("")){Resourses.filterByFloor(Integer.parseInt(input.getText().toString()));
                        dialog.dismiss();}
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


    }

    public void alertDialogRoom(){

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setTitle("Filter by floor");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Filter",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!input.getText().toString().equals("")){Resourses.filterByRoom(Integer.parseInt(input.getText().toString()));
                        dialog.dismiss();}
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }



        switch (item.getItemId()) {

            case R.id.action_filter_floor:

                alertDialogFloor();

                return true;
            case R.id.action_filter_room:

                alertDialogRoom();

                return true;
            case R.id.action_filter_reset:

                Resourses.filterReset();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void showInitialFragment(){

        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = PatientsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
