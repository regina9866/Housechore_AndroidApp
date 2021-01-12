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


public class Tab4Fragment extends Fragment {
    private static final String TAG = "Tab 4 Fragment";
    TabLayoutActivity myActivity;

//    private OnPhoneHeightChangeListener mListener;

    public Tab4Fragment() {
        // Required empty public constructor
    }

    public static Tab4Fragment newInstance() {
        Tab4Fragment fragment = new Tab4Fragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab4, container, false);
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        if(savedInstanceState==null){
            myActivity.ReplaceFragment(Enums.FragmentEnums.SettingFragment, 3, 0);
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
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
    public void onResume() {
        super.onResume();
//        myActivity.ReplaceFragment(Enums.FragmentEnums.BowlListFragment, 3, 0);
//        myActivity.ReplaceFragment(Enums.FragmentEnums.BowlAddFragment, 3, 0);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnPhoneHeightChangeListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
