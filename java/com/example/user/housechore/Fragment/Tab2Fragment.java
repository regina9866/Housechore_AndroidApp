package com.example.user.housechore.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.housechore.R;
import com.example.user.housechore.TabLayoutActivity;


public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab 2 Fragment";
//    private OnPhoneHeightChangeListener mListener;
    TabLayoutActivity myActivity;

    public Tab2Fragment() {
        // Required empty public constructor
    }

      public static Tab2Fragment newInstance() {
        Tab2Fragment fragment = new Tab2Fragment();
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
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        if(savedInstanceState==null){
            myActivity.ReplaceFragment(Enums.FragmentEnums.ContactListFragment, 3, 0);
        }

    }


    @Override
    public void onResume() {
        super.onResume();

    }
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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

//
//    public interface OnPhoneHeightChangeListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
