package com.hussein.startup

import android.content.Context
import androidx.room.*
import androidx.room.Room




@Entity
data class Notes(
        @PrimaryKey(autoGenerate = true)  val ID: Int,
        @ColumnInfo(name = "Title") val Title: String?,
        @ColumnInfo(name = "Description") val Description: String?
)

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes WHERE Title like :Title")
    fun loadByTitle(Title: String): List<Notes>

    @Insert
    fun insert(vararg notes: Notes)

    @Delete
    fun delete(note: Notes)

    @Query("update Notes set Title= :Title,Description= :Description  WHERE ID = :ID")
    fun update(Title: String, Description: String,ID: Int)
}

@Database(entities = arrayOf(Notes::class), version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao


}

class  DBManager {

    @Volatile
    private var INSTANCE: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase? {
        if (INSTANCE == null) {
            synchronized(NotesDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NotesDatabase::class.java!!, "MyNotes").allowMainThreadQueries()
                            .build()
                }
            }
        }
        return INSTANCE
    }
}

