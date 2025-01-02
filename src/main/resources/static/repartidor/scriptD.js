const dni = document.getElementById('DNI');
const name = document.getElementById('Name');
const surname_m = document.getElementById('Surnames_M');
const surname_f =document.getElementById('Surnames_F');
const phone =document.getElementById('Phone');
const email = document.getElementById('Email');
const password = document.getElementById('Password');
const tipoAutoHidden = document.getElementById('tipoAuto');
const botonesTipoAuto = document.querySelectorAll('.tipo-auto');
const registrarseBtn = document.getElementById('registrarse');

// Función para verificar si todos los campos están llenos
function validarCampos() {
    return (
        dni.value.trim() !== '' &&
        name.value.trim() !== '' &&
        surname_f.value.trim() !== '' &&
        surname_m.value.trim() !== '' &&
        phone.value.trim() !== '' &&
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
