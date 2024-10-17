<h1 align=center> Ejercicios POO con Kotlin ☑️ </h1>


**Main**

```csharp,

val person: Person = Person("Juan José", 20);
    val rectangulo: Rectangle = Rectangle(13.4, 3.4);
    val perro: Perro = Perro("Miguel");
    val vehiculo: Coche = Coche("Toyota", "Supra", 2);
    val estudiante: Stundent = Stundent(7.4, "Juan Jose", 20)

override fun onStart() {
        super.onStart();
        Log.d("Person class", person.greeting());
        Log.d("Rectangle class - Área", rectangulo.calcularArea());
        Log.d("Rectangle class - Perímetro", rectangulo.calcularPerimetro());
        Log.d("Perro class - Sound", perro.hacerSonido());
        Log.d("Vehículo class - Information", vehiculo.informacionCoche());
        Log.d("Student class - Status", estudiante.studentStatus());
    }

```

**Class Person - Student**

```kt,

open class Person(val nombre: String,val  edad: Int) {
    fun greeting(): String {
        val textPresentation: String = "Hola, soy $nombre y tengo $edad años, un placer conocerlos.";
        return textPresentation;
    }
}

class Stundent(val promedio: Double, nombre: String, edad: Int) : Person(nombre, edad) {

    fun studentStatus(): String {
        if (promedio <= 6){
            return  "El estudiante $nombre ha reprobado";
        }

        return "El estudiante $nombre ha aprobado";
    }
}

```
