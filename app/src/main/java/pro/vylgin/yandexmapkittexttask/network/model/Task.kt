package pro.vylgin.yandexmapkittexttask.network.model

import com.google.gson.annotations.SerializedName

public class Task {

    SerializedName("ID")
    public val id: Int = 0
    public val title: String? = null
    public val date: Long = 0
    public val text: String? = null
    public val longText: String? = null
    public val durationLimitText: String? = null
    public val price: Int = 0
    public val locationText: String? = null
    public val location: Location? = null
    public val zoomLevel: Int = 0
    public val prices: List<Price>? = null
    private val translation: Boolean = false
    public val reflink: String? = null
    public val bingMapImage: String? = null

    public fun isTranslation(): Boolean {
        return translation
    }

}
