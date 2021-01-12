package com.example.user.housechore.Fragment.Family;


import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.housechore.Data.DatabaseHelper;
import com.example.user.housechore.Model.Contact;
import com.example.user.housechore.R;
//import com.example.user.housechore.RotatorProgressDialogTask;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ContactAddFragment extends Fragment {
    //??륁젟 獄?ADD
    private static final String TAG="ChoreAddFragment";
    private boolean InEditMode;
    //    private Chore mChore;
    Contact mContact;
    private View mRootView;
    private DatabaseHelper db;
//    private RotatorProgressDialogTask task;

    //Image properties
    private String mCurrentImagePath = null;
    private Uri mCapturedImageURI = null;


    private EditText mTitleEditText,
            mIngredientEditText,
            mRecipeOrderEditText,
            mAmountEditText;
    private Context mContext;


    private LinearLayout mRecipeOrderLayout;

    private EditText mAddChoreOrderEditText;
    private int mRecipeOrderNum;
    private Button mSaveBtn, mImageSelectBtn, mAddRecipeOrderBtn, mAmountMinus,mAmountPlus;
    //===========================================================================================
    private EditText mContactName,
            mAssignEditText,
            mPhone;
    private Button mHowlongBtn, mRepeatBtn,mPointBtn;

    private ImageView mImageView;

    private int mStar=0;

    OnContactSavedListener mCallback;

    public static ContactAddFragment newInstance(int sectionNumber) {
        ContactAddFragment fragment = new ContactAddFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    public ContactAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity());
        mRecipeOrderNum=1;
        mContext=this.getContext();
        // Ensure there is a saved instance state.
        if (savedInstanceState != null) {

            // Get the saved Image uri string.
            String ImageUriString = savedInstanceState.getString(Constants.KEY_IMAGE_URI);

            // Restore the Image uri from the Image uri string.
            if (ImageUriString != null) {
                mCapturedImageURI = Uri.parse(ImageUriString);
            }
            mCurrentImagePath = savedInstanceState.getString(Constants.KEY_IMAGE_URI);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCapturedImageURI != null) {
            outState.putString(Constants.KEY_IMAGE_URI, mCapturedImageURI.toString());
        }
        outState.putString(Constants.KEY_IMAGE_PATH, mCurrentImagePath);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_add_chore
        mRootView = inflater.inflate(R.layout.fragment_add_contact , container, false);
        InitializeViews();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContact = new Contact();
    }



    private void InitializeViews() {

        mContactName = (EditText) mRootView.findViewById(R.id.FirstName);
        mPhone =(EditText)mRootView.findViewById(R.id.Phone);
//        mImageView = (ImageView) mRootView.findViewById(R.id.imageView1);
//
//        mImageSelectBtn = (Button) mRootView.findViewById(R.id.btn_select_gallery);
//        mImageSelectBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
////                chooseImage();
//            }
//        });

        mSaveBtn = (Button) mRootView.findViewById(R.id.buttonAdd);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(){
                    public void handleMessage(Message msg){
                        SaveContact();
                    }
                };
                handler.sendEmptyMessageDelayed(0,2000);

//                task = new RotatorProgressDialogTask();
//                task.startProcess(mContext);

            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnContactSavedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    private void ChangeAmount(View view){
        int amount = Integer.parseInt(mAmountEditText.getText().toString());
        if(view==mAmountMinus){
            if(amount>0) {  amount--;  }
        }else if(view ==mAmountPlus){
            amount++;
        }
        mAmountEditText.setText(String.valueOf(amount));
    }

    private String mergeChoreOrder(){
        String mergedOrder="";

        for(int i=0;i<mRecipeOrderNum;i++)
        {
            Log.d(TAG,"NUM:"+mRecipeOrderNum+" \n"+mRecipeOrderLayout.getChildAt(i));
            EditText temp = (EditText)mRecipeOrderLayout.getChildAt(i);
            mergedOrder+=temp.getText().toString()+Constants.SPLIT_DELIMINATOR;
        }
        return mergedOrder;
    }

    private void SaveContact(){
        mContact.setName(mContactName.getText().toString());
//
//        mChore.setAmount(mAmountEditText.getText().toString());
//        mChore.setStar("0");

        //Check to see if there is valid image path temporarily in memory
        //Then save that image path to the database and that becomes the profile
        //Image for this user.
        if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty())
        {
//            mContact.setImagePath(mCurrentImagePath);
        }

        long result = db.addContact(mContact);
        if (result == -1 ){
            Toast.makeText(getActivity(), "Unable to add Chore: " + mContact.getName(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "Chore: " + mContact.getName()+" Saved", Toast.LENGTH_LONG).show();

//            mTitleEditText.setText("");
            mContactName.setText("");
            mPhone.setText("");
//            mIngredientEditText.setText("");
//            mRecipeOrderEditText.setText("");
//            mAmountEditText.setText("0");
//            mImageView.setImageDrawable(mContact.setDefaultImage(getActivity()));

        }

        mContactName.setError(null);
        mCallback.onContactSaved();
        getActivity().onBackPressed();
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        CursorLoader cursorLoader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Constants.SELECT_IMAGE)
        {
            try {
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                mImageView.setImageBitmap(image_bitmap);
            }catch (FileNotFoundException e){ e.printStackTrace();}
            catch (IOException e)                 {		e.printStackTrace(); 			}
            catch (Exception e)		         {             e.printStackTrace();			}
//            Bundle extras2 = data.getExtras();
//            if (extras2 != null) {
//                Bitmap photo = extras2.getParcelable("data");
//                mImageView.setImageBitmap(photo);
//            }
            mCurrentImagePath=FileUtils.getPath(getActivity(),data.getData());
            Log.d(TAG,"ONACTIVITYRESULT:: URI"+data.getData());
        }
        if (resultCode == Activity.RESULT_OK){

        }else {
            // Get the resultant image's URI.
            final Uri selectedImageUri = (data == null) ? mCapturedImageURI : data.getData();
            Log.d(TAG, "onActivityResult:: selectedImageUri:: " + selectedImageUri);
            // Ensure the image exists.
            if (selectedImageUri != null) {
                Log.d(TAG, "onActivityResult::selectedImageUri " + selectedImageUri);
                // Add image to gallery if this is an image captured with the camera
                //Otherwise no need to re-add to the gallery if the image already exists
                if (requestCode == Constants.ACTION_REQUEST_IMAGE) {
                    final Intent mediaScanIntent =
                            new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(selectedImageUri);
                    getActivity().sendBroadcast(mediaScanIntent);
                }

                mCurrentImagePath = FileUtils.getPath(getActivity(), selectedImageUri);

                // Update client's picture
                if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty()) {

                    mImageView.setImageDrawable(new BitmapDrawable(getResources(),
                            FileUtils.getResizedBitmap(mCurrentImagePath, 512, 512)));
                }
                Log.d(TAG, "onActivityResult:: mCurrentImagePath:: " + mCurrentImagePath);
            }
        }

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment_add_chore to allow an interaction in this fragment_add_chore to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnContactSavedListener {
        // TODO: Update argument type and name
        void onContactSaved();
        void onAddImage();
    }
}
