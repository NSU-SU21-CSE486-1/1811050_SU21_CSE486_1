package com.leyon.project02;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static DataStorage INSTANCE = null;
    private static List<UserData> listOfUsers = new ArrayList<UserData>();

    private static String filename = "Project2Data.dat";

    private DataStorage() {
        //empty constructor
        //constructor made private because this class will be singleton. use newInstance instead
    }

    public static DataStorage newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataStorage();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public synchronized static void loadData() {
        //file read function
        /*
        File f = new File(filename);

        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fis);
                listOfUsers = (ArrayList<UserData>) in.readObject();

                fis.close();
                in.close();

            } catch (IOException | ClassNotFoundException exception) {
                Log.e("DataStorage", exception.toString());
            }
        }

        if (listOfUsers==null) {
            listOfUsers = new ArrayList<UserData>();
        }*/
    }

    public synchronized static void saveData() {
        //file save function
        /*
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(listOfUsers);

            fos.close();
            out.close();

        } catch (IOException exception) {
            Log.e("DataStorage", exception.toString());
        }*/
    }

    public synchronized static void addUserData(UserData userData) {
        listOfUsers.add(userData);
    }

    public static void deleteUserData() {
        listOfUsers.clear();
        //saveData();
    }

    public static ArrayList<UserData> getUserList() {
        return (ArrayList<UserData>) listOfUsers;
    }

    public static int getNumberOfUserDataStored() {
        return listOfUsers.size();
    }
}
