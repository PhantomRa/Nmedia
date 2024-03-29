package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.presentation.PostViewModel
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg

class NewPostFragment : Fragment(R.layout.fragment_new_post) {
    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private var _binding: FragmentNewPostBinding? = null
    private val binding: FragmentNewPostBinding get() = _binding!!

    //    private val viewModel: PostViewModel by activityViewModels()
    private val viewModel: PostViewModel by viewModels(::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)

        arguments?.textArg?.let(binding.editText::setText)
//        viewModel.edited.observe(viewLifecycleOwner) { post ->
//            binding.editText.setText(post.content)
//        }

//        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//
//        }

        binding.okFAB.setOnClickListener {
            viewModel.changePostContent(binding.editText.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
