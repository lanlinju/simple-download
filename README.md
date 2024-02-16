# simple-download
基于协程和Flow实现的文件下载，支持普通下载、分片下载、多线程下载、断点续传和m3u8类型文件下载

## 基本使用
```kotlin
// 创建下载任务
val downloadTask = coroutineScope.download("url")

// 监听下载进度
downloadTask.progress()
.onEach { progress ->  /* 更新进度 */  }
.launchIn(lifecycleScope)

// 开始下载
downloadTask.start()
```
### 监听下载状态
```kotlin
downloadTask.state().onEach { state ->
    when (state) {
        is State.None -> text.value = "下载"
        is State.Waiting -> text.value = "等待中"
        is State.Downloading -> {
            text.value = state.progress.percentStr()
            // 更新进度
            progress.value = state.progress.progress()
        }
        is State.Failed -> text.value = "重试"
        is State.Stopped -> text.value = "继续"
        is State.Succeed -> text.value = "完成"
    }
}.launchIn(lifecycleScope)
```
## 创建下载任务
### 后台下载
使用`GlobalScope.download(url)`创建后台下载任务,同时`DownloadTask`可以多个页面之间共享，通过url进行标识
```kotlin
// 创建后台下载任务
val downloadTask  = GlobalScope.download("url")

// 多页面共享, url相同返回同一个DownloadTask
// 1. 第一个页面中
val task1  = GlobalScope.download("http:xxx.sample_url.com")
// 2. 第二个页面中
val task2  = GlobalScope.download("http:xxx.sample_url.com")
// 两个页面中DownloadTask是相同的，即 task1 == task2，可以在任意多个页面中共享同一个下载进度和下载状态
```
### 生命周期绑定
1. 将下载任务与Activity的生命周期绑定，当Activity销毁的时候自动结束下载任务
```kotlin
//activity销毁时，该下载任务自动停止
val downloadTask = lifecycleScope.download("url")
downloadTask.start()
```
2. 将下载任务与可组合函数的生命周期绑定,退出组合时结束下载任务
```kotlin
@Composable
fun DownloadScreen() {
    // 该 CoroutineScope 绑定到调用它的组合点。调用退出组合后，作用域将取消
    val scope = rememberCoroutineScope()
    val downloadTask = remember { scope.download("url") }
}
```
3. 将下载任务与ViewModel的生命周期绑定
```kotlin
val downloadTask = viewModelScope.download("url")
```
## 启动与停止
```kotlin
// 开始下载
downloadTask.start()
// 停止下载
downloadTask.stop()
// 删除下载
downloadTask.remove()
```
## DEMO
<p align="center">
  <img src="./img/demo.jpg" width="30%" />
</p>

## 参考开源
本项目是在[DownloadX](https://github.com/ssseasonnn/DownloadX)的基础上进行开发的，增加了对M3u8类型文件的下载支持