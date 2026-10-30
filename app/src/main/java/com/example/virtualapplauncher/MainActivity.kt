package com.example.virtualapplauncher

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.virtualapplauncher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunch.setOnClickListener {
            val packageName = binding.packageInput.text.toString().trim()
            if (packageName.isEmpty()) {
                Toast.makeText(this, "Masukkan package name aplikasi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            launchApp(packageName)
        }

        binding.btnOpenUrl.setOnClickListener {
            val url = binding.urlInput.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Masukkan URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val validUrl = if (url.startsWith("http://") || url.startsWith("https://")) {
                url
            } else {
                "https://$url"
            }
            openUrl(validUrl)
        }

        binding.btnOpenPlayStore.setOnClickListener {
            val packageName = binding.packageInput.text.toString().trim()
            if (packageName.isEmpty()) {
                Toast.makeText(this, "Masukkan package name aplikasi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            openPlayStore(packageName)
        }
    }

    private fun launchApp(packageName: String) {
        try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent == null) {
                Toast.makeText(this, "Aplikasi tidak ditemukan atau belum diinstal", Toast.LENGTH_LONG).show()
                return
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Gagal membuka aplikasi: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Tidak ada browser yang tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openPlayStore(packageName: String) {
        try {
            val uri = Uri.parse("market://details?id=$packageName")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            val web = "https://play.google.com/store/apps/details?id=$packageName"
            openUrl(web)
        }
    }
}
