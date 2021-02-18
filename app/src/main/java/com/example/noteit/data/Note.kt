package com.example.noteit.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_table")
class Note (@ColumnInfo(name = "text") val text : String,
            @ColumnInfo(name = "title") val title : String,
            @ColumnInfo(name = "time") val time: String,
            @ColumnInfo(name = "image") val image : ByteArray):Parcelable {
    @PrimaryKey(autoGenerate = true) var id = 0
}