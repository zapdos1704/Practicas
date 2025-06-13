const Controller = {
	editIndex: null,
	csvFileInput: document.getElementById('csvFileInput'),

	// Inicialización
	init() {
		// Añade eventos a los botones
		document.getElementById('personForm').addEventListener('submit', this.guardarPersona.bind(this));
		document.getElementById('cancelButton').addEventListener('click', this.cancelarEdicion.bind(this));
		document.getElementById('saveCsvButton').addEventListener('click', this.guardarCSV.bind(this));
		document.getElementById('loadCsvButton').addEventListener('click', this.cargarDesdeCSV.bind(this));
	},

	guardarPersona(event) {
		event.preventDefault();

		// Obtener datos del formulario
		const persona = View.obtenerDatosFormulario();

		// Validar que no sea null (campos incompletos)
		if (!persona) {
			return; // Detener si hay campos vacíos
		}

		if (this.editIndex === null) {
			Model.agregarPersona(persona);
		} else {
			Model.actualizarPersona(this.editIndex, persona);
			this.editIndex = null;
		}

		View.resetearFormulario();
		this.actualizarVista();
	},


	eliminarPersona(index) {
		Model.eliminarPersona(index);
		this.actualizarVista();
	},

	modificarPersona(index) {
		const persona = Model.getPersonas()[index];
		View.claveInput.value = persona.clave;
		View.nombreInput.value = persona.nombre;
		View.fechaNacInput.value = persona.fechaNac;
		View.edadInput.value = persona.edad;
		this.editIndex = index;
		View.configurarFormularioParaEdicion();
	},

	cancelarEdicion() {
		this.editIndex = null;
		View.resetearFormulario();
	},

	actualizarVista() {
		View.actualizarTabla(Model.getPersonas());
	},

	guardarCSV() {
		const personas = Model.getPersonas();
		if (personas.length === 0) {
			alert("No hay registros para guardar.");
			return;
		}

		let csvContent = "Clave,Nombre,Fecha de Nacimiento,Edad\n";
		personas.forEach(persona => {
			csvContent += `${persona.clave},${persona.nombre},${persona.fechaNac},${persona.edad}\n`;
		});

		const blob = new Blob([csvContent], { type: 'text/csv' });
		const url = URL.createObjectURL(blob);
		const a = document.createElement('a');
		a.href = url;
		a.download = 'personas.csv';
		a.click();
		URL.revokeObjectURL(url);
	},

	cargarDesdeCSV() {
		const file = this.csvFileInput.files[0];
		if (!file) {
			alert("Por favor selecciona un archivo CSV.");
			return;
		}

		const reader = new FileReader();
		reader.onload = (event) => {
			const rows = event.target.result.split("\n").slice(1); // Ignorar la primera fila
			const registrosCsv = rows.map(row => {
				const columns = row.split(",");
				if (columns.length === 4) {
					return {
						clave: columns[0].trim(),
						nombre: columns[1].trim(),
						fechaNac: columns[2].trim(),
						edad: parseInt(columns[3].trim())
					};
				}
				return undefined;
			}).filter(persona => persona !== undefined);

			Model.agregarPersonas(registrosCsv);
			this.actualizarVista();
		};

		reader.readAsText(file);
	}
};

Controller.init();
