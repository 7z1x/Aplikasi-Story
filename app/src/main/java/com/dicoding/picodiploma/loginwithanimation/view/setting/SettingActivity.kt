package com.dicoding.picodiploma.loginwithanimation.view.setting

import android.app.LocaleManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivitySettingBinding
import com.dicoding.picodiploma.loginwithanimation.view.utils.ViewModelFactory

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val settingViewModel: SettingViewModel by viewModels {
            factory
        }

        val language: Array<String> = resources.getStringArray(R.array.language_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, language)
        settingViewModel.getLocale().observe(this) {
            when (it) {
                "jv" -> {
                    binding.spLanguage.setSelection(arrayAdapter.getPosition(language[2]))
                }
                "in" -> {
                    binding.spLanguage.setSelection(arrayAdapter.getPosition(language[1]))
                }
                else -> {
                    binding.spLanguage.setSelection(arrayAdapter.getPosition(language[0]))
                }
            }
        }

        binding.spLanguage.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (parent?.getItemAtPosition(position).toString() == language[2]) {
                        setLocale("jv", settingViewModel)
                    } else if(parent?.getItemAtPosition(position).toString() == language[1]) {
                        setLocale("in", settingViewModel)
                    } else {
                        setLocale("en", settingViewModel)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            adapter = arrayAdapter
        }
    }

    private fun setLocale(localeCode: String, settingViewModel: SettingViewModel) {
        settingViewModel.saveLocale(localeCode)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(localeCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(localeCode))
        }
    }
}