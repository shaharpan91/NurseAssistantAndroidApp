package com.g4bootcamp.nurseassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Help.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Help#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Help extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Help() {
        // Required empty public constructor
    }


    public static Help newInstance(String param1, String param2) {
        Help fragment = new Help();
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
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        Button btn_call = (Button) view.findViewById(R.id.btn_call);
        Button btn_mail = (Button) view.findViewById(R.id.btn_mail);

        btn_call.setOnClickListener(this);
        btn_mail.setOnClickListener(this);

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
        
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_call:

                String num = getString(R.string.phonenumber);

                String uri = "tel:" + num.trim() ;
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse(uri));
                startActivity(call);


                break;

            case R.id.btn_mail:

                Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", getString(R.string.emailaddress), null));
                startActivity(Intent.createChooser(mail, "Send email..."));

                break;

        }


    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
