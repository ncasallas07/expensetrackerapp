# Expense Tracker

Aplicación móvil Android para registrar y controlar finanzas personales. Permite llevar ingresos y gastos por categoría y moneda, adjuntar la foto de una factura a cada movimiento y ver un resumen del balance con una gráfica de barras.

## Funcionalidades

- Registro de movimientos (gasto o ingreso) con título, monto, categoría y moneda (COP, USD, EUR)
- Categorías: Comida, Transporte, Servicios, Ocio y Otro
- Adjuntar una imagen de la factura desde la galería
- Lista de movimientos; mantener presionado un elemento lo elimina
- Pantalla de estadísticas con total de ingresos, gastos, balance y gráfica de barras personalizada
- Almacenamiento local persistente con Room (SQLite)

## Tecnologías

- Kotlin
- Arquitectura MVVM (ViewModel + Repository)
- Room para persistencia local
- Kotlin Coroutines y Flow
- View Binding, RecyclerView y Material Components
- Vista personalizada (Canvas) para la gráfica de estadísticas

## Estructura

```
app/src/main/java/com/nelson/expensetracker/
├── data/   # Entidad Expense, DAO, base de datos Room y repositorio
└── ui/     # Activities, ViewModel, adaptador de la lista y gráfica
```

## Cómo ejecutarlo

1. Clona el repositorio: `git clone https://github.com/ncasallas07/expensetrackerapp.git`
2. Ábrelo en Android Studio (versión reciente, con JDK 17).
3. Espera a que Gradle sincronice las dependencias. Android Studio descarga Gradle 8.7 automáticamente según `gradle/wrapper/gradle-wrapper.properties`.
4. Ejecuta la app en un emulador o dispositivo con Android 7.0 (API 24) o superior.

## Autor

Nelson Casallas · [LinkedIn](https://www.linkedin.com/in/ncasallas07) · [GitHub](https://github.com/ncasallas07)
