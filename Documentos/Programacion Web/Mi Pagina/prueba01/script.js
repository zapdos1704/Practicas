const buton = document.getElementById("hid")
const content = document.getElementById("contenido")
const flecha = document.getElementById('flech')

let ban = true

buton.addEventListener('click', function () {
    if (ban) {
        ban = false
        content.style.bottom = 0
        flecha.style.transform = 'rotate(180deg)'
    } else {
        ban = true
        content.style.bottom = "-180px"
        flecha.style.transform = 'rotate(0deg)'
    }
})


