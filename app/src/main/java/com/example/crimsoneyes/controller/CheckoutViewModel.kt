package com.example.crimsoneyes.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crimsoneyes.model.ItemCarrito
import com.example.crimsoneyes.model.VentaResponse
import com.example.crimsoneyes.repository.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CheckoutUiState(
    val compraExitosa: VentaResponse? = null,
    val error: String? = null,
    val cargando: Boolean = false,
    val total: Double = 0.0,
    val itemsCarrito: List<ItemCarrito> = emptyList(),
    val qrData: String? = null  // URL o datos del QR
)

class CheckoutViewModel(
    private val ventaRepository: VentaRepository,
    val usuarioEmail: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    // ============= MÉTODOS =============

    fun setItemsCarrito(items: List<ItemCarrito>) {
        _uiState.value = _uiState.value.copy(itemsCarrito = items)
        calcularTotal()
    }

    /**
     * 🛍️ PROCESAR COMPRA
     * Ejemplo: procesarCompra("TARJETA")
     */
    fun procesarCompra(metodoPago: String) {
        val items = _uiState.value.itemsCarrito

        // Validar
        if (items.isEmpty()) {
            _uiState.value = _uiState.value.copy(error = "❌ El carrito está vacío")
            return
        }

        if (metodoPago.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "❌ Selecciona un método de pago")
            return
        }

        // Procesar en background
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(cargando = true, error = null)

            val resultado = ventaRepository.procesarCompra(
                email = usuarioEmail,
                metodoPago = metodoPago,
                itemsCarrito = items
            )

            resultado.onSuccess { venta ->
                // Generar datos para el QR (URL con el ID de la venta)
                val qrData = "https://crimsoneyes.com/venta/${venta.id}?email=${usuarioEmail}"
                _uiState.value = _uiState.value.copy(
                    compraExitosa = venta,
                    total = venta.total,
                    cargando = false,
                    qrData = qrData
                )
            }

            resultado.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    error = exception.message ?: "❌ Error desconocido",
                    cargando = false
                )
            }
        }
    }

    fun obtenerDetallesVenta(ventaId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(cargando = true, error = null)

            val resultado = ventaRepository.obtenerVenta(ventaId)

            resultado.onSuccess { venta ->
                val qrData = "https://crimsoneyes.com/venta/${venta.id}?email=${usuarioEmail}"
                _uiState.value = _uiState.value.copy(
                    compraExitosa = venta,
                    cargando = false,
                    qrData = qrData
                )
            }

            resultado.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    error = "❌ ${exception.message}",
                    cargando = false
                )
            }
        }
    }

    // ============= PRIVADO =============

    private fun calcularTotal() {
        val items = _uiState.value.itemsCarrito
        val total = items.sumOf {
            (it.cantidad * it.producto.precio).toDouble()
        }
        _uiState.value = _uiState.value.copy(total = total)
    }

    fun resetear() {
        _uiState.value = CheckoutUiState()
    }

    fun limpiarError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

