package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.codesroots.mac.cards.models.*

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory



interface APIServices {

    @POST("wserv?auth=0&page=1")/*{company_id}*/
    fun Login(@Query(value = "un") username :String,@Query(value = "pw")  password :String): Observable<LogData>

    @POST("wserv?page=2&val=0")/*{company_id}*/
    fun GetCompanyData(@Query("auth") auth: String):
            Observable<List<CompanyDatum>>

    @POST("wserv?page=12")/*{company_id}*/
    fun GetMyBlanceData(@Query("auth") auth: String):
            Observable<MyBalance>

    @POST("wserv?page=3")/*{company_id}*/
    fun GetPackageDetails(@Query("val") packageid: String,@Query("auth") auth: String):
            Observable<List<CompanyDatum>>




    @POST("wserv?page=5")/*{company_id}*/
    fun BuyPackage(@Query("val") packageid: String,@Query("auth") auth: String,@Query("mount") amount: String):
            Observable<Buypackge>

    @POST("wserv?page=18")/*{company_id}*/
    fun PrintReport(@Query("val") packageid: String,@Query("auth") auth: String):
            Observable<Buypackge>
    @POST("wserv?page=17")/*{company_id}*/
    fun SliderData(@Query("auth") auth: String):
            Observable<List<SliderElement>>
    @POST("wserv?page=13")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String):
            Observable<List<ReportDaily>>

    @POST("wserv?page=13")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String,@Query("val") fromto:String):
            Observable<List<ReportDaily>>
    @GET("wserv?page=15")/*{company_id}*/
    fun GetTerms():
            Observable<Terms>
    @GET("wserv?page=14")/*{company_id}*/
    fun GetContactData():
            Observable<Terms>

    @GET("wserv?page=16")/*{company_id}*/
    fun GetPartnersData():
            Observable<List<PartnersModel>>
    companion object {
        fun create(): APIServices {

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://across-cities.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();


            return retrofit.create(APIServices::class.java)
        }


        fun createMap(): APIServices {

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()


            val gson = GsonBuilder().setLenient().create()
            val retrofit = retrofit2.Retrofit.Builder()

                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)

                .build()


            return retrofit.create(APIServices::class.java)
        }

    }

}