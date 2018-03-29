package com.g4bootcamp.nurseassistant;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientViewActivity extends AppCompatActivity {

    public Toolbar mToolBar;
    private ListView mListView;
    private TextView personalcode;
    private TextView room;
    private TextView floor;
    ArrayList<Recipe> recipeList;
    String urlstring = new String();
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_view_activity);


        Patient keyPatient = (Patient) getIntent().getSerializableExtra("patient");


        recipeList = new ArrayList<Recipe>(Resourses.getRecipeList(keyPatient));


        mListView = (ListView) findViewById(R.id.recipe_list_view);

        ViewGroup header = (ViewGroup)getLayoutInflater().inflate(R.layout.recipe_list_header,mListView ,false);

        mListView.addHeaderView(header);
        mToolBar = (Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle(keyPatient.getFirstname() + " " + keyPatient.getLastname());

        mToolBar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientViewActivity.this.finish();
            }
        });

        personalcode = (TextView) findViewById(R.id.patient_view_code2);
        room = (TextView) findViewById(R.id.patient_view_room2);
        floor = (TextView) findViewById(R.id.patient_view_floor2);

        personalcode.setText(keyPatient.getPersonalCode());
        room.setText(String.valueOf(keyPatient.getRoom()));
        floor.setText(String.valueOf(keyPatient.getFloor()));

        RecipeListAdapter adapter = new RecipeListAdapter(this, recipeList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(PatientViewActivity.this, Integer.toString(position),Toast.LENGTH_SHORT).show();

            }});





    }

    public class RecipeListAdapter extends BaseAdapter {
        private Context context; //context
        private ArrayList<Recipe> mDataSource; //data source of the list adapter

        //public constructor
        public RecipeListAdapter(Context context, ArrayList<Recipe> items) {
            this.context = context;
            this.mDataSource = items;
        }

        @Override
        public int getCount() {
            return mDataSource.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return mDataSource.get(position); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // inflate the layout for each list row
            if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.recipe_list_item, parent, false);
            }

            // get current item to be displayed
            Recipe currentItem = (Recipe) getItem(position);

            // get the TextView for item name and item description
            TextView TVname = (TextView)
                    convertView.findViewById(R.id.recipe_list_name);
            TextView TVdosage = (TextView)
                    convertView.findViewById(R.id.recipe_list_dosage);
            TextView TVtime = (TextView)
                    convertView.findViewById(R.id.recipe_list_time);

            //sets the text for item name and item description from the current item object
            TVname.setText(mDataSource.get(position).getName());
            TVdosage.setText(mDataSource.get(position).getDosage());
            TVtime.setText(mDataSource.get(position).getTime());

            // returns the view for the current row
            return convertView;
        }



    }




















//    public class RecipeListAdapter extends BaseAdapter {
//
//        private Context mContext;
//        private LayoutInflater mInflater;
//        private List<Recipe> mDataSource;
//
//        public RecipeListAdapter(Context context, ArrayList<Recipe> items) {
//            mContext = context;
//            mDataSource = items;
//            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return mDataSource.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = convertView;
//
//
//
//            if(view == null)
//                view = LayoutInflater.from(mContext).inflate(R.layout.recipe_list,parent,false);
//
//            TextView titleTextView =
//                    (TextView) view.findViewById(R.id.recipe_list_name);
//
//// Get subtitle element
//            TextView subtitleTextView =
//                    (TextView) view.findViewById(R.id.recipe_list_dosage);
//
//// Get detail element
//            TextView detailTextView =
//                    (TextView) view.findViewById(R.id.recipe_list_time);
//
//// Get thumbnail element
//
//
//
//
//
//
//// 2
//            titleTextView.setText(mDataSource.get(position).getName());
//
//            subtitleTextView.setText(mDataSource.get(position).getDosage());
//
//            detailTextView.setText(mDataSource.get(position).getTime());
//            return view;
//        }
//    }
}
