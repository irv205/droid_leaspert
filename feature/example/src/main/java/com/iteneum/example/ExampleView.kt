package com.iteneum.example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.iteneum.designsystem.components.LpGenericCard


@Composable
fun ExampleView(){
    LpGenericCard(
        modifier = Modifier,
        title = "Current balance",
        details = "Go to payments",
        accountNumber = "0.00",
        currency = true,
        onTextClick = {}
    )
}

@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontWeight = FontWeight.Bold
        )
    }
}