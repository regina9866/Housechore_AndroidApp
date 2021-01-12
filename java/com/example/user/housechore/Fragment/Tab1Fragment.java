package com.example.user.housechore.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.housechore.TabLayoutActivity;
import com.example.user.housechore.R;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab 1 Fragment";
    //    private OnPhoneHeightChangeListener mListener;
    TabLayoutActivity myActivity;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    public static Tab1Fragment newInstance() {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myActivity= (TabLayoutActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_add_chore
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        if(savedInstanceState==null){
            myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreListFragment, 3, 0);
        }

    }


    @Override
    public void onResume() {
        super.onResume();

    }
    public void onButtonPressed(Uri uri) {
//
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnPhoneHeightChangeListener) {
//            mListener = (OnPhoneHeightChangeListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnPhoneHeightChangeListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

}
