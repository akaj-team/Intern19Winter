package asiantech.internship.summer.recyclerview

data class TimelineItem(
        var name: String,
        var image: Int,
        var description: String,
        var like: Int,
        var isLike: Boolean = false,
        var type: Int = 0)