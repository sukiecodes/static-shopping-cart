package com.example.staticshoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staticshoppingcart.ui.theme.StaticShoppingCartTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StaticShoppingCartTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    ShoppingCart(
                        modifier = Modifier
                            .padding(innerPadding),
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingCart(modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    val isOrdered = remember { mutableStateOf(false) } // keep track of whether ordered

    // if no checkout has happened yet and button is clicked, then order
    val snackbarMessage = if (!isOrdered.value) {
        "Ordered"
    } else {
        "Your order has been cancelled"
        // if it has already been ordered, then cancel the order if there is a double click
    }

    val imageSize = modifier.size(100.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
    ) { // cart of all items
        Text(
            text = "Your Cart",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedCard(
            // each item has its own outlined card
            modifier = modifier
                .wrapContentSize()
                .padding(2.dp)
            ,
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                // changes the background color of card from default grey to white
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.cookies),
                    contentDescription = null,
                    modifier = imageSize
                )

                Column {
                    Text(
                        text = "Cookies",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "Quantity: 1")
                    Text(text = "Price per unit: $2.00")
                }
            }

            HorizontalDivider(thickness = 1.dp)
            // next item below
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.bread),
                    contentDescription = null,
                    modifier = imageSize
                )

                Column {
                    Text(
                        text = "Bread",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "Quantity: 1")
                    Text(text = "Price per unit: $3.25")
                }
            }

            HorizontalDivider(thickness = 1.dp)
            // next item below
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.milk),
                    contentDescription = null,
                    modifier = imageSize
                )

                Column {
                    Text(
                        text = "Milk",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "Quantity: 2")
                    Text(text = "Price per unit: $2.50")
                }
            }
        }

        Text(
            text = "Summary",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Total cost: $10.25",
            fontSize = 20.sp
        )

        Button(onClick = {
            isOrdered.value = !isOrdered.value

            // used this instead of launched effect so that no message is shown immediately
            CoroutineScope(Dispatchers.Main).launch {
                snackbarHostState.showSnackbar(snackbarMessage)
            }
        }) {
            Text(text = "Checkout")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    val snackbarHostState = remember { SnackbarHostState() }
    StaticShoppingCartTheme {
        ShoppingCart(snackbarHostState = snackbarHostState)
    }
}