const Model = {
	personas: [],

	agregarPersona(persona) {
		if (!persona || !persona.clave || !persona.nombre || !persona.fechaNac || !persona.edad) {
			console.error("Intento de agregar registro vacío o incompleto:", persona);
			return;
		}
		this.personas.push(persona);
	},

	agregarPersonas(Apersonas) {
		this.personas = Apersonas;
	},

	actualizarPersona(index, persona) {
		this.personas[index] = persona;
	},

	eliminarPersona(index) {
		this.personas.splice(index, 1);
	},

	getPersonas() {
		return this.personas;
	}
};
