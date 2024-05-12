//IT22364760-MADHUWARSHA
package com.muffar.remindtask.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query  //implement room library
import androidx.room.Upsert
import com.muffar.remindtask.data.local.db.entity.TaskEntity
import com.muffar.remindtask.domain.model.StatusType
import kotlinx.coroutines.flow.Flow //Implement cororutines
import java.util.UUID

@Dao
interface TaskDao {

    @Upsert
    suspend fun save(taskEntity: TaskEntity)

    @Query("SELECT * FROM task ORDER BY deadline ASC")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getById(id: UUID): TaskEntity?

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteById(id: UUID)

    @Query("UPDATE task SET status = :status WHERE id = :id")
    suspend fun checkTask(id: UUID, status: StatusType)
}