package com.example.listdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listdemo.ui.theme.ListDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column {
        ColumnList()
    }
}

@Composable
fun RowList(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Row(
        modifier
            .horizontalScroll(scrollState)
    ) {
        repeat(50) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ColumnList(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(0)
                    }
                },
                modifier
                    .weight(0.5f)
                    .padding(8.dp)
            ) {
                Text(text = "Top")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                },
                modifier
                    .weight(0.5f)
                    .padding(8.dp)
            ) {
                Text(text = "End")
            }
        }
        RowList()
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier.verticalScroll(scrollState)
        ) {
            repeat(100) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    text = "List item $it",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ListDemoTheme {
        HomeScreen()
    }
}