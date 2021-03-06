package com.example.user.housechore.Fragment.Chore;


import android.app.Activity;
import android.content.ComponentName;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.user.housechore.Model.Chore;
import com.example.user.housechore.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChoreAddFragment extends Fragment {
    //?섏젙 諛?ADD
    private static final String TAG="ChoreAddFragment";
    private boolean InEditMode;
    private Chore mChore;
    private View mRootView;
    private DatabaseHelper db;


    //Image properties
    private String mCurrentImagePath = null;
    private Uri mCapturedImageURI = null;


    private EditText mTitleEditText,
            mIngredientEditText,
            mRecipeOrderEditText,
            mAmountEditText;

    private LinearLayout mRecipeOrderLayout;

    private EditText mAddChoreOrderEditText;
    private int mRecipeOrderNum;
    private Button mSaveBtn, mImageSelectBtn, mAddRecipeOrderBtn, mAmountMinus,mAmountPlus;
    //===========================================================================================
    private EditText mChoreNameEditText,
            mAssignEditText,
            mNoteEditText;
    private Button mHowlongBtn, mRepeatBtn,mPointBtn;

    private ImageView mImageView;

    private int mStar=0;

    OnChoreSavedListener mCallback;

    public static ChoreAddFragment newInstance(int sectionNumber) {
        ChoreAddFragment fragment = new ChoreAddFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    public ChoreAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity());
        mRecipeOrderNum=1;

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
        mRootView = inflater.inflate(R.layout.fragment_add_chore , container, false);
        InitializeViews();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mChore = new Chore();
    }



    private void InitializeViews() {

        mChoreNameEditText= (EditText) mRootView.findViewById(R.id.edit_task_name);


        mImageView = (ImageView) mRootView.findViewById(R.id.imageView1);

        mImageSelectBtn = (Button) mRootView.findViewById(R.id.btn_select_gallery);
        mImageSelectBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        mSaveBtn = (Button) mRootView.findViewById(R.id.btn_update);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveChore();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnChoreSavedListener) activity;
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

    private void SaveChore(){
        mChore.setChoreName(mChoreNameEditText.getText().toString());
//
//        mChore.setAmount(mAmountEditText.getText().toString());
//        mChore.setStar("0");

        //Check to see if there is valid image path temporarily in memory
        //Then save that image path to the database and that becomes the profile
        //Image for this user.
        if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty())
        {
            mChore.setImagePath(mCurrentImagePath);
        }

        long result = db.addChore(mChore);
        if (result == -1 ){
            Toast.makeText(getActivity(), "Unable to add Chore: " + mChore.getChoreName(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "Chore: " + mChore.getChoreName()+" Saved", Toast.LENGTH_LONG).show();

//            mTitleEditText.setText("");
            mChoreNameEditText.setText("");
//            mIngredientEditText.setText("");
//            mRecipeOrderEditText.setText("");
//            mAmountEditText.setText("0");
            mImageView.setImageDrawable(mChore.setDefaultImage(getActivity()));

        }

        mChoreNameEditText.setError(null);
        mCallback.onChoreSaved();
        getActivity().onBackPressed();
    }
    private void chooseImage(){

//        mCallback.onAddImage();
//
//        //We need the title to to save the image file
//        if (mTitleEditText.getText() != null && !mTitleEditText.getText().toString().isEmpty()) {
        if (mChoreNameEditText.getText() != null && !mChoreNameEditText.getText().toString().isEmpty()) {
            // Determine Uri of camera image to save.
            final File rootDir = new File(Constants.PICTURE_DIRECTORY);

            //noinspection ResultOfMethodCallIgnored
            rootDir.mkdirs();

            // Create the temporary file and get it's URI.

            //Get the  title
            String choreName = mChoreNameEditText.getText().toString();

            //Remove all white space in the title
            choreName.replaceAll("\\s+", "");

            //Use the title to create the file name of the image that will be captured
            File file = new File(rootDir, FileUtils.generateImageName(choreName));
            mCapturedImageURI = Uri.fromFile(file);

            // Initialize a list to hold any camera application intents.
            final List<Intent> cameraIntents = new ArrayList<Intent>();

            // Get the default camera capture intent.
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Get the package manager.
            final PackageManager packageManager = getActivity().getPackageManager();

            // Ensure the package manager exists.
            if (packageManager != null) {

                // Get all available image capture app activities.
                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);

                // Create camera intents for all image capture app activities.
                for(ResolveInfo res : listCam) {

                    // Ensure the activity info exists.
                    if (res.activityInfo != null) {

                        // Get the activity's package name.
                        final String packageName = res.activityInfo.packageName;

                        // Create a new camera intent based on android's default capture intent.
                        final Intent intent = new Intent(captureIntent);

                        // Set the intent data for the current image capture app.
                        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                        intent.setPackage(packageName);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

                        // Add the intent to available camera intents.
                        cameraIntents.add(intent);
                    }
                }
            }
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, Constants.SELECT_IMAGE);

//            // Create an intent to get pictures from the filesystem.
//            final Intent galleryIntent = new Intent();
//            galleryIntent.setType("image/*");
//            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//            galleryIntent.putExtra("crop", "true");
//            galleryIntent.putExtra("aspectX", 0);
//            galleryIntent.putExtra("aspectY", 0);
//            galleryIntent.putExtra("outputX", 200);
//            galleryIntent.putExtra("outputY", 150);
//            galleryIntent.putExtra("uri",mCapturedImageURI);
//            try{
//                 galleryIntent.putExtra("return-data", true);
//                startActivityForResult(Intent.createChooser(galleryIntent,"Select Source"),Constants.SELECT_IMAGE);
//            }
//            catch(ActivityNotFoundException e){
//
//            }
            // Chooser of filesystem options.
            //final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

            // Add the camera options.
            // chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
            //         cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

            // Start activity to choose or take a picture.
            //startActivityForResult(chooserIntent, Constants.SELECT_IMAGE);


        }else {
            mChoreNameEditText.setError("Please enter Chore Name");
        }
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

    public interface OnChoreSavedListener {
        // TODO: Update argument type and name
        void onChoreSaved();
        void onAddImage();
    }
}
