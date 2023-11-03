package com.dicoding.picodiploma.loginwithanimation

import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem

object DataDummy {

    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val story = ListStoryItem(
                id = "story-NyGJQv7oYd59DC-U",
                name = "Zulfahmi M.Ardianto",
                description = "ini deskripsi ke-${i+1} untuk tes yaaa",
                photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1683035377773_BPKaDWeA.jpg",
                createdAt = "2023-05-02T13:49:37.774Z",
                lat = -7.7794882,
                lon = 110.3909943
            )
            items.add(story)
        }
        return items
    }
}