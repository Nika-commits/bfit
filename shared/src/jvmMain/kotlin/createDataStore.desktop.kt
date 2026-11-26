import androidx.datastore.core.DataStore
import androidx.datastore.core.FileStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesFileSerializer
import java.io.File

private const val APP_NAME = "bFit"

private enum class OS { Windows, MAC, LINUX }

private val currentOs: OS by lazy {
    val name = System.getProperty("os.name").lowercase()
    when {
        "win" in name -> OS.Windows
        "mac" in name || "darwin" in name -> OS.MAC
        else -> OS.LINUX
    }
}

private fun getOsSpecificDir(): File {
    val home = System.getProperty("user.home")
    val dir = when (currentOs) {
        OS.Windows -> File(System.getenv("APPDATA") ?: "$home\\AppData\\Roaming", APP_NAME)
        OS.MAC -> File(home, "Library/Application Support/$APP_NAME")
        OS.LINUX -> File(System.getenv("XDG_CONFIG_HOME") ?: "$home/.config", APP_NAME)
    }
    return dir.also { it.mkdirs() }
}

fun createDataStore(): DataStore<Preferences> {
    return createDataStore(
        storage = FileStorage(
            serializer = PreferencesFileSerializer,
            produceFile = {
                File(getOsSpecificDir(), dataStoreFileName)
            }
        )
    )
}
