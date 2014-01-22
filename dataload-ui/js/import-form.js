/**
 * @author Ben Outram
 */
function ImportForm(form, service) {
	this.form = form;
	this.service = service;
}

ImportForm.prototype.init = function(filenames) {
	var this_ = this;
	_.each(filenames, function(filename, index, list) {
		var id = 'filename-' + filename;
		var input = document.createElement('input');
		input.setAttribute('id', id);
		input.setAttribute('type', 'radio');
		input.setAttribute('name', 'filename');
		input.setAttribute('value', filename);
		if (index === 0) {
			input.setAttribute('checked', '');
		}

		var label = document.createElement('label');
		label.setAttribute('for', id);
		label.setAttribute('class', 'pure-radio');
		label.appendChild(input);
		label.insertAdjacentHTML('beforeend', ' ' + filename);
		
		this_.form.appendChild(label);
	});

	this.form.appendChild(this.serviceField(this.service));
	this.form.appendChild(this.submitButton());
}

ImportForm.prototype.serviceField = function(service) {
	var serviceField = document.createElement('input');
	serviceField.setAttribute('type', 'hidden');
	serviceField.setAttribute('name', 'service');
	serviceField.setAttribute('value', service);
	return serviceField;
}

ImportForm.prototype.submitButton = function() {
	var submitButton = document.createElement('button');
	submitButton.setAttribute('type', 'submit');
	submitButton.setAttribute('class', 'pure-button pure-button-primary');
	submitButton.insertAdjacentHTML('beforeend', 'Import');
	return submitButton;
}