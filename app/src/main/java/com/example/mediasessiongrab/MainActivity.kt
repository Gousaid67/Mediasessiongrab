package com.example.mediasessiongrab

import android.content.ComponentName
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("TAG", ContextCompat.checkSelfPermission(this, android.Manifest.permission.QUERY_ALL_PACKAGES).toString())


        val mngr = this.getSystemService(MEDIA_SESSION_SERVICE) as MediaSessionManager
        val mmlist = mngr.getActiveSessions(
            ComponentName(
                this,
                NotificationListener::class.java
            )
        )
       val paks = packageManager.getInstalledPackages(0)
        for(pak in paks)
        {
            Log.i("", pak.packageName)
        }

        val contarr : Array<String> = Array(mmlist.size) {""}
        val imgarr : Array<Drawable?> = arrayOfNulls(mmlist.size)

        mmlist.forEachIndexed{index, session ->


            val icon: Drawable = packageManager.getApplicationIcon(session.packageName)

            val txt = packageManager.getApplicationLabel(packageManager.getApplicationInfo(session.packageName, 0)).toString()
            contarr[index] = txt
            imgarr[index] = icon
            }



        val speeen = findViewById<Spinner>(R.id.mediasessionlist)
        val inputlist = SpinnerAdapter(this, R.layout.spinner_value_layout,contarr, imgarr)
        inputlist.setDropDownViewResource(R.layout.spinner_dropdown_item)
        speeen.adapter = inputlist


        Log.i("", Mc_to_Str(mmlist))
    }
    private fun Mc_to_Str(mm_list: List<MediaController>): String {
        val new_str = StringBuilder()
        for (i in mm_list) {
            new_str.append(
                """
                ${i.packageName}
                
                """.trimIndent()
            )
        }
        return new_str.toString()
    }
}