// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.artgallery

// Import library yang akan digunakan
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikanurfitriani.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Blok untuk menentukan tata letak aktivitas tempat fungsi composable
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Untuk mengisi Surface dengan ukuran maksimum yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil fungsi utama yaitu ArtGalleryApp dari aplikasi
                    ArtGalleryApp()
                }
            }
        }
    }
}

// Untuk membuat kelas baru yaitu ArtPiece
data class ArtPiece(
    val resourceId: Int, val name: String, val description: String, val year: String
)

@Composable
// Blok fungsi ArtGalleryApp untuk menampilkan semua bagian halaman
fun ArtGalleryApp() {
    // Deklarasi variabel artPieces yang berisi daftar karya seni.
    val artPieces = listOf(
        // Setiap karya seni memiliki berbagai properti seperti gambar, judul, deskripsi, dan tahun pembuatan
        ArtPiece(
            R.drawable.karya_seni_1,
            "Diponegoro Memimpin Pertempuran",
            "Karya Seni oleh Basuki Abdullah",
            "1960"
        ),
        ArtPiece(
            R.drawable.karya_seni_2,
            "Dr. Ir. Soekarno Presiden Republik Indonesia",
            "Karya Seni oleh Basuki Abdullah",
            "1964"
        ),
        ArtPiece(
            R.drawable.karya_seni_3,
            "Ibuku",
            "Karya Seni oleh Affandi",
            "1941"
        ),
        ArtPiece(
            R.drawable.karya_seni_4,
            "Café Terrace at Night",
            "Karya Seni oleh Vincent van Gogh",
            "1888"
        ),
        ArtPiece(
            R.drawable.karya_seni_5,
            "Le Moulin de Blute",
            "Karya Seni oleh Vincent van Gogh",
            "1886"
        )
    )

    // Untuk melacak indeks karya seni yang sedang ditampilkan
    var currentArtPieceIndex by remember { mutableStateOf(0) }

    // Untuk mengatur elemen-elemen anak secara vertikal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        // Menyusun konten secara vertikal di tengah
        verticalArrangement = Arrangement.Center,
        // Menyusun konten secara horizontal di tengah
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Bagian atas artgallery menggunakan Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(5.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Untuk mengatur dan menampilkan text
            Text(
                text = "ART GALLERY",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Menambahkan ruang kosong sebesar 48 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(48.dp))

        //untuk menampilkan gambar karya seni
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colorScheme.primary)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        ) {
            // Untuk menampilkan gambar/foto
            Image(
                painter = painterResource(id = artPieces[currentArtPieceIndex].resourceId),
                contentDescription = artPieces[currentArtPieceIndex].name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Menambahkan ruang kosong sebesar 8 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(8.dp))

        // Teks untuk menampilkan judul karya seni
        Text(
            text = artPieces[currentArtPieceIndex].name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )

        // Menambahkan ruang kosong sebesar 8 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(8.dp))

        // Teks untuk menampilkan deskripsi karya seni
        Text(
            text = artPieces[currentArtPieceIndex].description,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp
        )

        // Menambahkan ruang kosong sebesar 8 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(8.dp))

        // Teks untuk menampilkan tahun karya seni
        Text(
            text = "Tahun: ${artPieces[currentArtPieceIndex].year}",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp
        )

        // Menambahkan ruang kosong sebesar 16 dp diantara elemen-elemen tata letak
        Spacer(modifier = Modifier.height(16.dp))

        // Pembuatan baris yang berisi dua tombol navigasi, yaitu "Sebelumnya" dan "Selanjutnya"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentArtPieceIndex > 0) {
                        currentArtPieceIndex--
                    }
                },
                enabled = currentArtPieceIndex > 0
            ) {
                Text(text = "Sebelumnya")
            }

            Button(
                onClick = {
                    if (currentArtPieceIndex < artPieces.size - 1) {
                        currentArtPieceIndex++
                    }
                },
                enabled = currentArtPieceIndex < artPieces.size - 1
            ) {
                Text(text = "Selanjutnya")
            }
        }
    }
}

// Menampilkan pratinjau aplikasi art gallery dengan tema terang
@Preview(showBackground = true)
// Menampilkan pratinjau aplikasi art gallery dengan tema gelap
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name = "Dark Mode"
)
// Fungsi composable untuk menampilkan pratinjau halaman fungsi ArtGalleryApp
@Composable
fun BusinessCardPreview() {
    ArtGalleryTheme {
        ArtGalleryApp()
    }
}