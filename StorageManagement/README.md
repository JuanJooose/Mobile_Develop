<h1 align="center">Almacenamiento con sharedPreferences e interno 🧏 </h1>

<h2>Proyecto Almacenamiento de Inventario</h2>

*Juan Jose Gil Arboleda*
*Marcos Duque Jaramillo*
*Miguel Angel Cadena Rojas*


**Main**
```kotlin,
class MainActivity : ComponentActivity() {
    final lateinit var bindings: ViewBinding
    val utilities: Utilities = Utilities();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
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

    override fun onStart() {
        super.onStart()

        val productTitle: String? = utilities.getDataBySharedPref(this, "title");
        val productDescription: String? = utilities.getDataBySharedPref(this, "description");
        val productReviewed: String? = utilities.getDataBySharedPref(this, "dateReviewed");

        utilities.saveDataInSharedPref(this);

        Log.d("Titulo", "$productTitle\n")
        Log.d("Descripción", "$productDescription\n")
        Log.d("Producto reviewed", "EL producto fué visto por última vez el $productReviewed\n")


        utilities.createFileIfNotExists(this)
        utilities.saveDataInFile(this);

        val contentByFile = utilities.getDataByFile(this);

        Log.d("Datos", "Contenido del archivo $contentByFile\n")
    }
}
```

*Pruebas*
![image](https://github.com/user-attachments/assets/f85edf80-bb4c-47b0-8287-995bdbc3f777)


**Clase Utilities**

```kotlin,
val date: Date = Date();
    private val product: Product = Product();

    fun saveDataInSharedPref(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences("MyPrefsProducts", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        product.fetchUsers(
            onSuccess = { product ->
                println("Productos obtenidos: $product")
                // Asegúrate de que 'products' es un objeto que contiene las propiedades title y description
                editor.putString("title", product.title)
                editor.putString("description", product.description)
                editor.putString("dateReviewed", date.toString()) // Guarda la fecha actual

                // Aplica los cambios en SharedPreferences
                editor.apply()
            },
            onError = { e ->
                Log.d("Error", "Error al obtener productos: ${e.message}")
            }
        )
    }

    fun getDataBySharedPref(context: Context, key: String): String? {
        val sharedPreferences =
            context.getSharedPreferences("MyPrefsProducts", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "No existe clave con este nombre");
    }


    fun saveDataInFile(context: Context) {
        val filename = "storage.txt";

        product.fetchUsers(
            onSuccess = { product ->
                context.openFileOutput(filename, Context.MODE_PRIVATE)
                    .use { outputStream ->
                        outputStream.write(
                            product.title.toByteArray()
                        )
                    }
            },
            onError = { e ->
                Log.d("Error", "Error al obtener productos: ${e.message}")
            }
        )
    }


    fun getDataByFile(context: Context): String {
        val filename = "storage.txt";

        return context.openFileInput(filename).bufferedReader()
            .useLines { line -> line.fold("") { some, text -> "$some\n\n\n$text\n\n" } }
    }



    fun createFileIfNotExists(context: Context) {
        val filename = "storage.txt"
        val file = context.getFileStreamPath(filename)
        if (!file.exists()) {
            context.openFileOutput(filename, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write("".toByteArray())  // Crea el archivo vacío
            }
        }
    }

```
