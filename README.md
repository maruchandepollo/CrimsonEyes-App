
# CrimsonEyes-App

Aplicación móvil para óptica: recetas, compras, productos y pagos mediante QR

## Desarrolladores

Proyecto desarrollado por:

* **Sergio Puebla (maruchandepollo)** — [GitHub](https://github.com/maruchandepollo)
* **Matías Bórquez (Anideout)** — [GitHub](https://github.com/Anideout)

---

## Descripción

**CrimsonEyes-App** es una aplicación móvil para Android desarrollada en **Kotlin**.
Está diseñada para modernizar los procesos de una óptica mediante funcionalidades que permiten:

* Generar recetas ópticas personalizadas.
* Explorar y comprar productos (gafas, lentes de contacto, accesorios).
* Administrar pedidos y visualizar historial.
* Registrar datos de pacientes/clientes.
* Facilitar el proceso de pago mediante **códigos QR generados automáticamente en cada compra**.

La app busca ofrecer una experiencia completa para usuarios y ópticas, conectando el proceso clínico (recetas) con el proceso comercial (compra y pago).

---

## Tecnologías utilizadas

* **Kotlin** — Lenguaje de programación.
* **Android Studio (Gradle Kotlin DSL)** — Entorno y sistema de construcción.
* **AndroidX / Jetpack Libraries** — Navegación, ciclo de vida, vistas, LiveData, etc.
* **ViewBinding** — Manejo seguro y eficiente de vistas.
* **QR Generation/Scanning Libraries** (por ejemplo: ZXing / MLKit) — Para generar y leer códigos QR en tiempo real.
* **XML Layouts** — Diseño de pantallas.

---

## Funcionalidades principales

### Gestión de recetas ópticas

* Crear recetas personalizadas.
* Guardar parámetros como graduación, cilindro, eje, prisma, etc.
* Visualizar y editar recetas guardadas.

### Catálogo y compra de productos

* Productos organizados por categorías.
* Vista detallada de cada producto.
* Carrito dinámico y proceso de compra completo.

### Pago mediante QR

CrimsonEyes-App incorpora un sistema de **generación automática de códigos QR** al finalizar la compra.
Esto permite:

* Realizar pagos rápidos en caja o mediante apps compatibles.
* Vincular el pedido generado con el código QR único.
* Agilizar el proceso de validación de compras.

Además, la app puede utilizar **lectura de QR** para verificar pedidos o recibir confirmación de pagos.

---

## Estructura del proyecto

```
app/
  ├─ src/main/java/...     # Código Kotlin (Activities, Fragments, ViewModels)
  ├─ src/main/res/...      # Layouts, drawables, icons, strings, styles
  ├─ AndroidManifest.xml    # Declaración de componentes y permisos
gradle/                     # Configuración de Gradle
build.gradle.kts            # Dependencias y configuraciones
settings.gradle.kts         # Configuración raíz
```

---

## Instalación y ejecución

```bash
# 1. Clonar el repositorio
git clone https://github.com/maruchandepollo/CrimsonEyes-App.git

# 2. Abrir en Android Studio:
File -> Open -> Seleccionar carpeta del proyecto

# 3. Esperar sincronización de Gradle

# 4. Ejecutar la aplicación
Run ▶ sobre un dispositivo físico o emulador
```

---
