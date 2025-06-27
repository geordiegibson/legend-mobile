package legend.com.DAL

import legend.com.model.DrawModel

interface DrawRepository {
    suspend fun getDrawData(): List<List<DrawModel>>
}