package com.tabs.tablature.framework.implementation;

import android.media.SoundPool;

import com.tabs.tablature.framework.interfaces.Sound;

public class TablatureSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public TablatureSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}

