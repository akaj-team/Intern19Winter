package asiantech.internship.summer.drawerlayout

import asiantech.internship.summer.R


data class MenuItem(val icon: Int, val title: String) {
    companion object {
        fun getItems(): List<MenuItem> {
            val menuItems = mutableListOf<MenuItem>()
            menuItems.add(MenuItem(R.drawable.inbox, "Inbox"))
            menuItems.add(MenuItem(R.drawable.outbox, "Outbox"))
            menuItems.add(MenuItem(R.drawable.trash, "Trash"))
            menuItems.add(MenuItem(R.drawable.spam, "Spam"))

            return menuItems
        }
    }
}
