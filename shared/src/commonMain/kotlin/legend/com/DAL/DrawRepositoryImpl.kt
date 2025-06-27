package legend.com.DAL

import legend.com.model.DrawModel

class DrawRepositoryImpl : DrawRepository {
    override suspend fun getDrawData(): List<List<DrawModel>> {
        val groupedModels = mutableListOf<List<DrawModel>>()

        for (groupIndex in 1..10) {
            val group = mutableListOf<DrawModel>()
            for (i in 1..10) {
                group.add(
                    DrawModel(
                        id = i,
                        "ic_launcher_foreground",
                        "ic_launcher_foreground",
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