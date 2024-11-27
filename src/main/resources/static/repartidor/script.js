// Referencias a los campos del formulario
const nif = document.getElementById('NIF');
const nombre = document.getElementById('Nombre');
const apellidos = document.getElementById('Apellidos');
const email = document.getElementById('Email');
const password = document.getElementById('Password');
const tipoAutoHidden = document.getElementById('tipoAuto');
const botonesTipoAuto = document.querySelectorAll('.tipo-auto');
const registrarseBtn = document.getElementById('registrarse');

// Función para verificar si todos los campos están llenos
function validarCampos() {
    return (
        nif.value.trim() !== '' &&
        nombre.value.trim() !== '' &&
        apellidos.value.trim() !== '' &&
        email.value.trim() !== '' &&
        password.value.trim() !== '' &&
        tipoAutoHidden.value.trim() !== '' // Verificar que se haya seleccionado un tipo de vehículo
    );
}

// Función para actualizar el estado del botón de registro
function actualizarEstadoBoton() {
    registrarseBtn.disabled = !validarCampos(); // Habilita o deshabilita el botón
}

// Evento para seleccionar el tipo de vehículo
botonesTipoAuto.forEach((boton) => {
    boton.addEventListener('click', () => {
        // Actualizar el valor del campo oculto
        tipoAutoHidden.value = boton.getAttribute('data-tipo');

        // Resaltar el botón seleccionado
        botonesTipoAuto.forEach((btn) => btn.classList.remove('activo'));
        boton.classList.add('activo');

        // Verificar y actualizar el estado del botón de registro
        actualizarEstadoBoton();
    });
});

// Evento para validar los campos en tiempo real
document.querySelector('form').addEventListener('input', () => {
    actualizarEstadoBoton(); // Validar los campos y actualizar el botón
});

// Inicialización: Desactivar el botón al cargar la página
actualizarEstadoBoton();
