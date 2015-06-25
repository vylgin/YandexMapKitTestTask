package pro.vylgin.yandexmapkittexttask.network

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService

public class YandexMapRetrofitSpiceService : RetrofitGsonSpiceService() {

    override fun onCreate() {
        super.onCreate()
        addRetrofitInterface(javaClass<YandexMap>())
    }

    override fun getServerUrl(): String {
        return ApiConst.API_URL_BASE
    }

}
