package com.example.recipe.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 29,August,2023,ArezouGhorbaniii@gmail.com**/
@Suppress("DEPRECATION")
class NetworkChecker @Inject constructor(
    private val manager: ConnectivityManager,
    private val request: NetworkRequest
) : ConnectivityManager.NetworkCallback() {

    private val isNetworkAvailable = MutableStateFlow(false)
    private var capabilities: NetworkCapabilities? = null
    fun checkNetworkAvailability(): MutableStateFlow<Boolean> {
//        Register
        manager.registerNetworkCallback(request, this)
//        init Network
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ActiveNetwork
            val activeNetwork = manager.activeNetwork
            if (activeNetwork == null) {
                isNetworkAvailable.value = false
                return isNetworkAvailable
            }
//            capability
            capabilities = manager.getNetworkCapabilities(activeNetwork)
            if (capabilities == null) {
                isNetworkAvailable.value = false
                return isNetworkAvailable
            }
            isNetworkAvailable.value = when {
                capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }

                capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }

                else -> {
                    false
                }
            }
        } else manager.run {
            manager.activeNetworkInfo?.run {
                isNetworkAvailable.value = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetworkAvailable.value = true
    }

    override fun onLost(network: Network) {
        isNetworkAvailable.value=false
    }
}