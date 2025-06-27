package legend.com.DAL

import legend.com.model.DrawModel
import kotlin.random.Random

class DrawRepositoryImpl : DrawRepository {
    override suspend fun getDrawData(): List<List<DrawModel>> {
        val groupedModels = mutableListOf<List<DrawModel>>()
        val icons = listOf("burger", "house", "phone");

        for (groupIndex in 1..10) {
            val group = mutableListOf<DrawModel>()
            for (i in 1..10) {
                group.add(
                    DrawModel(
                        id = i,
                        icons[Random.nextInt(0, 2)],
                        icons[Random.nextInt(0, 2)],
                        time = "$groupIndex:30", // time is just the index now
                        venue = "Wingham Park"
                    )
                )
            }
            groupedModels.add(group)
        }

        return groupedModels
    }
}