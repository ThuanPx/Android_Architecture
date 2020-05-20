package com.example.framgia.architecture.data.source.repository

import com.example.framgia.architecture.data.model.Token
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefs
import com.example.framgia.architecture.data.source.local.sharedprf.SharedPrefsKey

interface TokenRepository {

    fun getToken(): Token?
    fun saveToken(token: Token)
    fun clearSharedPref()
    fun isHasLogIn(): Boolean
    fun doLogout()
}

class TokenRepositoryImpl(private val sharedPrefsApi: SharedPrefs) : TokenRepository {

    override fun getToken(): Token? {
        return sharedPrefsApi.get(SharedPrefsKey.KEY_TOKEN, Token::class.java)
    }

    override fun saveToken(token: Token) {
        sharedPrefsApi.put(SharedPrefsKey.KEY_TOKEN, token)
    }

    override fun clearSharedPref() {
        sharedPrefsApi.clear()
    }

    override fun isHasLogIn(): Boolean {
        return !getToken()?.accessToken.isNullOrEmpty()
    }

    override fun doLogout() {
        clearSharedPref()
    }
}
