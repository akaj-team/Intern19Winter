package asiantech.internship.winter.recyclerview

data class TimeLineItem(
        val nickname: String,
        val imgTimeLine: Int,
        val imgAvatar: Int,
        var countLike: Int,
        var isLiked: Boolean
)