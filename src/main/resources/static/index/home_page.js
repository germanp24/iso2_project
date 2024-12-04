function buscarDireccion() {
    var direccion = document.getElementById('direccion').value;
    if (direccion) {
        alert('Buscando cerca de: ' + direccion); // Aquí puedes realizar la acción deseada.
        document.getElementById('direccion').value = ''; // Reinicia el campo de texto
    }
}
