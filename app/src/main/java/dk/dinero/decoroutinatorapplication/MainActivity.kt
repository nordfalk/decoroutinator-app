package dk.dinero.decoroutinatorapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
/* debug build output:
coroutines didnt crash
java.lang.Exception: coroutines stack trace
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck3(MainActivity.kt:64)
	at dk.dinero.decoroutinatorapplication.MainActivity.access$coroutineCheck3(MainActivity.kt:22)
	at dk.dinero.decoroutinatorapplication.MainActivity$coroutineCheck3$1.invokeSuspend(Unknown Source:14)
	at dev.reformator.stacktracedecoroutinator.common.internal.DecoroutinatorSpecImpl.resumeNext(utils-common.kt:126)
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck2(MainActivity.kt:56)
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck1(MainActivity.kt:49)
	at dk.dinero.decoroutinatorapplication.MainActivity$onResume$1$1.invokeSuspend(MainActivity.kt:42)
	at dev.reformator.stacktracedecoroutinator.common.internal.AwakenerKt.callSpecMethods(awakener.kt:80)
	at dev.reformator.stacktracedecoroutinator.common.internal.AwakenerKt.awake(awakener.kt:32)
	at dev.reformator.stacktracedecoroutinator.common.internal.Provider.awakeBaseContinuation(provider-impl.kt:38)
	at dev.reformator.stacktracedecoroutinator.provider.DecoroutinatorProviderApiKt.awakeBaseContinuation(provider-api.kt:48)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(Unknown Source:20)
	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
	at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
	at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
DecoroutinatorCommonApi.getStatus: DecoroutinatorStatus(successful=true, description=no issues detected)



production build output:
java.lang.Exception: coroutines stack trace
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck3(MainActivity.kt:64)
	at dk.dinero.decoroutinatorapplication.MainActivity.access$coroutineCheck3(MainActivity.kt:22)
	at dk.dinero.decoroutinatorapplication.MainActivity$coroutineCheck3$1.invokeSuspend(Unknown Source:14)
	at dev.reformator.stacktracedecoroutinator.common.internal.DecoroutinatorSpecImpl.resumeNext(utils-common.kt:126)
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck2(MainActivity.kt:56)
	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck1(MainActivity.kt:49)
	at dk.dinero.decoroutinatorapplication.MainActivity$onResume$1$1.invokeSuspend(MainActivity.kt:42)
	at dev.reformator.stacktracedecoroutinator.common.internal.AwakenerKt.callSpecMethods(awakener.kt:80)
	at dev.reformator.stacktracedecoroutinator.common.internal.AwakenerKt.awake(awakener.kt:32)
	at dev.reformator.stacktracedecoroutinator.common.internal.Provider.awakeBaseContinuation(provider-impl.kt:38)
	at dev.reformator.stacktracedecoroutinator.provider.DecoroutinatorProviderApiKt.awakeBaseContinuation(provider-api.kt:48)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(Unknown Source:20)
	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
	at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
	at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
DecoroutinatorCommonApi.getStatus: DecoroutinatorStatus(successful=true, description=no issues detected)
 */
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DecoroutinatorApplicationTheme {
        Greeting("Android")
    }
}