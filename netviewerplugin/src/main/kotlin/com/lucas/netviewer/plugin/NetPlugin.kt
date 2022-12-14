package com.lucas.netviewer.plugin

import com.android.build.gradle.AppExtension
import com.lucas.netviewer.okhttp.OkHttpTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class NetPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        var enableNetPlugin = true
        val properties = Properties()
        val file = project.rootProject.file("local.properties")
        if (file.exists()) {
            properties.load(file.inputStream())
            enableNetPlugin = properties.getProperty("net.enablePlugin", "true")?.toBoolean() ?: true
        }
        println("MonitorPlugin---->enableMonitorPlugin = $enableNetPlugin")
        if (!enableNetPlugin) return
        try {
            val appException: AppExtension = project.extensions.getByName("android") as AppExtension
            appException.registerTransform(OkHttpTransform(project))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}