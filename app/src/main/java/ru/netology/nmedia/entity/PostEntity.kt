package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val video: String? = null,
    val likedByMe: Boolean = false,
    var likeCount: Long = 0,
    var shareCount: Long = 0,
    var viewCount: Long = 0,
) {
    fun toDto() =
        Post(id, author, published, content, video, likedByMe, likeCount, shareCount, viewCount)

    companion object {
        fun fromDto(dto: Post) = PostEntity(
            dto.id,
            dto.author,
            dto.published,
            dto.content,
            dto.video,
            dto.likedByMe,
            dto.likeCount,
            dto.shareCount,
            dto.viewCount
        )
    }
}