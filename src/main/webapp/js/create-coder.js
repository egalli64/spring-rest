/*
 * Spring Boot Web REST tutorial
 * 
 * https://github.com/egalli64/spring-rest
 */

/**
 * Associated to the click event on a button - See HTML
 */
function create() {
	let request = new XMLHttpRequest()
	const url = "/api/m1/s3/coders"
	const data = {
		firstName: document.getElementById("firstname").value,
		lastName: document.getElementById("lastname").value,
		hireDate: document.getElementById("hired").value,
		salary: parseInt(document.getElementById("salary").value)
	}
		
	request.onload = callback
	request.onerror = errorHandler
	request.open("POST", url)
	request.setRequestHeader("Content-Type", "application/json")

	request.send(JSON.stringify(data))
}

/**
 * Callback in case of error for the create() AJAX call
 */
function errorHandler() {
	let target = document.getElementById('target')
	target.value += "Something bad happened, please check browser log for more details\n"
	if (this.status == 0 && this.readyState == 4) {
		target.value += "Do you have a CORS problem?\n"
	}
}

/**
 * Callback in case of success (load) for the create() AJAX call
 */
function callback() {
	let target = document.getElementById('target');
	if (this.status != 200) {
		target.value += "[" + this.status + "]\n"
	} else {
		let json = JSON.parse(this.responseText);
		target.value += "Coder " + json.firstName + " " + json.lastName +
			" has been created with id " + json.id + "\n"
	}
}
