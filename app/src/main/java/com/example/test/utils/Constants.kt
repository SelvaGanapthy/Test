package com.example.test.utils

import androidx.collection.ArrayMap

class Constants {

    companion object {


        val BASE_URL: String = "https://www.nepalimatrimony.com/api/"


        /*stage*/
//        val BASE_URL: String = "https://apistage.nepalimatrimony.com/api/"
//        /*Htaccess Authenticator*/
        val htusername: String = "admin"
        val htpassword: String = "lRqW6WNT"
//        internal val BASE_URL: String? = "https://restcountries.eu"
//        internal val GEONAMES_URL: String? = "http://api.geonames.org";
//        internal val FILEUPLOAD_URL: String? = "http://www.androidexample.com";

        /*For the Retorfit  N/W purpose*/
        var NETWORK_ERROR: String = "Please check your network connection"
        val TIMEOUTCONNECTION: Long = 60000
        val TIMEOUTSOCKET: Long = 60000
        val CHAT_SOCKET_TIMEOUT: Long = 30000


        fun getParams(
            params: ArrayMap<String, String>,
            reffer: String,
            page: Int,
            timestamp: String
        ): ArrayMap<String, String> {
            if (params != null) {
                params.clear()
            }

            params["Module"] = "Allmatches"
            params["SortBy"] = "Last_Login DESC"
            params["EnablePhotoRequest"] = "1"
            params["MatriId"] = "NEP765498"
            params["PackageName"] = "com.nepalimatrimony"
            params["AppVersionCode"] = "144"
            params["SignalStrength"] = ""
//            params["alreadyViewedOpt"= "1")
            params["AppVersion"] = "5.8"
            params["EncryptId"] = "973a7d8533b79e95943989b233796ad3886a20ae"
            params["Referrer"] = reffer
//            params["YTBVMatch"= "1")

            if (page == 1) {
                params["days"] = "30"
            } else {
                params["alreadyViewedOpt"] = "1"
                params["YTBVMatch"] = "1"
            }


            params["SrchTime"] = "1"
            params["NetworkType"] = "4G"
            params["UniqueId"] = "15fac21631"
            params["Page"] = page.toString()
            params["mima"] = "yes"
            params["DeviceVersion"] = "9"
            params["igOpt"] = "1"
            params["DeviceModel"] = "Redmi Note 7 Pro"
            params["refpp"] = "true"
            params["TokenId"] = ""
            params["DevicePlatform"] = "Android"
            params["OutputType"] = "2"
            params["AppType"] = "2054"
            params["ignoredOpt"] = "1"
            params["CarrierName"] = "Jio 4G"
            params["resultperpage"] = "20"
            params["TimeStamp"] = timestamp
            return params
        }


    }

}