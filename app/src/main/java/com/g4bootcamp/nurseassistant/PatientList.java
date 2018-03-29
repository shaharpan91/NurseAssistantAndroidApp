package com.g4bootcamp.nurseassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PatientList extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView mListView;
    ArrayList<Patient> patientList;
    String URL = "http://192.168.56.1:8080/RestWebAPIExample/GetMethod/patList?key=SHARED_KEY";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PatientList() {
        // Required empty public constructor
    }


    public static PatientList newInstance(String param1, String param2) {
        PatientList fragment = new PatientList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.d("sort","PatientList new instance");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);


        patientList = new ArrayList<Patient>(Resourses.patientList);


        mListView = (ListView) view.findViewById(R.id.patient_list_view);

        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_patien_list_header, mListView, false);
        mListView.addHeaderView(header);
        final PatientListAdapter adapter = new PatientListAdapter(getContext(), patientList);
        mListView.setAdapter(adapter);
        final TextView namesurname = (TextView) view.findViewById(R.id.patient_header_name);
        final TextView room = (TextView) view.findViewById(R.id.patient_header_room);
        final TextView floor = (TextView) view.findViewById(R.id.patient_header_floor);

        namesurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sort", "Sort by name clicked");
                Resourses.sortByName();
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.getFragments().clear();
                fragmentManager.beginTransaction().replace(R.id.patient_list, PatientList.newInstance("name", "")).commit();
            }
        });
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sort", "Sort by room clicked");
                Resourses.sortByRoom();
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.getFragments().clear();
                fragmentManager.beginTransaction().replace(R.id.patient_list, PatientList.newInstance("room", "")).commit();
            }
        });

        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sort", "Sort by floor clicked");
                Resourses.sortByFloor();
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.patient_list, PatientList.newInstance("floor", "")).commit();
                fragmentManager.getFragments().clear();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0) {
                    Intent intent = new Intent(view.getContext(), PatientViewActivity.class);

                    intent.putExtra("patient", patientList.get(position - 1));

                    startActivity(intent);
                }


            }
        });


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
