package tech.ru1t3rl.madlevel6task1

data class ColorItem (
    var hex: String,
    var name: String
) {
    fun getImageUrl() = "http://singlecolorimage.com/get/$hex/1080x1080"
}