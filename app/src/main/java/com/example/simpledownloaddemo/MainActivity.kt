package com.example.simpledownloaddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.simpledownloaddemo.ui.theme.SimpleDownloadDemoTheme
import com.forest.download.State
import com.forest.download.download
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleDownloadDemoTheme {

                val saveDir = LocalContext.current.getExternalFilesDir("download/")!!.path

                Column(modifier = Modifier.fillMaxSize()) {
                    fakeData.forEach { item ->

                        val downloadTask = remember(item.url) {
                            lifecycleScope.download(
                                url = item.url,
                                savePath = saveDir,
                                saveName = item.saveName
                            )
                        }

                        val text = remember { mutableStateOf("下载") }
                        val progress = remember { mutableStateOf(0f) }

                        ListItem(
                            headlineContent = { Text(text = item.name) },
                            trailingContent = {
                                ProgressButton(text = text.value, progress = progress.value) {
                                    when {
                                        downloadTask.isStarted() -> downloadTask.stop()
                                        else -> downloadTask.start()
                                    }
                                }
                            }
                        )

                        LaunchedEffect(downloadTask) {
                            downloadTask.state().onEach { state ->
                                when (state) {
                                    is State.None -> text.value = "下载"
                                    is State.Waiting -> text.value = "等待中"
                                    is State.Downloading -> {
                                        text.value = state.progress.percentStr()
                                        progress.value = state.progress.progress()
                                    }

                                    is State.Failed -> text.value = "重试"
                                    is State.Stopped -> text.value = "继续"
                                    is State.Succeed -> text.value = "完成"
                                }
                            }.launchIn(this)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ProgressButton(
    text: String,
    progress: Float,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(40.dp),
        shape = CircleShape,
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxSize())
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleDownloadDemoTheme {
        Column {
            ListItem(headlineContent = { Text(text = "QQ飞车手游") }, trailingContent = {
                ProgressButton(text = "下载", progress = 0.4f) { }
            })
            Divider(thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
            ListItem(headlineContent = { Text(text = "QQ飞车手游") }, trailingContent = {
                ProgressButton(text = "下载", progress = 0.9f) { }
            })
            Divider(thickness = 0.5.dp)
            ListItem(headlineContent = { Text(text = "QQ飞车手游") }, trailingContent = {
                ProgressButton(text = "下载", progress = 0.3f) { }
            })
            Divider(thickness = 0.5.dp)
        }

    }
}