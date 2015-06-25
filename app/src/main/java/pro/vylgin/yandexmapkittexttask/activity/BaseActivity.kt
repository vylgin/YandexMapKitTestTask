package pro.vylgin.yandexmapkittexttask.activity

import android.support.v7.app.AppCompatActivity

import com.octo.android.robospice.SpiceManager

import pro.vylgin.yandexmapkittexttask.network.YandexMapRetrofitSpiceService

public open class BaseActivity : AppCompatActivity() {

    public val spiceManager: SpiceManager = SpiceManager(javaClass<YandexMapRetrofitSpiceService>())

    override fun onStart() {
        spiceManager.start(this)
        super.onStart()
    }

    override fun onStop() {
        spiceManager.shouldStop()
        super.onStop()
    }

}
