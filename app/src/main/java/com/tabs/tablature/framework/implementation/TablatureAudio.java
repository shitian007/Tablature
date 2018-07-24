package com.tabs.tablature.framework.implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.tabs.tablature.framework.interfaces.Audio;
import com.tabs.tablature.framework.interfaces.Music;
import com.tabs.tablature.framework.interfaces.Sound;

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
