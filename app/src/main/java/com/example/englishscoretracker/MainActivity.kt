package com.example.englishscoretracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.englishscoretracker.ui.theme.EnglishScoreTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishScoreTracker()
            EnglishScoreTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EnglishScoreTracker()
                }
            }
        }
    }
}

@Composable
fun EnglishScoreTracker() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "confirm",
            Modifier.padding(innerPadding)
        ) {
            composable("confirm") { ConfirmScreen() }
            composable("record") { RecordScreen() }
            composable("setting") { SettingScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Check, contentDescription = "Confirm") },
            label = { Text("Confirm") },
            selected = currentDestination?.route == "confirm",
            onClick = {
                navController.navigate("confirm") {
//                    popUpTo("confirm") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Edit, contentDescription = "Record") },
            label = { Text("Record") },
            selected = currentDestination?.route == "record",
            onClick = {
                navController.navigate("record") {
//                    popUpTo("record") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = currentDestination?.route == "setting",
            onClick = {
                navController.navigate("setting") {
//                    popUpTo("setting") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}