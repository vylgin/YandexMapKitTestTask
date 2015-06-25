package pro.vylgin.yandexmapkittexttask.network

import pro.vylgin.yandexmapkittexttask.network.response.TasksResponse
import retrofit.http.GET
import retrofit.http.Query

public interface YandexMap {

    GET("/tasks")
    public fun getTasks(): TasksResponse

}
