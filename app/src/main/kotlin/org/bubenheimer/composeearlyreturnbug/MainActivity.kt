package org.bubenheimer.composeearlyreturnbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { Foo() }
    }
}

@Composable
fun Foo() {
    getOrElse { return }
    getOrElse { return }
}

@Composable
inline fun getOrElse(block: () -> Boolean): Boolean {
    val valid = produceState(Bar(false)) { value = Bar(true) }.value.data
    return if (valid) true else block()
}

class Bar(val data: Boolean)
