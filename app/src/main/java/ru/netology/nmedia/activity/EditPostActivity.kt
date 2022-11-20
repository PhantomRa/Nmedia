package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditPostBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//      Как получить входную строку данных?
        val intentStr = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.editText.setText(intentStr)
        binding.editText.requestFocus()

        binding.okFAB.setOnClickListener {
            if (binding.editText.text.isNullOrBlank() || binding.editText.text.equals(intentStr)) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val intent = Intent()
                val content = binding.editText.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
