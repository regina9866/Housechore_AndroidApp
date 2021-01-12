package com.example.user.housechore.Helpers;

import android.os.Environment;

import com.example.user.housechore.R;

import java.io.File;

/**
 * Created by Valentine on 4/10/2015.
 */
public class Constants {

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_RECIPE_ID = "recipe_id";
    public static final String ARG_BOWL_ID="bowl_id";
    public static final String ARG_CHORE_ID="chore_id";
    public static final String ARG_MEASURE_MODE="measure_mode";
    public static final String ARG_LIST_MODE="list_mode";
    public static final String ARG_PHONE_HEIGHT="phone_height";

    public static final String ARG_BOWL_HEIGHT="bowl_height";
    public static final String ARG_BOWL_WIDTH="bowl_width";
    public static final String ARG_BOWL_SOBEL_ANGLE="bowl_sobel_angle";
    public static final String ARG_BOWL_IMAGE_URI="bowl_image_uri";
    public static final String ARG_BOWL_EDGE = "bowl_edge";

    public static final String ARG_BOWL_ROWS_LEN = "bowl_rows_length";
    public static final String ARG_BOWL_COLS_LEN = "bowl_cols_length";

    public static final String ARG_BOWL_EDGE_LEFT_TOP = "left_top";
    public static final String ARG_BOWL_EDGE_LEFT_DOWN = "left_down";
    public static final String ARG_BOWL_EDGE_RIGHT_TOP = "right_top";
    public static final String ARG_BOWL_EDGE_RIGHT_DOWN = "rigt_down";

    public static  final int SIMPLE_MEASURE=1;  //?⑥닚 怨꾨웾
    public static  final int RECIPE_MEASURE=2;  //?덉떆??怨꾨웾

    public static final String RECIPLE_LIST="recipe_list";
    public static final String CHORE_LIST="chore_list";
    public static final String FAVORITE_RECIPE_LIST ="favorite_recipe_list";
    public static final String FAVORITE_CHORE_LIST ="favorite_chore_list";

    public static final String BOWL_LIST="bowl_list";
    public static final String BOWL_SELECT_LIST="bowl_select_list";

    /* Bowl DB Column */
    public static final String COLUMN_BOWL_ID = "_id";
    public static final String COLUMN_BOWL_NAME = "name";
    public static final String COLUMN_BOWL_TYPE = "type";

    public static final String COLUMN_BOWL_ROWS = "rowLength";
    public static final String COLUMN_BOWL_COLS = "colsLength";

    public static final String COLUMN_BOWL_EDGE_LEFT_X = "leftX";
    public static final String COLUMN_BOWL_EDGE_LEFT_Y = "leftY";
    public static final String COLUMN_BOWL_EDGE_RIGHT_X = "rightX";
    public static final String COLUMN_BOWL_EDGE_RIGHT_Y = "rigtY";

    public static final String COLUMN_BOWL_HEIGHT = "height";
    public static final String COLUMN_BOWL_WIDTH = "width";
    public static final String COLUMN_BOWL_VOLUME = "volume";
    public static final String COLUMN_BOWL_CANNY_IMAGE_PATH = "cannyImagePath";

    public static final String BOWL_TYPE_CYLINDER ="cylinder";  // 洹몃쫯 ??? ?먭린?ν삎
    public static final String BOWL_TYPE_CUP ="cup";             // 洹몃쫯 ??? ?꾧? 踰뚯뼱吏?而듯삎


    /* Recipe DB Column */
    public static final String COLUMN_RECIPE_ID = "_id";
    public static final String COLUMN_STAR = "star";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_INGREDIENT = "ingredient";
    public static final String COLUMN_RECIPE_ORDER = "recipeOrder";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_RECIPE_IMAGE_PATH = "imagePath";

    public static final String FAVORITE_RECIPE="favorite";
    public static final String NON_FAVORITE_RECIPE="non_favorite";

    public static final String SPLIT_DELIMINATOR="<split/>";

    /* Chore DB Column */
    public static final String COLUMN_CHORE_ID = "_id";
    public static final String COLUMN_POINT = "point";
    public static final String COLUMN_CHORE_NAME = "name";
    public static final String COLUMN_HOWLONG = "howlong";
    public static final String COLUMN_DUEDATE = "dueDate";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_IMAGE_PATH = "imagePath";

    public static final String FAVORITE_CHORE="favorite";
    public static final String NON_FAVORITE_CHORE="non_favorite";




    public static final String KEY_IMAGE_URI = "image_uri";

    public static final String KEY_IMAGE_PATH = "key_image_path";
    public static final String CONTACT_LIST ="contact_list" ;
    public static final String COLUMN_CONTACT_ID = "contact_id";
    public static final String COLUMN_CONTACT_NAME ="contact_name" ;
    public static final String COLUMN_PHONENUMBER = "phonenumber";


    public static int ACTION_REQUEST_IMAGE = 1000;
    public static int SELECT_IMAGE = 1001;
    public static final String TAKE_PHOTO = "Take Photo";
    public static final String CHOOSE_FROM_GALLERY = "Choose from gallery";
    public static final String CANCEL= "Cancel";

    public static final int DEFAULT_IMAGE_RESOURCE = R.drawable.default_customer_picture;
    public static final String PICTURE_DIRECTORY = Environment.getExternalStorageDirectory()
            + File.separator + "DCIM" + File.separator + "" +
            "" + File.separator;

}
