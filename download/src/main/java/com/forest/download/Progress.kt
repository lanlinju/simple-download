package com.forest.download

import com.forest.download.utils.formatSize
import com.forest.download.utils.ratio


class Progress(
    var downloadSize: Long = 0,
    var totalSize: Long = 0,
    /**
     * 用于标识一个链接是否是分块下载, 如果该值为true, 那么totalSize为-1
     */
    var isChunked: Boolean = false
) {
    /**
     * Return total size str. eg: 10M
     */
    fun totalSizeStr(): String {
        return totalSize.formatSize()
    }

    /**
     * Return download size str. eg: 3M
     */
    fun downloadSizeStr(): String {
        return downloadSize.formatSize()
    }

    /**
     * Return percent number.
     */
    fun percent(): Double {
        if (isChunked) return 0.0
        return downloadSize ratio totalSize
    }

    /**
     * Return percent string.
     */
    fun percentStr(): String {
        return "${percent()}%"
    }
}