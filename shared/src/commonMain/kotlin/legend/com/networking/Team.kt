package legend.com.networking

import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    val Standings: List<TeamStanding>,
)

@Serializable
data class TeamStanding(
    val TeamName: String,
    val Id: Int,
    val Total: Int
)

@Serializable
data class Fixtures(
    val Fixtures: List<Fixture>
)

@Serializable
data class Fixture(
    val AwayTeamId: Int,
    val HomeTeamId: Int,
    val VenueName: String
)