package com.shaiful.shaitask.modules.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaiful.shaitask.ui.theme.BabyBlue
import com.shaiful.shaitask.ui.theme.LightGreen
import com.shaiful.shaitask.ui.theme.RedOrange
import com.shaiful.shaitask.ui.theme.RedPink
import com.shaiful.shaitask.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {

    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
