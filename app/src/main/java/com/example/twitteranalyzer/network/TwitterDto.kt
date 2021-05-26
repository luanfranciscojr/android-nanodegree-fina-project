package com.example.twitteranalyzer.network

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class TwitterList(
    @Json(name = "statuses") val data: List<Twitter>

)

@JsonClass(generateAdapter = true)
data class Twitter(
    @Json(name = "id") val id: Long,
    @Json(name = "text") val text: String,
    @Json(name = "user") val user: User
)


@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "name") val name: String,
    val id: Long,
    @Json(name = "profile_image_url_https")
    val image: String
)

@Parcelize
@Entity(tableName = "twitter")
data class TwitterModel(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "userImage") val userImage: String?
) : Parcelable


fun TwitterList.asTwitterModelList(): List<TwitterModel> =
    data.map { v ->
        TwitterModel(
            id = v.id,
            text = v.text,
            userName = v.user.name,
            userImage = v.user.image
        )
    }
