package com.net.taipeizoo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.R
import com.net.taipeizoo.model.ContentItem
import com.net.taipeizoo.model.ZooPlant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZooPlantDetailFragmentViewModel : ViewModel() {

    private val _contentItems = MutableLiveData<List<ContentItem>>()
    val contentItems: LiveData<List<ContentItem>> = _contentItems

    fun toContentItems(context: Context, plant: ZooPlant) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                listOf(
                    genContentItem(plant.nameEn, plant.nameLatin),
                    genContentItem(context.getString(R.string.alias_name), plant.aliasName),
                    genContentItem(context.getString(R.string.brief), plant.brief),
                    genContentItem(context.getString(R.string.feature), plant.info),
                    genContentItem(context.getString(R.string.function), plant.function)
                )
            }.let { contentItems ->
                _contentItems.postValue(contentItems)
            }
        }
    }

    private fun genContentItem(title: String?, content: String?): ContentItem {
        return ContentItem(title ?: "", content ?: "")
    }
}