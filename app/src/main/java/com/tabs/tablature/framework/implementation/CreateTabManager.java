package com.tabs.tablature.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tabs.tablature.framework.base.NoteBase;

import java.util.ArrayList;

public class CreateTabManager {

    public ArrayList<NoteBase> getNotes() {
        return notes;
    }

    ArrayList<NoteBase> notes;

    public void instantiateObjects(TablatureFileIO tablatureFileIO) {
        notes = new ArrayList<>();
        try {
            Bitmap crochet = BitmapFactory.decodeStream(tablatureFileIO.readAsset("crochet.png"));
            notes.add(new NoteBase(crochet, 0, 0));
            notes.add(new NoteBase(crochet, 0, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
