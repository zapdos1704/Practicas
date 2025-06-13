document.addEventListener('DOMContentLoaded', function () {
    const hobbies = ['Programar', 'Dibujar', 'Tocar música', 'Hacer ejercicio'];
    const hobbiesList = document.getElementById('hobbies-list');

    // Agregar los hobbies a la lista
    hobbies.forEach(hobby => {
        const li = document.createElement('li');
        li.textContent = hobby;
        hobbiesList.appendChild(li);
    });

    // Mostrar más información al hacer clic en el botón
    const btnMoreInfo = document.getElementById('btn-more-info');
    const moreInfo = document.getElementById('more-info');

    btnMoreInfo.addEventListener('click', function () {
        if (moreInfo.style.display === 'none') {
            moreInfo.style.display = 'block';
            btnMoreInfo.textContent = 'Menos información';
        } else {
            moreInfo.style.display = 'none';
            btnMoreInfo.textContent = 'Más información';
        }
    });
});