package com.g4bootcamp.nurseassistant;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PatientsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PatientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientsFragment extends Fragment {

    private TabLayout tabLayout;



    private int[] tabIcons = {
            R.mipmap.ic_brightness_6_white_24dp,
            R.mipmap.ic_brightness_5_white_24dp,
            R.mipmap.ic_brightness_3_white_24dp
    };

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PatientsFragment() {
        // Required empty public constructor
    }

    public static PatientsFragment newInstance(String param1, String param2) {
        PatientsFragment fragment = new PatientsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_patients, container, false);

        View result=inflater.inflate(R.layout.fragment_patients, container, false);
        ViewPager pager=(ViewPager)result.findViewById(R.id.vpPager);
        tabLayout = (TabLayout) result.findViewById(R.id.tabs);




        pager.setAdapter(buildAdapter());

        tabLayout.setupWithViewPager(pager);

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        setupTabIcons();

        return(result);
    }

    private PagerAdapter buildAdapter() {

        return(new MyPagerAdapter(getChildFragmentManager()));
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

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return PatientList.newInstance("name","morning");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return PatientList.newInstance("name","day");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return PatientList.newInstance("name","night");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }




}
