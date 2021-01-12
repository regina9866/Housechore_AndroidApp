package com.example.user.housechore.Fragment.Chore;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.housechore.Data.DatabaseHelper;
import com.example.user.housechore.Model.Chore;
import com.example.user.housechore.R;
import com.example.user.housechore.TabLayoutActivity;

import static com.example.user.housechore.Helpers.Constants.ARG_CHORE_ID;
import static com.example.user.housechore.Helpers.Constants.ARG_LIST_MODE;
import static com.example.user.housechore.Helpers.Constants.ARG_SECTION_NUMBER;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMeasure2StartedListener} interface
 * to handle interaction events.
 * Use the {@link ChoreDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoreDetailsFragment extends Fragment implements View.OnClickListener {
    private static final String TABLE_NAME = "mytable";
    private static final String TAG ="Chore Details Fragment";
    private  static  long choreId;
    private Chore mChore;
    private View mRootView;
    private static DatabaseHelper db;

    //ImageProperties
    private String mCurrentImagePath = null;
    private Uri mCapturedImageURI = null;

    private ImageView mPhotoImageView;

    private TextView   mTitleTextView,
            mIngredientTextView,
            mRecipeOrderTextView,
            mAmountTextView;

    private Button  mFavoriteBtn,
            mEditBtn,
            mStartMeasure2Btn,
            mCancelBtn;
    private String mListMode;

    private LinearLayout mRecipeOrderLayout;
    String recipeOrderArray[];


    private OnMeasure2StartedListener mListener;
    public ChoreDetailsFragment() {
        // Required empty public constructor

    }

    static  Bundle b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity());
        mListMode=getArguments().getString(Constants.ARG_LIST_MODE);

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
        mRootView = inflater.inflate(R.layout.fragment_chore_details,null);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitializeViews();

    }

    @Override
    public void onResume() {
        super.onResume();
        GetPassedInChore();
        PopulateFields();

    }
    public static ChoreDetailsFragment newInstance(String mode, int sectionNumber, long choreId) {
        ChoreDetailsFragment fragment = new ChoreDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        args.putString(Constants.ARG_LIST_MODE,mode);
        if (choreId > 0){
            args.putLong(ARG_CHORE_ID, choreId );
        }
        fragment.setArguments(args);
        return fragment;
    }

    private void GetPassedInChore(){

        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_CHORE_ID)) {
            long choreId = args.getLong(ARG_CHORE_ID, 0);
//            b.putLong("id",args.getLong(ARG_CHORE_ID));
            if (choreId > 0) {
                mChore = db.getChoreById(choreId);
                Log.d("ChoreDetailFrag","title"+mChore.getChoreName());
                Log.d("ChoreDetailFrag","getRecipeID"+mChore.getId());
                Log.d(TAG, "GetPassedInChore: "+choreId);
                args.putLong("id",choreId);
            }

        }
//        new ChoreDetailsFragment(ARG_LIST_MODE,ARG_SECTION_NUMBER,choreId);


    }



    private void PopulateFields() {
//        TabLayoutActivity myActivity = (TabLayoutActivity) getActivity();
        mTitleTextView.setText(mChore.getChoreName());
        Log.d("텍스트뷰성공"," ");
        // Update profile's Image
        if (mCurrentImagePath != null && !mCurrentImagePath.isEmpty()) {
            mPhotoImageView.setImageDrawable(new BitmapDrawable(getResources(),
                    FileUtils.getResizedBitmap(mCurrentImagePath, 512, 512)));
            Log.d("CurrentImagePath","getimage"+mCurrentImagePath);

        } else {
//            mPhotoImageView.setImageDrawable(mChore.getThumbnail(getActivity());
//            mPhotoImageView.setImageBitmap(BitmapFactory.decodeFile(mChore.getImagePath()));
            mPhotoImageView.setImageDrawable(mChore.getImage(getActivity()));
            Log.d("CurrentImagePath","getimage"+mChore.getImagePath());
            Log.d("CurrentImagePath","getimage"+mChore.getmImagePath());
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMeasure2StartedListener) {
            mListener = (OnMeasure2StartedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPhoneHeightChangeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private void InitializeViews() {

        mPhotoImageView = (ImageView)mRootView.findViewById(R.id.ChoreImageView);
//
        mTitleTextView = (TextView)mRootView.findViewById(R.id.tv_ChoreName);
        mStartMeasure2Btn = (Button)mRootView.findViewById(R.id.btn_start_measure2);
        mStartMeasure2Btn.setOnClickListener(this);
        mCancelBtn = (Button)mRootView.findViewById(R.id.btn_Cancel);
        mCancelBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

                if(v==mStartMeasure2Btn){
                    mStartMeasure2Btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TabLayoutActivity myActivity = (TabLayoutActivity)getActivity();
                            myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreUpdateFragment,3,getArguments().getLong(ARG_CHORE_ID));
                        }
                    });

                }
                else if(v==mCancelBtn){
                TabLayoutActivity mActivity = (TabLayoutActivity) getActivity();
                mActivity.onBackPressed();
//            if(mListMode.equals(Constants.FAVORITE_RECIPE_LIST)){
//                mActivity.ReplaceFragment(Enums.FragmentEnums.FavoriteListFragment,3, mRecipe.getId());
//                Log.d("Detail","Cancel111111111111111");
//            }else {
//                mActivity.ReplaceFragment(Enums.FragmentEnums.ChoreListFragment, 3, mRecipe.getId());
//                Log.d("Detail","Cancel22222222222222222");
//            }
                }
                }

public interface OnMeasure2StartedListener {
    // TODO: Update argument type and name
    void onMeasure2StartedAtRecipeDetail(long itemId);
    void onMeasure2StartedAtFavoriteDetail(long itemId);

}
}
