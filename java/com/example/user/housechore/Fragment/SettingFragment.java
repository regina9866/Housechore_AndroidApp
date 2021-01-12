package com.example.user.housechore.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.housechore.LoginActivity;
import com.example.user.housechore.TabLayoutActivity;
import com.example.user.housechore.R;

public class SettingFragment extends Fragment {
    private static final String TAG = "Setting Fragment";

    private EditText mPhoneHeightEditText;
    private Button mSaveBtn;
    private SharedPreferences mSetting;
    private SharedPreferences.Editor mSettingEditor;
    private int mPhoneHeight;


    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting=getActivity().getSharedPreferences("setting",0);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayoutActivity myActivity =(TabLayoutActivity)getActivity();

        mPhoneHeight= (int) mSetting.getFloat(Constants.ARG_PHONE_HEIGHT,0);
        mSettingEditor=mSetting.edit();

        mPhoneHeightEditText = (EditText)view.findViewById(R.id.edit_text_Phone_Height);
        mPhoneHeightEditText.setText(String.valueOf(mPhoneHeight));

        mSaveBtn= (Button)view.findViewById(R.id.btn_savePhoneHeight);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneHeight=Integer.parseInt(mPhoneHeightEditText.getText().toString());
                mSettingEditor.putFloat(Constants.ARG_PHONE_HEIGHT,mPhoneHeight);
//                mSettingEditor.commit();
                Toast.makeText(getActivity(),"잠금 비밀번호를 바꿨습니다"+mPhoneHeight,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,null);
    }

    @Override
    public void onStop() {
        super.onStop();
        mSettingEditor.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSettingEditor.commit();
        Intent i=new Intent(SettingFragment.this.getContext(),LoginActivity.class);
        i.putExtra("password",mPhoneHeight);
        startActivity(i);

    }



}
