document.addEventListener("DOMContentLoaded", function () {
    const sections = {
        gustos: document.querySelector(".gustos"),
        pasatiempos: document.querySelector(".pasatiempos"),
        trabajos: document.querySelector(".trabajos"),
        informacion: document.querySelector(".informacion"),
        musical: document.querySelector(".musica"),
        comida: document.querySelector(".comida")
    };

    const buttons = {
        gustosBtn: document.querySelector("#gustosBtn"),
        pasatiemposBtn: document.querySelector("#pasatiemposBtn"),
        trabajosBtn: document.querySelector("#trabajosBtn"),
        informacionBtn: document.querySelector("#informacionBtn"),
        musical: document.querySelector("#musical"),
        comida: document.querySelector("#comida")
    };


    function toggleSection(section) {
        Object.keys(sections).forEach((key) => {
            sections[key].classList.remove("active");
        });
        section.classList.add("active");
    }

    buttons.gustosBtn.addEventListener("click", function () {
        toggleSection(sections.gustos);
    });

    buttons.pasatiemposBtn.addEventListener("click", function () {
        toggleSection(sections.pasatiempos);
    });

    buttons.trabajosBtn.addEventListener("click", function () {
        toggleSection(sections.trabajos);
    });

    buttons.informacionBtn.addEventListener("click", function () {
        toggleSection(sections.informacion);
    });
    buttons.informacionBtn.addEventListener("click", function () {
        toggleSection(sections.informacion);
    });
});