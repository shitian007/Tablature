package com.tabs.tablature.framework.implementation;

import com.tabs.tablature.constants.DimenConstants;
import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.SpriteBase;
import com.tabs.tablature.framework.base.Stave;

import java.util.ArrayList;

public class CreateTabManager {


    public ArrayList<Stave> getStaves() { return staves; }
    public ArrayList<Note> getNotes() {
        return notes;
    }

    ArrayList<Stave> staves;
    ArrayList<Note> notes;

    private SpriteBase currentlySelectedObject;

    public void instantiateObjects() {
        staves = new ArrayList<>();
        notes = new ArrayList<>();
        try {
            Stave stave = new Stave("Staves/Stave Large.png",
                    DimenConstants.SCROLL_VIEW_PADDING_LEFT,
                    DimenConstants.SCROLL_VIEW_PADDING_TOP);
            stave.setScale(0.6, 0.7);
            staves.add(stave);
            notes.add(new Note("Notes/Crotchets/Crotchet.png",
                    DimenConstants.SCROLL_VIEW_PADDING_LEFT,
                    DimenConstants.SCROLL_VIEW_PADDING_TOP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpriteBase getCurrentlySelectedObject() {
        return currentlySelectedObject;
    }

    public void setCurrentlySelectedObject(SpriteBase currentlySelectedObject) {
        this.currentlySelectedObject = currentlySelectedObject;
    }
}
