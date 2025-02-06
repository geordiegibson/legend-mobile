package legend.com

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform