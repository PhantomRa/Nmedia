package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.presentation.PostViewModel
import ru.netology.nmedia.util.AndroidUtils

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: PostViewModel by viewModels()
    private val interactionListener = object : OnInteractionListener {
        override fun onLike(post: Post) {
            viewModel.likeById(post.id)
        }

        override fun onShare(post: Post) {
            viewModel.shareById(post.id)
        }

        override fun onEdit(post: Post) {
            viewModel.edit(post)
            binding.editGroup.visibility = View.VISIBLE
        }

        override fun onRemove(post: Post) {
            viewModel.removeById(post.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = PostsAdapter(interactionListener)
        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.contentEditText) {
                requestFocus()
                setText(post.content)
                binding.editedTextView.text = post.content
            }
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    viewModel.changeContent(text.toString())
                    viewModel.save()
                    binding.editGroup.visibility = View.GONE

                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(this)
                }
            }
        }

        binding.cancelImageButton.setOnClickListener {
            with(binding.contentEditText) {
                binding.editGroup.visibility = View.GONE
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}