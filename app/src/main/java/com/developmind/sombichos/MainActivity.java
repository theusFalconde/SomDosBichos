package com.developmind.sombichos;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView cao;
    private ImageView gato;
    private ImageView leao;
    private ImageView macaco;
    private ImageView ovelha;
    private ImageView vaca;
    private MediaPlayer mediaPlayer;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSetup();
        initializeClicks();
        buldAdmob();
    }

    private void buldAdmob() {


        String key = getResources().getString(R.string.admob_key);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(key);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdLoaded() {
                //  mAdIsLoading = false;
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // mAdIsLoading = false;
            }
        });

        interstitialAd.loadAd(adRequest);
        mRewardedVideoAd.loadAd(key,adRequest);
        adView.loadAd(adRequest);

    }



    private void initializeClicks() {
        cao.setOnClickListener(this);
        gato.setOnClickListener(this);
        leao.setOnClickListener(this);
        macaco.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        vaca.setOnClickListener(this);
    }

    private void initializeSetup() {

        cao = (ImageView) findViewById(R.id.caoId);
        gato = (ImageView) findViewById(R.id.gatoId);
        leao = (ImageView) findViewById(R.id.leaoId);
        macaco = (ImageView) findViewById(R.id.macacoId);
        ovelha = (ImageView) findViewById(R.id.ovelhaId);
        vaca = (ImageView) findViewById(R.id.vacaId);
        adView = (AdView) findViewById(R.id.adView);
    }

    @Override
    public void onClick(View v) {

        v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_anim));

        switch (v.getId()) {
            case R.id.caoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.cao);
                tocarMusica();
                break;
            case R.id.gatoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.gato);
                tocarMusica();
                break;
            case R.id.leaoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.leao);
                tocarMusica();
                break;
            case R.id.macacoId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.macaco);
                tocarMusica();
                break;
            case R.id.ovelhaId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ovelha);
                tocarMusica();
                break;
            case R.id.vacaId:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.vaca);
                tocarMusica();
                break;

        }

    }

    public void tocarMusica() {

        if (interstitialAd.isLoaded()) {
            Log.d("TAG", "DEU BOA");
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        if (mRewardedVideoAd.isLoaded()) {
            Log.d("TAG", "DEU BOA");
            mRewardedVideoAd.show();
        }
        else {
            Log.d("TAG", "The VIDEO wasn't loaded yet.");
        }

        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
       buldAdmob();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        buldAdmob();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

}