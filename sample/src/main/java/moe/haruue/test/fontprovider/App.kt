package moe.haruue.test.fontprovider

import android.app.Application
import android.util.Log
import moe.shizuku.fontprovider.FontProviderClient
import moe.shizuku.fontprovider.FontRequest

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
class App : Application() {

    companion object {
        var createInApplication:Boolean = false

        fun replace(client: FontProviderClient) {
            val time = System.currentTimeMillis()

            client.setNextRequestReplaceFallbackFonts(true)

            client.replace("Noto Sans CJK",
                    "sans-serif", "sans-serif-thin", "sans-serif-light", "sans-serif-medium", "sans-serif-black")

            client.replace(arrayOf(FontRequest.NOTO_SERIF, FontRequest.NOTO_COLOR_EMOJI_NOUGAT), "Noto Serif CJK",
                    "serif", "serif-thin", "serif-light", "serif-medium", "serif-black")
            Log.d("FontProvider", "replace costs " + (System.currentTimeMillis() - time) + "ms")
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (createInApplication) {
            App.replace(FontProviderClient.create(this)!!)
        }
    }
}