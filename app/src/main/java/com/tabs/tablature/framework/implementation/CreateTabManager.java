package com.tabs.tablature.framework.implementation;

import com.tabs.tablature.framework.base.InteractiveSpriteBase;
import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.Stave;

import java.util.ArrayList;

import static com.tabs.tablature.constants.DimenConstants.INTER_STAVE_DISTANCE;
import static com.tabs.tablature.constants.DimenConstants.SCROLL_VIEW_PADDING_LEFT;
import static com.tabs.tablature.constants.DimenConstants.SCROLL_VIEW_PADDING_TOP;

public class CreateTabManager {

    public float screenScale;

    public ArrayList<Stave> getStaves() { return staves; }
    public ArrayList<Note> getNotes() {
        return notes;
    }

    ArrayList<Stave> staves;
    ArrayList<Note> notes;

    private InteractiveSpriteBase currentlySelectedObject;

    public void instantiateObjects() {
        staves = new ArrayList<>();
        notes = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++) {
                Stave stave = new Stave("Staves/Stave Large.png",
                        SCROLL_VIEW_PADDING_LEFT * screenScale,
                        SCROLL_VIEW_PADDING_TOP * screenScale + i * INTER_STAVE_DISTANCE * screenScale);

                stave.setScale(0.5 * screenScale, 0.18 * screenScale);
                staves.add(stave);
            }
            notes.add(new Note("Notes/Crotchets/Crotchet.png",
                    SCROLL_VIEW_PADDING_LEFT * screenScale,
                    SCROLL_VIEW_PADDING_TOP * screenScale));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InteractiveSpriteBase getCurrentlySelectedObject() {
        return currentlySelectedObject;
    }

    public void setCurrentlySelectedObject(InteractiveSpriteBase currentlySelectedObject) {
        this.currentlySelectedObject = currentlySelectedObject;
    }

    /**
     * Resets transparency of selected object and resets value */
    public void resetCurrentlySelectedObject() {
        this.currentlySelectedObject.setTransparency(255);
        this.currentlySelectedObject = null;
    }
}
