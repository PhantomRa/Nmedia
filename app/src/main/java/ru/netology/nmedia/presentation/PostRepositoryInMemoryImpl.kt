package ru.netology.nmedia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        published = "21 мая в 18:36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                "по онлайн-маркетингу. Затем повились курсы по дизайну, разработке, аналитике и управлению. " +
                "Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но " +
                "самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет " +
                "хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и " +
                "начать цепочку перемен -> \nhttp://netolo.gy/fyb",
        likeCount = 999,
        shareCount = 999999,
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post =
            if (post.likedByMe) post.copy(likeCount = post.likeCount - 1) else post.copy(likeCount = post.likeCount + 1)
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }

    override fun share() {
        post = post.copy(shareCount = post.shareCount + 1)
        data.value = post
    }
}