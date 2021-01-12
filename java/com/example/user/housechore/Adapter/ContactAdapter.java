package com.example.user.housechore.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.housechore.Data.DatabaseHelper;
import com.example.user.housechore.Model.Chore;
import com.example.user.housechore.Model.Contact;
import com.example.user.housechore.R;

import java.util.List;

/**
 * Created by Valentine on 4/16/2015.
 */
public class ContactAdapter extends ArrayAdapter<Contact>{
    private Context mContext;
    private List<Contact> mContact;
    private static final String TAG="ContactAdapter";
//    private DatabaseHelper db;
    public SharedPreferences sharedPreferences;
    private OnFavoriteAddedListener mListener;

    public ContactAdapter(Context context, List<Contact> contacts)
    {
//        super(context, R.layout.row_chore_list, chores);
        super(context, R.layout.row_contact_list, contacts);
        context = mContext;
        mContact = contacts;

    }


    @Override
    public int getCount() {
        return mContact.size();
    }

    @Override
    public Contact getItem(int position) {
        if (position < mContact.size()) {
            return mContact.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ContactHolder {
        private Button FavoriteBtn;
//        private TextView Title;
        private ImageView Thumbnail;
        private TextView Name;
        private TextView Complete;
        private TextView RegisterTime;
        private TextView Point;
        private TextView MissionName;
        private CheckBox checkBox;
        public TextView PhoneNum;
    }



    public void Add(Contact contact)
    {
        mContact.add(contact);
        this.notifyDataSetChanged();
    }

    public void Update()
    {
        mContact.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ContactAdapter.ContactHolder mHolder;
        if (getContext() instanceof OnFavoriteAddedListener) {
            mListener = (OnFavoriteAddedListener) getContext();
        } else {
            throw new RuntimeException(getContext().toString()
                    + " must implement OnPhoneHeightChangeListener");
        }
        final Contact contact= getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.row_contact_list, null);
            mHolder = new ContactAdapter.ContactHolder();
            mHolder.MissionName=(TextView)view.findViewById(R.id.famname);
            mHolder.PhoneNum=(TextView)view.findViewById(R.id.phonenum);
//            mHolder.Thumbnail = (ImageView) view.findViewById(R.id.recipe_image_thumbnail);
//            mHolder.Thumbnail = (ImageView) view.findViewById(R.id.profile_image);
//            mHolder.checkBox=(CheckBox)view.findViewById(R.id.checkBox2);

            view.setTag(mHolder);
        }else {
            mHolder = (ContactHolder) view.getTag();
        }

        //Set the Chore Name
        if (mHolder.MissionName != null) {
            mHolder.MissionName.setText(contact.getName());
        }//Set the Chore Name
        if (mHolder.PhoneNum != null) {
            mHolder.PhoneNum.setText(contact.getPhoneNumber());
        }
//
//        //set the Chore Picture
//        if (mHolder.Thumbnail != null){
//            mHolder.Thumbnail.setImageDrawable(chore.getThumbnail(getContext()));
//        }
//        mHolder.checkBox.setOnClickListener( new View.OnClickListener() {
//            public void onClick(View v) {
////                Country country = (Country) cb.getTag();
//                Toast.makeText(getContext(),
//                        "Clicked on Checkbox:"+chore.getChoreName(),
//                        Toast.LENGTH_LONG).show();
////                country.setSelected(cb.isChecked());
//            }
//        });

        return view;
    }

    public interface OnFavoriteAddedListener {
        // TODO: Update argument type and name
        void onFavoriteAdded();

    }


}
