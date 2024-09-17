package dk.dinero.decoroutinatorapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.reformator.stacktracedecoroutinator.common.DecoroutinatorCommonApi
import dk.dinero.decoroutinatorapplication.ui.theme.DecoroutinatorApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecoroutinatorApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            CoroutineScope(Dispatchers.IO).launch {
                coroutineCheck1()
            }
        }, 100)
    }

    private suspend fun coroutineCheck1() {
        delay(100)
        coroutineCheck2()
        delay(100)
    }


    private suspend fun coroutineCheck2() {
        delay(100)
        coroutineCheck3()
        delay(100)
    }


    private suspend fun coroutineCheck3() {
        delay(100)
        Log.i("decoroutinator", "coroutines didnt crash")
        Log.w("decoroutinator", Exception("coroutines stack trace"))
        val status = DecoroutinatorCommonApi.getStatus { it() }
        Log.i("decoroutinator", "DecoroutinatorCommonApi status: $status")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val running = remember { mutableStateOf(false) }

    Text(
        text = "Hello $name! "+running.value,
        modifier = modifier.clickable {
            runBg(coroutineScope, running) {
                Log.i("decoroutinator", "runBg coroutine started")
                delay(1000)
                Log.i("decoroutinator", "runBg coroutine finished")
            }
            Log.i("decoroutinator", "clickable text clicked")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DecoroutinatorApplicationTheme {
        Greeting("Android")
    }
}


fun runBg(
    coroutineScope: CoroutineScope,
    running: MutableState<Boolean>? = null,
    function: suspend () -> Unit
) {
    if (running != null) running.value = true
    coroutineScope.launch {
        try {
            function()
        } catch (e: Exception) {
            Log.e("decoroutinator", "runBg error", e)
        } finally {
            if (running != null) running.value = false
        }
    }
}

@Composable
fun RunBgLaunchedEffect(
    key1: Any?,
    loading: MutableState<Boolean>? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    LaunchedEffect(key1) {
        try {
            block()
        } catch (e: Exception) {
            Log.e("decoroutinator", "RunBgLaunchedEffect error", e)
        } finally {
            loading?.value = false
        }
    }
}
