package com.forest.download

import com.forest.download.core.DownloadConfig
import com.forest.download.core.DownloadParam
import com.forest.download.core.DownloadTask
import kotlinx.coroutines.CoroutineScope

fun CoroutineScope.download(
    url: String,
    saveName: String = "",
    savePath: String ,
    downloadConfig: DownloadConfig = DownloadConfig()
): DownloadTask {
    val downloadParam = DownloadParam(url, saveName, savePath)
    val task = DownloadTask(this, downloadParam, downloadConfig)
    return downloadConfig.taskManager.add(task)
}

fun CoroutineScope.download(
    downloadParam: DownloadParam,
    downloadConfig: DownloadConfig = DownloadConfig()
): DownloadTask {
    val task = DownloadTask(this, downloadParam, downloadConfig)
    return downloadConfig.taskManager.add(task)
}