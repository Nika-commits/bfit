import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Storage
import androidx.datastore.preferences.core.Preferences

fun createDataStore(storage: Storage<Preferences>): DataStore<Preferences> {
    return DataStoreFactory.create(storage = storage)
}


internal const val dataStoreFileName = "bfit.preferences_pb"

