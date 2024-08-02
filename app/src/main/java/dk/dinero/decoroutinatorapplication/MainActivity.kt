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
    }
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