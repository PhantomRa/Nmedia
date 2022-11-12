package ru.netology.nmedia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем повились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> \nhttp://netolo.gy/fyb",
            likeCount = 999,
            shareCount = 999999,
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "29 мая в 13:45",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем повились курсы по дизайну, разработке, аналитике и управлению."
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "3 июня в 13:45",
            content = "Привет, это новая Нетология!"
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "4 июня в 13:45",
            content = "Привет, это новая Нетология!"
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "5 июня в 13:45",
            content = "Привет, это новая Нетология!"
        ),
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it
            else {
                if (it.likedByMe) it.copy(likeCount = it.likeCount - 1, likedByMe = !it.likedByMe)
                else it.copy(likeCount = it.likeCount + 1, likedByMe = !it.likedByMe)
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shareCount = it.shareCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    published = "Now",
                )
            ) + posts
        } else
            posts = posts.map {
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        data.value = posts
    }
}