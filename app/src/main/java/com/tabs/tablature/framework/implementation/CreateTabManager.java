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

    public void instantiateObjects(TablatureFileIO tablatureFileIO) {
        staves = new ArrayList<>();
        notes = new ArrayList<>();
        try {
            Bitmap crochet = BitmapFactory.decodeStream(tablatureFileIO.readAsset("Notes/Crotchets/" + "Crotchet.png"));
            notes.add(new Note(crochet, 0, 0));
            notes.add(new Note(crochet, 0, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
