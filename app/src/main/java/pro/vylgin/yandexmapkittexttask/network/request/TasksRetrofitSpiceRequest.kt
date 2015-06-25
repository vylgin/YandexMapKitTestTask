package pro.vylgin.yandexmapkittexttask.network.request

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest

import pro.vylgin.yandexmapkittexttask.network.YandexMap
import pro.vylgin.yandexmapkittexttask.network.response.TasksResponse

public class TasksRetrofitSpiceRequest : RetrofitSpiceRequest<TasksResponse, YandexMap>(javaClass<TasksResponse>(), javaClass<YandexMap>()) {

    throws(Exception::class)
    override fun loadDataFromNetwork(): TasksResponse {
        return getService().getTasks()
    }

}
