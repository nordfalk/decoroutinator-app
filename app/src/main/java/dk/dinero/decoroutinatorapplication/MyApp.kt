package dk.dinero.decoroutinatorapplication

import android.app.Application
import dev.reformator.stacktracedecoroutinator.runtime.DecoroutinatorRuntime

class MyApp : Application() {
    init {
        DecoroutinatorRuntime.load()
    }
}
