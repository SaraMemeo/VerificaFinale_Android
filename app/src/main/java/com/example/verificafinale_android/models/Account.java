package com.example.verificafinale_android.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {
    private String username;
    private String password;
    private Persona persona;

    protected Account(Parcel in) {
        username = in.readString();
        password = in.readString();
        persona = in.readParcelable(Persona.class.getClassLoader());
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String username, String password, Persona persona) {
        this.username = username;
        this.password = password;
        this.persona = persona;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeParcelable(persona, i);
    }
}
