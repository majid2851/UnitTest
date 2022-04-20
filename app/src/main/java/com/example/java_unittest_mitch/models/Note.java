package com.example.java_unittest_mitch.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "notes")
public class Note implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "timeStamp")
    private String timeStamp;

    public Note(@NonNull String title, String content, String timeStamp) {
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
    }
    @Ignore
    public Note(int id, @NonNull String title, String content, String timeStamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    @Ignore
    public Note(){

    }
    @Ignore
    public Note(Note note){
        id=note.id;
        timeStamp=note.timeStamp;
        title=note.title;
        content=note.content;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        timeStamp = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(timeStamp);
    }

    @Override
    public boolean equals(Object object) {
        if (object==null)
            return false;
        if (object.getClass()!=getClass())
            return false;
        Note note= (Note) object;
        return  note.getId()==getId()
                && note.getTitle().equals(getTitle())
                && note.getContent().equals(getContent());


    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, timeStamp);
    }


}
