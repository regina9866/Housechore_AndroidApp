package com.example.user.housechore.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class Chore {
    private int mId;
//    private String mStar; //즐겨찾기 여부
//    private String mTitle; //레시피 명
//    private String mIngredient;//재료
//    private String mRecipeOrder;//레시피 내용
//
//    private String mRecipeOrderArray[];
//    private String mAmount;//몇인분?
    private String mChoreName;
    private int mPoint;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmPoint() {
        return mPoint;
    }

    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }

    public String getmHowlong() {
        return mHowlong;
    }

    public void setmHowlong(String mHowlong) {
        this.mHowlong = mHowlong;
    }

    public String getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    private String mHowlong;

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getChoreName() {
        return mChoreName;
    }

    public void setChoreName(String choreName) {
        this.mChoreName = choreName;
    }
    private String mDueDate;
    private String mNote;
    private String mImagePath; //이미지





    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public boolean hasImage() {
        return getImagePath() != null && !getImagePath().isEmpty();
    }

    /**
     * Get a thumbnail of this profile's picture, or a default image if the profile doesn't have a
     * Image.
     *
     * @return Thumbnail of the profile.
     */
    public Drawable getThumbnail(Context context) {
        return getScaledImage(context, 128, 128);
    }

    /**
     * Get this profile's picture, or a default image if the profile doesn't have a Image.
     *
     * @return Image of the profile.
     */
    public Drawable getImage(Context context) {
        return getScaledImage(context, 512, 512);
    }

    public Drawable setDefaultImage(Context context){
        return context.getResources().getDrawable(Constants.DEFAULT_IMAGE_RESOURCE);
    }
    /**
     * Get a scaled version of this Recipe's Image, or a default image if the profile doesn't have
     * a Image.
     *
     * @return Image of the profile.
     */
    private Drawable getScaledImage(Context context, int reqWidth, int reqHeight) {

        // If profile has a Image.
        if (hasImage()) {

            // Decode the input stream into a bitmap.
            Bitmap bitmap = FileUtils.getResizedBitmap(getImagePath(), reqWidth, reqHeight);

            // If was successfully created.
            if (bitmap != null) {

                // Return a drawable representation of the bitmap.
                return new BitmapDrawable(context.getResources(), bitmap);
            }
        }

        // Return the default image drawable.
        return context.getResources().getDrawable(Constants.DEFAULT_IMAGE_RESOURCE);
    }


}