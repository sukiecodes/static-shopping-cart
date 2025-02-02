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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staticshoppingcart.ui.theme.StaticShoppingCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StaticShoppingCartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingCart(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingCart(modifier: Modifier = Modifier) {
    val imageSize = modifier.size(100.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Cart",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = modifier.wrapContentSize(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                // changes the background color of card from default grey to white
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(24.dp)
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

        }

        Card(
            modifier = modifier.wrapContentSize(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
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
        }

        Card(
            modifier = modifier.wrapContentSize(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    StaticShoppingCartTheme {
        ShoppingCart()
    }
}