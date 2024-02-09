package com.example.simpledownloaddemo

data class Item(
    val name: String,
    val saveName: String,
    val url: String,
)

val fakeData = arrayListOf(
    Item(
        name = "腾讯欢乐麻将全集",
        saveName = "腾讯欢乐麻将全集.apk",
        url = "http://imtt.dd.qq.com/sjy.40001/sjy.00001/16891/apk/D830417642DF843242A0BD78601D1B14.apk?fsname=com.qqgame.happymj_7.7.63_77630.apk&csr=81e7"
    ),
    Item(
        name = "QQ飞车手游",
        saveName = "QQ飞车手游.apk",
        url = "http://imtt.dd.qq.com/sjy.40001/sjy.00001/16891/apk/D09201BF06A2EAFA68833DDAEEB38A5F.apk?fsname=com.tencent.tmgp.speedmobile_1.32.0.2188_1320002188.apk&csr=81e7"
    ),
    Item(
        name = "乐享四人斗地主",
        saveName = "腾讯欢乐麻将全集.apk",
        url = "http://imtt.dd.qq.com/sjy.40001/sjy.00002/16891/apk/DEC4815412BFEDFDD02308835FF74999.apk?fsname=com.cbfq.srddz_9.2.9_929.apk&csr=81e7"
    ),
    Item(
        name = "欢乐升级（腾讯）",
        saveName = "欢乐升级(腾讯).apk",
        url = "http://imtt.dd.qq.com/sjy.40001/sjy.00001/16891/apk/C2F17F4006AB308BAF0B29D102DD6566.apk?fsname=com.tencent.qqgame.qqhlupwvga_4.3.2_43020.apk&csr=81e7"
    ),
    // m3u8测试链接可能会失效，替换成自己的m3u8链接测试
    Item(
        name = "葬送的芙莉莲：第01集(m3u8类型)",
        saveName = "葬送的芙莉莲：第01集(m3u8类型).mp4",
        url = "https://yzzy.play-cdn19.com/20230929/5424_a437130f/index.m3u8"
    ),
    Item(
        name = "葬送的芙莉莲：第02集(m3u8类型)",
        saveName = "葬送的芙莉莲：第02集(m3u8类型).mp4",
        url = "https://yzzy.play-cdn19.com/20230929/5423_63aea672/index.m3u8"
    ),
    Item(
        name = "葬送的芙莉莲：第03集(m3u8类型)",
        saveName = "葬送的芙莉莲：第03集(m3u8类型).mp4",
        url = "https://yzzy.play-cdn19.com/20230929/5422_eac63440/index.m3u8"
    )
)