package com.tabs.tablature.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tabs.tablature.framework.InputOutput.TablatureFileIO;
import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.Stave;

import java.util.ArrayList;

public class CreateTabManager {

    public ArrayList<Stave> getStaves() { return staves; }
    public ArrayList<Note> getNotes() {
        return notes;
    }

    ArrayList<Stave> staves;
    ArrayList<Note> notes;

    public void instantiateObjects() {
        staves = new ArrayList<>();
        notes = new ArrayList<>();
        try {
            Stave stave = new Stave("Staves/Stave Large.png", 0, 0);
            stave.setScale(0.5, 0.5);
            staves.add(stave);
            notes.add(new Note("Notes/Crotchets/Crotchet.png", 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
