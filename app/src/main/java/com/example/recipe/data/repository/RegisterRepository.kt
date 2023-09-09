package com.example.recipe.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recipe.data.source.RemoteDataSource
import com.example.recipe.models.register.BodyRegister
import com.example.recipe.models.register.RegisterStoredModel
import com.example.recipe.utils.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 03,September,2023,ArezouGhorbaniii@gmail.com**/
@ActivityRetainedScoped
class RegisterRepository @Inject constructor(
    private val remote: RemoteDataSource,
    @ApplicationContext private val context: Context
) {
    suspend fun postRegister(apiKey: String, body: BodyRegister) = remote.postRegister(apiKey, body)

    //Store user info
    private object StoredKeys {
        val userName = stringPreferencesKey(Constant.REGISTER_USERNAME)
        val hash = stringPreferencesKey(Constant.REGISTER_HASH)

    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.REGISTER_USERINFO)
    suspend fun saveRegisterData(userName: String, hash: String) {
        context.dataStore.edit {
            it[StoredKeys.userName] = userName
            it[StoredKeys.hash] = hash
        }
    }

    val readRegisterData: Flow<RegisterStoredModel> = context.dataStore.data
        .catch {e->
            if (e is IOException){
                emit(emptyPreferences())
            }else throw e

        }.map {
            val  userName=it[StoredKeys.userName]?:""
            val  hash=it[StoredKeys.hash]?:""
            RegisterStoredModel(userName, hash)
        }
}