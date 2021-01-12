package com.example.user.housechore.Fragment.Chore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.housechore.Data.DatabaseHelper;
import com.example.user.housechore.Model.Chore;
import com.example.user.housechore.TabLayoutActivity;
import com.example.user.housechore.Adapter.ChoreAdapter;
import com.example.user.housechore.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.housechore.Helpers.Constants.ARG_CHORE_ID;

/**
 * A fragment_add_chore representing a list of Items.
 * <p/>
 * Activities containing this fragment_add_chore MUST implement the {@link }
 * interface.
 */
public class ChoreListFragment extends Fragment  {
    private static final String TAG="ChoreList Fragment";
    private List<Chore> mChores;
    private ListView mChoreListView;
    private ChoreAdapter mAdapter;
    private String mListMode;


    private View mRootView;
    private Button mAddFavoriteBtn;
    private Button mAddChoreBtn;
    private DatabaseHelper db;




    public static ChoreListFragment newInstance(String mode, int sectionNumber) {
        ChoreListFragment fragment = new ChoreListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        args.putString(Constants.ARG_LIST_MODE,mode);
        Log.d(TAG,"LIST MODE ="+mode);
        fragment.setArguments(args);
        return fragment;
    }


    public ChoreListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        db = new DatabaseHelper(getActivity());
        Bundle args = getArguments();
        mListMode=args.getString(Constants.ARG_LIST_MODE);
        if(mListMode==null){
            mListMode=Constants.CHORE_LIST;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_add_chore
        mRootView = inflater.inflate(R.layout.fragment_chore_list, container, false);


        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        InitializeViews();
        LoadListData();
    }

    private void LoadListData()
    {
        //First clear the adapter of any Recipe it has
        mAdapter.Update();

        //Get the list of Recipes from the database
        Bundle args = getArguments();
        mListMode=args.getString(Constants.ARG_LIST_MODE);
        Log.d(TAG,"LIST MODE ="+mListMode);

        if(mListMode.equals(Constants.FAVORITE_CHORE_LIST)){
            mChores = db.getAllFavoriteChore();
            Log.d(TAG,"FAVORITE Chore");

        }else{
            mChores = db.getAllChore();
            Log.d(TAG,"ALL Chore");
        }

        if (mChores != null){
            for (Chore chore: mChores){
                mAdapter.add(chore);
            }
        }

    }

    private void InitializeViews() {

        mChoreListView = (ListView) mRootView.findViewById(R.id.chore_list);
        mChores = new ArrayList<Chore>();
        mAdapter = new ChoreAdapter(getActivity(), mChores);
//        mAddFavoriteBtn = (Button) mRootView.findViewById(R.id.btn_favorite_at_List);
        if(mListMode.equals(Constants.CHORE_LIST)){
            mAddChoreBtn = (Button) mRootView.findViewById(R.id.btn_AddChore);
            mAddChoreBtn.setVisibility(View.VISIBLE);
            mAddChoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TabLayoutActivity myActivity = (TabLayoutActivity)getActivity();
                    myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreAddFragment,3,0);
                }
            });
        }
        mAddChoreBtn = (Button) mRootView.findViewById(R.id.btn_AddChore);
        mAddChoreBtn.setVisibility(View.VISIBLE);
        mAddChoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayoutActivity myActivity = (TabLayoutActivity)getActivity();
                myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreAddFragment,3,0);
            }
        });

        mChoreListView.setAdapter(mAdapter);
        TextView emptyText = (TextView) mRootView.findViewById(R.id.chore_list_empty);
        if(mListMode.equals(Constants.FAVORITE_CHORE_LIST)) {
            emptyText.setText(R.string.no_favorite_chore);
        }else{
            emptyText.setText(R.string.no_chore);
        }
        mChoreListView.setEmptyView(emptyText);





        mChoreListView.setItemsCanFocus(true);
        mChoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chore tempChore = mChores.get(position);
                TabLayoutActivity myActivity = (TabLayoutActivity) getActivity();
                if(mListMode.equals(Constants.FAVORITE_CHORE_LIST)){
                    myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreDetailsFragment, 3, tempChore.getId());
                }else{
                    myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreDetailsFragment, 3, tempChore.getId());
                }
                //Get the selected client
            }
        });
        mChoreListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int positionToRemove = position;

                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete "+ mChores.get(positionToRemove).getChoreName());
                adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.removeChore(mChores.get(positionToRemove));
                        mAdapter.notifyDataSetChanged();//由ъ뒪???곗씠??諛붾먭쾬 notify
                        LoadListData();//諛붾?由ъ뒪?몃뜲?댄꽣 ?ㅼ떆 濡쒕뱶
                    }});

                adb.setNegativeButton("Cancel",null);
                adb.show();
                return true;
            }
        });

//        mAddBtn = (FloatingActionButton) mRootView.findViewById(R.id.fabtn_addBowl);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


//        ((TabLayoutActivity) activity).onSectionAttached(
//                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    @Override
//    public void onFavoriteAdded() {
////        onResume();
//    }

    public interface OnChoreSelectedListener {
        void onChoreSelected();
    }

}
