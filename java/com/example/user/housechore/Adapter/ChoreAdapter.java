package com.example.user.housechore.Adapter;

import android.content.Context;
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
import com.example.user.housechore.R;

import java.util.List;

/**
 * Created by Valentine on 4/16/2015.
 */
public class ChoreAdapter extends ArrayAdapter<Chore>{
    private Context mContext;
    private List<Chore> mChore;
    private static final String TAG="ChoreAdapter";
    private DatabaseHelper db;

    private OnFavoriteAddedListener mListener;

    public ChoreAdapter(Context context, List<Chore> chores)
    {
        super(context, R.layout.row_chore_list, chores);
        context = mContext;
        mChore = chores;

    }


    @Override
    public int getCount() {
        return mChore.size();
    }

    @Override
    public Chore getItem(int position) {
        if (position < mChore.size()) {
            return mChore.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ChoreHolder {
        private Button FavoriteBtn;
//        private TextView Title;
        private ImageView Thumbnail;
        private TextView Name;
        private TextView Complete;
        private TextView RegisterTime;
        private TextView Point;
        private TextView MissionName;
        private CheckBox checkBox;
    }



    public void Add(Chore chore)
    {
        mChore.add(chore);
        this.notifyDataSetChanged();
    }

    public void Update()
    {
        mChore.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ChoreAdapter.ChoreHolder mHolder;
        if (getContext() instanceof OnFavoriteAddedListener) {
            mListener = (OnFavoriteAddedListener) getContext();
        } else {
            throw new RuntimeException(getContext().toString()
                    + " must implement OnPhoneHeightChangeListener");
        }
        final Chore chore= getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.row_chore_list, null);

            mHolder = new ChoreAdapter.ChoreHolder();
//
            mHolder.MissionName=(TextView)view.findViewById(R.id.mission_name);
//            mHolder.Thumbnail = (ImageView) view.findViewById(R.id.recipe_image_thumbnail);
            mHolder.Thumbnail = (ImageView) view.findViewById(R.id.profile_image);
            mHolder.checkBox=(CheckBox)view.findViewById(R.id.checkBox2);



            view.setTag(mHolder);
        }else {
            mHolder = (ChoreHolder)view.getTag();
        }

        //Set the Chore Name
        if (mHolder.MissionName != null) {
            mHolder.MissionName.setText(chore.getChoreName());
        }

        //set the Chore Picture
        if (mHolder.Thumbnail != null){
            mHolder.Thumbnail.setImageDrawable(chore.getThumbnail(getContext()));
        }
        mHolder.checkBox.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
//                Country country = (Country) cb.getTag();
                Toast.makeText(getContext(),
                        "Clicked on Checkbox:"+chore.getChoreName(),
                        Toast.LENGTH_LONG).show();
//                country.setSelected(cb.isChecked());
            }
        });

        return view;
    }

    public interface OnFavoriteAddedListener {
        // TODO: Update argument type and name
        void onFavoriteAdded();

    }


}
