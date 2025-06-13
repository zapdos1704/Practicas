const View = {
	tableBody: document.getElementById('personTableBody'),
	claveInput: document.getElementById('clave'),
	nombreInput: document.getElementById('nombre'),
	fechaNacInput: document.getElementById('fechaNac'),
	edadInput: document.getElementById('edad'),
	submitButton: document.getElementById('submitButton'),
	cancelButton: document.getElementById('cancelButton'),

	actualizarTabla(personas) {
		this.tableBody.innerHTML = '';
		personas.forEach((persona, index) => {
			const row = document.createElement('tr');
			row.innerHTML = `
                <td>${persona.clave}</td>
                <td>${persona.nombre}</td>
                <td>${persona.fechaNac}</td>
                <td>${persona.edad}</td>
                <td>
                    <button onclick="Controller.eliminarPersona(${index})">Eliminar</button>
                    <button onclick="Controller.modificarPersona(${index})">Modificar</button>
                </td>
            `;
			this.tableBody.appendChild(row);
		});
	},

	obtenerDatosFormulario() {
		const clave = this.claveInput.value.trim();
		const nombre = this.nombreInput.value.trim();
		const fechaNac = this.fechaNacInput.value.trim();
		const edad = this.edadInput.value.trim();

		// Validar campos vacíos
		if (!clave || !nombre || !fechaNac || !edad) {
			alert("Por favor, completa todos los campos.");
			return null;
		}

		return {
			clave,
			nombre,
			fechaNac,
			edad: parseInt(edad)
		};
	},

	resetearFormulario() {
		this.claveInput.value = '';
		this.nombreInput.value = '';
		this.fechaNacInput.value = '';
		this.edadInput.value = '';
		this.submitButton.textContent = 'Agregar Persona';
		this.cancelButton.style.display = 'none';
	},

	configurarFormularioParaEdicion() {
		this.submitButton.textContent = 'Guardar Cambios';
		this.cancelButton.style.display = 'inline';
	}
};
