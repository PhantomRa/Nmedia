package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentPreviewPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.presentation.PostViewModel
import ru.netology.nmedia.util.StringArg

class PreviewPostFragment : Fragment(R.layout.fragment_preview_post) {
    companion object {
        var Bundle.textArgPreview: String? by StringArg
    }

    private var _binding: FragmentPreviewPostBinding? = null
    private val binding: FragmentPreviewPostBinding get() = _binding!!

    //    private val viewModel: PostViewModel by activityViewModels()
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    private val data = viewModel.data
    private var previewPostId: Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            previewPostId = it.getString(arguments?.textArgPreview)?.toLongOrNull()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreviewPostBinding.inflate(
            inflater,
            container,
            false
        )

        var post: Post? = null

        data.observe(viewLifecycleOwner) { posts ->
            post = posts.find { it.id == previewPostId }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}