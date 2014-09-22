package com.example.hariskljajic.picassooptimizedadapter.Data.Contracts;

import android.provider.BaseColumns;

/**
 * Created by Haris Kljajic on 2014-09-17.
 */
public class ContractGallery {

    public ContractGallery(){
    }

    public static abstract class Gallery implements BaseColumns{
        public final static String TABLE_NAME = "Gallery";
        public final static String COLUMN_NAME_URL = "url";
        public final static String COLUMN_NAME_NAME = "name";
        public final static String COLUMN_NAME_DESCRIPTION = "description";
    }

}
