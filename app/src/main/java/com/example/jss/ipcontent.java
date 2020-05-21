package com.example.jss;

import android.provider.BaseColumns;

public class ipcontent
{
    private  ipcontent() {}
    public static final class ipcontentEntry implements BaseColumns
    {
        public static final String TABLE_NAME="ipaddresstable";

        public static final String COLUMN_NAME="address";
        public static final String COLUMN_TIMESTAMP="timestamp";

    }
}
