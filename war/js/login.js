$(document)
		.ready(
				function() {
					$('#defaultForm')
							.bootstrapValidator(
									{
										// live: 'disabled',
										message : 'This value is not valid',
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											name : {
												message : 'The username is not valid',
												validators : {
													notEmpty : {
														message : 'The username is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'The username must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z0-9_\.]+$/,
														message : 'The username can only consist of alphabetical, number, dot and underscore'
													},
													different : {
														field : 'password',
														message : 'The username and password cannot be the same as each other'
													}
												}
											},
											password : {
												validators : {
													notEmpty : {
														message : 'The password is required and cannot be empty'
													},
													different : {
														field : 'username',
														message : 'The password cannot be the same as username'
													}
												}
											},
											role : {
												validators : {
													notEmpty : {
														message : 'The role is required'
													}
												}
											}
										}
									});

					// Validate the form manually
					$('#validateBtn').click(function() {
						$('#defaultForm').bootstrapValidator('validate');
					});

					$('#resetBtn').click(
							function() {
								$('#defaultForm').data('bootstrapValidator')
										.resetForm(true);
							});
				});