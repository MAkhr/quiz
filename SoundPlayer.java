package com.example.quiz;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int trueSound;
    private static int falseSound;

    private AudioAttributes audioAttributes;

    public SoundPlayer(Context context) {
       //短い音を再生するときのsoundPool
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        trueSound = soundPool.load(context, R.raw.quiz_correct_answer, 1);
        falseSound = soundPool.load(context, R.raw.quiz_wrong_buzzer, 1);
    }

    public void playHitSound() {
        soundPool.play(trueSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound() {
        soundPool.play(falseSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}

