package com.lihao.composedemo.custom_theme.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class Post(
    val id: Long,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val metadata: Metadata,
    @DrawableRes val imageId: Int,
    @DrawableRes val imageThumbId: Int,
    val tags: Set<String>
)

@Immutable
data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int
)

@Immutable
data class PostAuthor(
    val name: String,
    val url: String? = null
)

/*
模拟获取相关数据。
 */
object PostRepo {
    fun getPosts(): List<Post> = posts
    fun getFeaturedPost(): Post = posts.random()
}

/*
示例数据。
 */
private val pietro = PostAuthor("Pietro Maggi", "https://medium.com/@pietro_maggi")
private val manuel = PostAuthor("Manuel vivo", "https://medium.com/@manuel_vivo")
private val florina = PostAuthor("Florina Muntenescu", "https://medium.com/@florina_muntenescu")
private val jose = PostAuthor("Jose Alcerreca", "https://medium.com/@jose_alcerreca")

private val post1 = Post(
    id = 1L,
    title = "A Little Thing about Android Module Paths",
    subtitle = "How to configure your module path, instead of using One whole module.",
    url = "https://medium.com/androiddevelopers/gradle-path-configuration",
    metadata = Metadata(
        author = pietro,
        date = "Augest 02",
        readTimeMinutes = 1
    ),
    imageId = android.R.drawable.star_big_on,
    imageThumbId = android.R.drawable.star_on,
    tags = setOf("Modularization", "Gradle")
)

private val post2 = Post(
    id = 2L,
    title = "Dagger in Kotlin: Gotchas and Optimizations",
    subtitle = "Use Dagger in Kotlin! This article includes best practise of some content.",
    url = "https://medium.com/androiddevelopers/dagger-in-kotlin-gotchas-and-optimizations",
    metadata = Metadata(
        author = manuel,
        date = "July 30",
        readTimeMinutes = 3
    ),
    imageId = android.R.drawable.btn_radio,
    imageThumbId = android.R.drawable.btn_default_small,
    tags = setOf("Dagger", "Kotlin")
)

private val post3 = Post(
    id = 3L,
    title = "From Java Programming Language to Kotlin",
    subtitle = "Learn how to get started converting Java Programming to Kotlin.",
    url = "https://medium.com/androiddevelopers/from-java-programming-language-to-kotlin",
    metadata = Metadata(
        author = florina,
        date = "July 09",
        readTimeMinutes = 1
    ),
    imageId = android.R.drawable.btn_star_big_on,
    imageThumbId = android.R.drawable.btn_star_big_off,
    tags = setOf("Kotlin")
)

private val post4 = Post(
    id = 4L,
    title = "Locale changes and the AndroidViewModel antipattern",
    subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid mess code structure.",
    url = "https://medium.com/androiddevelopers/locale-changes-and-the-androidViewModel-antipattern",
    metadata = Metadata(
        author = jose,
        date = "April 02",
        readTimeMinutes = 1
    ),
    imageId = android.R.drawable.checkbox_on_background,
    imageThumbId = android.R.drawable.checkbox_off_background,
    tags = setOf("ViewModel", "Locale")
)

private val post5 = Post(
    id = 5L,
    title = "Collections and sequences in Kotlin",
    subtitle = "Working with collections is a common task and the Kotlin language supported that.",
    url = "https://medium.com/androiddevelopers/collections-and-sequences-in-kotlin",
    metadata = Metadata(
        author = florina,
        date = "July 24",
        readTimeMinutes = 4
    ),
    imageId = android.R.drawable.stat_notify_sdcard_prepare,
    imageThumbId = android.R.drawable.stat_notify_sdcard_usb,
    tags = setOf("Kotlin", "Collections", "Sequences")
)

private val posts = listOf(
    post1,
    post2,
    post3,
    post4,
    post5,
    post1.copy(id = 6L),
    post2.copy(id = 7L),
    post3.copy(id = 8L),
    post4.copy(id = 9L),
    post5.copy(id = 10L)
)