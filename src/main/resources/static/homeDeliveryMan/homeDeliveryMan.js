document.addEventListener('DOMContentLoaded', () => {
    const btnPedidosHechos = document.getElementById('btn-pedidos-hechos');
    const btnPedidosPendientes = document.getElementById('btn-pedidos-pendientes');
    const btnPedidosAsignados = document.getElementById('btn-pedidos-asignados');

    btnPedidosHechos.addEventListener('click', () => {
        alert('Mostrando pedidos hechos');
    });

    btnPedidosPendientes.addEventListener('click', () => {
        alert('Mostrando pedidos pendientes');
    });

    btnPedidosAsignados.addEventListener('click', () => {
        alert('Mostrando pedidos asignados');
    });
});
