package com.github.fajaragungpramana.pokelib.core.di

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Core {

    private lateinit var mContext: Context

    fun init(context: Context) {
        if (::mContext.isInitialized) return

        mContext = context
    }

    fun getContext(): Context {
        if (::mContext.isInitialized) return mContext

        throw UninitializedPropertyAccessException("Context is not initialized.")
    }

}