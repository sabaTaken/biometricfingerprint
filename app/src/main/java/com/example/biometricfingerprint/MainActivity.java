package com.example.biometricfingerprint;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends AppCompatActivity
{
    private String mTitle;
    private String mSubTitle;
    private String mDescription;
    private String mBackButtonText;
    private String boiMetric_log = "biometric";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = " title ";
        mSubTitle = " sub title ";
        mDescription = " some description ";
        mBackButtonText = " back ";

        buildBioMetric();
    }


    /**
     * build biometric
     */
    private void buildBioMetric()
    {
        Executor executor = Executors.newSingleThreadExecutor();

        FragmentActivity mFragmentActivity = this;

        final BiometricPrompt biometricPrompt = new BiometricPrompt(mFragmentActivity, executor, new BiometricPrompt.AuthenticationCallback()
        {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString)
            {
                super.onAuthenticationError(errorCode, errString);
                //مشکلات سخت افزاری 1
                if (errorCode == BiometricPrompt.ERROR_HW_UNAVAILABLE)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast("مشکل سخت افزاری "));
                    onBackPressed();
                }
                // مشکل در پردازش اثر انگشت 2
                else if (errorCode == BiometricPrompt.ERROR_UNABLE_TO_PROCESS)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast("سنسور قادر به پردازش انگشت شما نمی باشد  "));
                    onBackPressed();
                }
                // اتمام زمان 3
                else if (errorCode == BiometricPrompt.ERROR_TIMEOUT)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast(" زمان شما به اتمام رسیده است "));
                    onBackPressed();
                }
                // کم بودن حافظه 4
                else if (errorCode == BiometricPrompt.ERROR_NO_SPACE)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast(" فضای حافظع کم است "));
                    onBackPressed();
                }
                // کنسل شدن پروژه 5
                else if (errorCode == BiometricPrompt.ERROR_CANCELED)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    onBackPressed();
                }
                //خطا هنگامی که بیش از 5 بار اتفاق بیافتد 7
                else if (errorCode == BiometricPrompt.ERROR_LOCKOUT)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast(" تعداد دفعات اشتباه رمز بیشتر از حد مجاز است لطفا پس از 30 ثانیه دوباره تلاش کنید "));
                    onBackPressed();
                }
                //8 اشتباهات سازندگان
                else if (errorCode == BiometricPrompt.ERROR_VENDOR)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    onBackPressed();
                }
                // پس از چند با انگشت اشتباه وارد کردن  9
                else if (errorCode == BiometricPrompt.ERROR_LOCKOUT_PERMANENT)
                {
                    //                    errorText.setVisibility(View.VISIBLE);
                    //                    errorText.setText("تعداد دفعات اشتباه رمز بیشتر از حد مجاز است لطفا پس از 30 ثانیه دوباره تلاش کنید");
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast("تعداد دفعات ثبت اثر انگشت بیشتر از حد مجاز است لطفا با پشتیبانی تماس بگیرید"));
                    onBackPressed();
                }
                // بک سخت افزاری یا بستن دیالوگ finger print توسط کاربر 10
                else if (errorCode == BiometricPrompt.ERROR_USER_CANCELED)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    onBackPressed();
                }
                //ست نکردن اثر انگشت برای دستگاه 11
                else if (errorCode == BiometricPrompt.ERROR_NO_BIOMETRICS)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast("لطفا در بخش تنظیمات اثر انگشت یک اثر انگشت ثبت کنید"));
                    onBackPressed();
                }
                // مشکل در سنسور دستگاه 12
                else if (errorCode == BiometricPrompt.ERROR_HW_NOT_PRESENT)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    runOnUiThread(()->toast("مشکل سخت افزاری دستگاه "));


                    onBackPressed();
                }
                //کلیک بر روی دکمه بازگشت  13
                else if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON)
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : error code is : " + errorCode + "  -- error : " + errString);
                    onBackPressed();
                }
                else
                {
                    Log.i(boiMetric_log, "//biometric fingerprint : " + "error : default error");
                    onBackPressed();
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result)
            {
                super.onAuthenticationSucceeded(result);
                Log.i(boiMetric_log, "//biometric fingerprint : " + "ok! ... successfully finger print !!!");
                //TODO: Called when a biometric is recognized.
            }

            @Override
            public void onAuthenticationFailed()
            {
                super.onAuthenticationFailed();
                Log.i(boiMetric_log, "biometric fingerprint : " + " failed authentication");
                //TODO: Called when a biometric is valid but not recognized.
            }
        });


        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(mTitle)
                .setSubtitle(mSubTitle)
                .setDescription(mDescription)
                .setNegativeButtonText(mBackButtonText)
                .build();


        biometricPrompt.authenticate(promptInfo);
    }


    /**
     * toast
     * @param text string
     */
    private void toast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            finishAffinity();
        }
    }
}
