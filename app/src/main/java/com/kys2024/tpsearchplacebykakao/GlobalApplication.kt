package com.kys2024.tpsearchplacebykakao

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        //카카오 SDK 초기화 작업
        KakaoSdk.init(this, "dec4139553397b32a5347ba2135d82cd")

    }
}