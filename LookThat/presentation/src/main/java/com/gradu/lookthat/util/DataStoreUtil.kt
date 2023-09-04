package com.gradu.lookthat.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.gradu.lookthat.di.MyApplication.Companion.X_ACCESS_TOKEN
import com.gradu.lookthat.di.MyApplication.Companion.X_REFRESH_TOKEN
import kotlinx.coroutines.flow.first

class DataStoreUtil(private val context: Context){
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GARAMGAEBI_APP")


    suspend fun saveStringToDataStore(key: String, value: String) {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        context.dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }
    suspend fun saveIntToDataStore(key: String, value: Int) {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        context.dataStore.edit { preferences ->
            preferences[intKey] = value
        }
    }

    suspend fun saveBooleanToDataStore(key: String, value: Boolean) {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        context.dataStore.edit { preferences ->
            preferences[booleanKey] = value
        }
    }
    suspend fun loadStringData(key: String): String? {
        val stringKey = stringPreferencesKey(key) // String 타입 저장 키값
        val preferences = context.dataStore.data.first()
        return preferences[stringKey]
    }

    suspend fun loadIntData(key: String): Int? {
        val intKey = intPreferencesKey(key) // String 타입 저장 키값
        val preferences = context.dataStore.data.first()
        return preferences[intKey]
    }
    suspend fun loadBooleanData(key: String): Boolean? {
        val booleanKey = booleanPreferencesKey(key) // String 타입 저장 키값
        val preferences = context.dataStore.data.first()
        return preferences[booleanKey]
    }

    suspend fun clearDataStore() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun getUserAccessToken() : String? {
        return loadStringData(X_ACCESS_TOKEN)
    }
    suspend fun getUserRefreshToken() : String? {
        return loadStringData(X_REFRESH_TOKEN)
    }


}