package legend.com

data class Team(val id: Int, val name: String, val colour: Long, val logo: String)

val teamList = listOf(
    Team(350577, "Greymouth Greyhounds", 0xFFD32F2F, "greymouth.png"),
    Team(345054, "Halswell Hornets", 0x00000000, "hornets.png"),
    Team(343369, "Hornby Panthers", 0xFFD32F2F, "hornets.png"),
    Team(340750, "Eastern Eagles", 0xFFD32F2F, "hornets.png"),
    Team(340816, "Riccarton Knights", 0xFFD32F2F, "hornets.png"),
    Team(341243, "Linwood Keas", 0xFFD32F2F, "hornets.png")
)

val teamMap: Map<Int, Team> = teamList.associateBy { it.id }

fun getTeamById(id: Int): Team {
    val team = teamMap[id] ?: throw Exception()
    return team
}
