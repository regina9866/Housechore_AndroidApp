package com.example.user.housechore.Fragment.Family;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.example.user.housechore.Adapter.ContactAdapter;
import com.example.user.housechore.Data.DatabaseHelper;
import com.example.user.housechore.Model.Contact;
import com.example.user.housechore.TabLayoutActivity;
import com.example.user.housechore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment_add_chore representing a list of Items.
 * <p/>
 * Activities containing this fragment_add_chore MUST implement the {@link }
 * interface.
 */
public class ContactListFragment extends Fragment  {
    private static final String TAG="ContactList Fragment";
//    private List<Chore> mChores;
    private List<Contact> mContact;
    private ListView mContactListView;
    private ContactAdapter mAdapter;
    private String mListMode;


    private View mRootView;
    private Button mAddFavoriteBtn;
    private Button mAddContactBtn;
    private DatabaseHelper db;
    public SharedPreferences sh;




    public static ContactListFragment newInstance(String mode, int sectionNumber) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        args.putString(Constants.ARG_LIST_MODE,mode);
        Log.d(TAG,"LIST MODE ="+mode);
        fragment.setArguments(args);
        return fragment;
    }


    public ContactListFragment() {
        // Required empty public constructor
    }
//    public List<Contact> getAllContact() {
//        //Initialize an empty list of Customers
//        List<Contact> contactList = new ArrayList<Contact>();
//
//        //Command to select all Customers
//        String selectQuery = "SELECT * FROM " +  TABLE_CONTACTS;
//
//        //lock database for reading
//        synchronized (databaseLock) {
//            //Get a readable database
//            SQLiteDatabase database = getReadableDatabase();
//
//            //Make sure database is not empty
//            if (database != null) {
//
//                //Get a cursor for all Customers in the database
//                Cursor cursor = database.rawQuery(selectQuery, null);
//                if (cursor.moveToFirst()) {
//                    while (!cursor.isAfterLast()) {
//                        Contact contact = getContact(cursor);
//                        contactList.add(contact);
//                        cursor.moveToNext();
//                    }
//                }
//                //Close the database connection
//                database.close();
//            }
//            //Return the list of customers
//            return contactList;
//        }
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        db = new DatabaseHelper(getActivity());

//        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS student(eid VARCHAR,ename VARCHAR,esdate VARCHAR);");


        try {
            sh.getString("family",null);
            Log.d(TAG, "onCreate: "+sh);
        }catch (Exception e){

        }

        Bundle args = getArguments();
        mListMode=args.getString(Constants.ARG_LIST_MODE);
        if(mListMode==null){
            mListMode=Constants.CONTACT_LIST;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_add_chore
        mRootView = inflater.inflate(R.layout.fragment_contact_list, container, false);


        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseHelper(getActivity());
        try {
            sh.getString("family",null);
            Log.d(TAG, "onCreate: "+sh);
        }catch (Exception e){

        }
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

        }else{

//            Log.d(TAG,"ALL Chore");
        }
        mContact = db.getAllContact();
//        getAllContact();
        if (mContact != null){
            for (Contact contact: mContact){
                mAdapter.add(contact);
            }
        }

    }


    private void InitializeViews() {

        mContactListView = (ListView) mRootView.findViewById(R.id.contact_list);
        mContact = new ArrayList<Contact>();
        mAdapter = new ContactAdapter(getActivity(), mContact);
//        mAddFavoriteBtn = (Button) mRootView.findViewById(R.id.btn_favorite_at_List);
        if(mListMode.equals(Constants.CONTACT_LIST)){

            mAddContactBtn = (Button) mRootView.findViewById(R.id.btn_AddContact);
            mAddContactBtn.setVisibility(View.VISIBLE);
            mAddContactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TabLayoutActivity myActivity = (TabLayoutActivity)getActivity();
                    myActivity.ReplaceFragment(Enums.FragmentEnums.ContactAddFragment,3,0);
                }
            });
        }

        mAddContactBtn = (Button) mRootView.findViewById(R.id.btn_AddContact);
        mAddContactBtn.setVisibility(View.VISIBLE);
        mAddContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayoutActivity myActivity = (TabLayoutActivity)getActivity();
                myActivity.ReplaceFragment(Enums.FragmentEnums.ContactAddFragment,3,0);
            }
        });

        mContactListView.setAdapter(mAdapter);
        TextView emptyText = (TextView) mRootView.findViewById(R.id.contact_list_empty);
        if(mListMode.equals(Constants.FAVORITE_CHORE_LIST)) {
//            emptyText.setText(R.string.no_favorite_chore);
        }else{
            emptyText.setText("가족을 등록해 주세요");
        }
        mContactListView.setEmptyView(emptyText);





        mContactListView.setItemsCanFocus(true);
        mContactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact tempContact = mContact.get(position);
                TabLayoutActivity myActivity = (TabLayoutActivity) getActivity();
                if(mListMode.equals(Constants.FAVORITE_CHORE_LIST)){
//                    myActivity.ReplaceFragment(Enums.FragmentEnums.ChoreDetailsFragment, 3, tempContact.getId());
                }else{
                    myActivity.ReplaceFragment(Enums.FragmentEnums.ContactDetailsFragment, 3, tempContact.getId());
                }
                //Get the selected client
            }
        });
        mContactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int positionToRemove = position;

                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
//                adb.setMessage("Are you sure you want to delete "+ mContact.get(positionToRemove).getChoreName());

                adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        db.removeC(mContact.get(positionToRemove));

                        mAdapter.notifyDataSetChanged();//由ъ뒪???곗씠??諛붾먭쾬 notify
//                        LoadListData();//諛붾?由ъ뒪?몃뜲?댄꽣 ?ㅼ떆 濡쒕뱶
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

//    public interface OnContacteSelectedListener {
//        void onContactSelected();
//    }

}