package com.tabs.tablature.framework.InputOutput;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class TablatureAudio implements Audio {

    AssetManager assets;
    SoundPool soundPool;

    public TablatureAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor=assets.openFd(filename);
            return new TablatureMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '"+filename+"'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor=assets.openFd(filename);
            int soundId=soundPool.load(assetDescriptor, 0);
            return new TablatureSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '"+filename+"'");
        }
    }
}
