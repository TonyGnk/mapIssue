package com.tonygnk.mapissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tonygnk.mapissue.ui.theme.MapIssueTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapIssueTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable(
                        route = "home"
                    ) {
                        Scaffold {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("mapsLibre")
                                    }
                                ) {
                                    Text(
                                        text = "Maps Libre",
                                    )
                                }
                                Button(
                                    onClick = {
                                        navController.navigate("ramaniMaps")
                                    }
                                ) {
                                    Text(
                                        text = "Ramani Maps",
                                    )
                                }
                                Button(
                                    onClick = {
                                        navController.navigate("otherScreen")
                                    }
                                ) {
                                    Text(
                                        text = "Other Screen",
                                    )
                                }
                            }
                        }
                    }
                    composable(
                        route = "mapsLibre"
                    ) {
                        MapsLibre()
                    }
                    composable(
                        route = "ramaniMaps"
                    ) {
                        RamaniMaps()
                    }

                    composable(
                        route = "otherScreen"
                    ) {
                        Box(modifier = Modifier.background(Color.Cyan))
                    }
                }
            }
        }
    }
}
