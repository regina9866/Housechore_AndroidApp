package com.example.user.housechore;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.user.housechore.Adapter.ChoreAdapter;
import com.example.user.housechore.Adapter.TabPagerAdapter;

import com.example.user.housechore.Fragment.Chore.ChoreAddFragment;
import com.example.user.housechore.Fragment.Chore.ChoreDetailsFragment;
import com.example.user.housechore.Fragment.Chore.ChoreListFragment;
import com.example.user.housechore.Fragment.Chore.ChoreUpdateFragment;

import com.example.user.housechore.Fragment.SettingFragment;

public class TabLayoutActivity extends AppCompatActivity implements
        ChoreAddFragment.OnChoreSavedListener,
        ChoreDetailsFragment.OnMeasure2StartedListener,
        ChoreListFragment.OnChoreSelectedListener,
        ChoreAdapter.OnFavoriteAddedListener,
        ContactAddFragment.OnChoreSavedListener,
        Camera.PictureCallback
{


    private static final int REQUEST_CAMERA=0;


    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    private int mPhoneHeight=0;
    private SharedPreferences mSetting;
    private SharedPreferences mFamily;

    private Menu menu;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        startActivity(new Intent(this, SplashActivity.class));

        mSetting=getSharedPreferences("setting",0);//폰이 꺼져도 저장될 정보가 있는 xml 파일 setting
        mFamily=getSharedPreferences("family",0);//폰이 꺼져도 저장될 정보가 있는 xml 파일 setting

        


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout =
                (TabLayout) findViewById(R.id.tab_layout);

//        tabLayout.addTab(tabLayout.newTab().setIcon(
//                R.drawable.ic_camera));
        tabLayout.addTab(tabLayout.newTab().setText("Chore\nList"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.addTab(tabLayout.newTab().setIcon(
                R.drawable.ic_setting));//tab4


//        tabLayout.addTab(tabLayout.newTab().setIcon(
//                R.drawable.ic_recipe_list));
//        tabLayout.addTab(tabLayout.newTab().setIcon(
//                R.drawable.ic_measure1));
//        tabLayout.addTab(tabLayout.newTab().setIcon(
//                R.drawable.ic_favorite));
//        tabLayout.addTab(tabLayout.newTab().setIcon(
//                R.drawable.ic_setting));

       mViewPager =
                (ViewPager) findViewById(R.id.pager);
       mAdapter = new TabPagerAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
       mViewPager.setAdapter(mAdapter);
       mViewPager.setOffscreenPageLimit(4);

       mViewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
       tabLayout.setOnTabSelectedListener(new
               TabLayout.OnTabSelectedListener() {
                   @Override
                   public void onTabSelected(TabLayout.Tab tab) {
                       mViewPager.setCurrentItem(tab.getPosition());
                   }

                   @Override
                   public void onTabUnselected(TabLayout.Tab tab) {

                   }

                   @Override
                   public void onTabReselected(TabLayout.Tab tab) {

                   }
               });

        requestCameraPermission();
    }
    private void requestCameraPermission(){
        // Camera permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA);

    }



    public void ReplaceFragment(Enums.FragmentEnums frag, int sectionNumber, long id){

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (frag){
//
            //Chores
            //List는 더이상 돌아갈 곳이 없으므로 backstack 추가하지 않음.
            case ChoreListFragment:
                ChoreListFragment choreListFrag =  ChoreListFragment.newInstance(Constants.CHORE_LIST,sectionNumber);
                fragmentManager.beginTransaction().replace(R.id.tab1_fragment, choreListFrag)
                        .commitAllowingStateLoss();
                break;
            case ChoreAddFragment:
                ChoreAddFragment addChoreFrag = ChoreAddFragment.newInstance(sectionNumber);//id::bowl id
                fragmentManager.beginTransaction().replace(R.id.tab1_fragment, addChoreFrag)
                        .addToBackStack(null).commit();
                break;
            case ChoreDetailsFragment:
                ChoreDetailsFragment choreFrag =  ChoreDetailsFragment.newInstance(Constants.CHORE_LIST,sectionNumber, id);//id::recipe id
                fragmentManager.beginTransaction().replace(R.id.tab1_fragment, choreFrag)
                        .addToBackStack(null).commit();
                break;
            case ChoreUpdateFragment:
                ChoreUpdateFragment choreUpdateFrag =  ChoreUpdateFragment.newInstance(Constants.CHORE_LIST,sectionNumber, id);//id::recipe id
                fragmentManager.beginTransaction().replace(R.id.tab1_fragment, choreUpdateFrag)
                        .addToBackStack(null).commit();
                break;

            case ContactListFragment:
                ContactListFragment contactListFrag = ContactListFragment.newInstance("CONTACT",sectionNumber);
                fragmentManager.beginTransaction().replace(R.id.tab2_fragment, contactListFrag)
                        .commitAllowingStateLoss();
                break;
            case ContactAddFragment:
                ContactAddFragment contactAddFragment =  ContactAddFragment.newInstance(sectionNumber);
                fragmentManager.beginTransaction().replace(R.id.tab2_fragment, contactAddFragment)
                        .addToBackStack(null).commit();
                break;

            case SettingFragment:
                SettingFragment settingFrag = SettingFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.tab4_fragment,settingFrag)
                        .commit();
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode==RESULT_OK)&(requestCode==1000)){

        }
    }


    @Override
    public void onChoreSaved() {

    }

//    @Override
//    public void onChoreUpdated() {
//
//    }

    @Override
    public void onAddImage() {
        getSupportFragmentManager().findFragmentById(R.id.tab1_fragment).onDestroy();
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }


    @Override
    public void onFavoriteAdded() {
        //Favorite 버튼 눌리면 실시간으로 페이지 Resume
        getSupportFragmentManager().findFragmentById(R.id.tab4_fragment).onResume();
        getSupportFragmentManager().findFragmentById(R.id.tab2_fragment).onResume();
    }


    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {

    }

    @Override
    public void onChoreSelected() {

    }

    @Override
    public void onMeasure2StartedAtRecipeDetail(long itemId) {

    }

    @Override
    public void onMeasure2StartedAtFavoriteDetail(long itemId) {

    }
}