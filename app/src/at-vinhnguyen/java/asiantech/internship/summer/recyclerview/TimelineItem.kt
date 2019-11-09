package asiantech.internship.summer.recyclerview

data class TimelineItem(val profilePicture: Int,
                        val name: String,
                        val imageContent: Int,
                        var isLike: Boolean = false,
                        var likeCount: Int,
                        val description: String)