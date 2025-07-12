import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerializationException
import legend.com.networking.Competition
import legend.com.networking.Fixture
import legend.com.networking.Fixtures
import legend.com.networking.TeamStanding
import legend.com.util.NetworkError
import legend.com.util.Result
import kotlin.time.Duration.Companion.days

class SportyClient(
    private val httpClient: HttpClient
) {

    private val crlPremiershipId = "464560";
    private val phaseId = "22164";

    suspend fun fetchLadder(): Result<List<TeamStanding>, NetworkError> {
        val response = try {
            httpClient.post("https://www.sporty.co.nz/api/v2/competition/widget/standings/Phase") {
                contentType(ContentType.Application.Json)
                setBody("""{"GradeId":${crlPremiershipId},"PhaseId":${phaseId}}""")
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val censoredText = response.body<List<Competition>>()
                Result.Success(censoredText[0].Standings)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    private fun getFormattedDateTimeWithWeekOffset(weekOffset: Int = 0): String {
        val timeZone = TimeZone.currentSystemDefault()
        val nowInstant = Clock.System.now()
        val offsetInstant = nowInstant + (weekOffset * 7).days
        val offsetDateTime = offsetInstant.toLocalDateTime(timeZone)

        return offsetDateTime.toString()
    }

    suspend fun fetchDraw(): Result<List<Fixture>, NetworkError> {

        val response = try {
            httpClient.post("https://www.sporty.co.nz/api/v2/competition/widget/fixture/Dates") {
                contentType(ContentType.Application.Json)
                setBody("""{"CompIds":[10329],"OrgIds":[17940],"GradeIds":[${crlPremiershipId}],"From":"${getFormattedDateTimeWithWeekOffset()}","To":"${getFormattedDateTimeWithWeekOffset(5)}"}""")
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val fixtures = response.body<Fixtures>()
                Result.Success(fixtures.Fixtures)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}