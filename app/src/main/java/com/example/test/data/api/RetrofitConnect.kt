package com.example.test.data.api

import android.os.Build
import android.util.Log
import com.example.test.utils.Constants
import com.example.test.utils.IntegerDefault0Adapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitConnect {

    private var httpClient: OkHttpClient.Builder? = null
    private var httpClientGlide: OkHttpClient.Builder? = null
    private var retroApiServices: ApiServices? = null
    private var retryCount = 0

    companion object {
        private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder? {

            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
                try {
                    val sc = SSLContext.getInstance("TLSv1.2")
                    sc.init(null, null, null)
                    val trustManagerFactory =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                    trustManagerFactory.init(null as KeyStore?)
                    val trustManagers = trustManagerFactory.trustManagers
                    check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                        "Unexpected default trust managers:" + Arrays.toString(
                            trustManagers
                        )
                    }
                    val trustManager = trustManagers[0] as X509TrustManager
                    client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory), trustManager)
                    val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build()
                    val specs = ArrayList<ConnectionSpec>()
                    specs.add(cs)
                    specs.add(ConnectionSpec.COMPATIBLE_TLS)
                    specs.add(ConnectionSpec.CLEARTEXT)
                    client.connectionSpecs(specs)
                } catch (exc: Exception) {
                    exc.message
//                    ExceptionTrack.instance.TrackLog(exc)
                }

            }
            return client
        }
    }

    /*Initi retrofit with base Url*/
    fun retrofit(UrlBasePath: String): ApiServices {

        if (httpClient == null) {

            /*val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    //header
                    val request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build()
                    return@Interceptor chain.proceed(request)
                })
                .addInterceptor(interceptor)
                //  .connectTimeout(100, TimeUnit.SECONDS)
                // .readTimeout(100, TimeUnit.SECONDS)
                .build()*/


            httpClient = OkHttpClient.Builder()
            val authToken = Credentials.basic(Constants.htusername, Constants.htpassword)
            val interceptor = AuthenticationInterceptor(authToken)
            httpClient?.connectTimeout(Constants.TIMEOUTCONNECTION, TimeUnit.MILLISECONDS)
            httpClient?.readTimeout(Constants.TIMEOUTSOCKET, TimeUnit.MILLISECONDS)
            httpClient?.addInterceptor(interceptor)
            //httpClient.addNetworkInterceptor(new StethoInterceptor());
            //httpClient.retryOnConnectionFailure(false);
            enableTls12OnPreLollipop(httpClient!!)

//            val logging = HttpLoggingInterceptor()
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            httpClient!!.addInterceptor(logging)
        }

        retroApiServices = Retrofit.Builder().baseUrl(UrlBasePath)
            .addConverterFactory(ToStringConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gsonMapper()))
            .client(httpClient!!.build())
            .build().create(ApiServices::class.java)
        return retroApiServices!!
    }


    fun gsonMapper(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Int::class.java, IntegerDefault0Adapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefault0Adapter())
            .serializeNulls()
            .create()
    }


    /*retrofit Request & Response*/
    fun <T> AddToEnqueue(
        baseCall: retrofit2.Call<T>,
        listener: ApiRequestListener,
        ReqType: String
    ) {

        baseCall.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>?, response: Response<T>?) {

                Log.i("res", response!!.isSuccessful.toString())

                if (response?.body() != null) {

                    if (response.code() == 200) {
                        listener.onReceiveResult(ReqType, response)
                    }
                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                Log.v("retrofit", "call failed" + t.toString())
            }

        })
    }


}