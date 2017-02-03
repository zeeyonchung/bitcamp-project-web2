var memberNo = 0;
try {
	var memberNo = location.href.split('?')[1].split('=')[1];
} catch(error) {
	memberNo = -1;
}


if (memberNo > 0) {
	prepareViewForm();
} else {
	prepareNewForm();
}



function prepareViewForm() {
	$('.new-form').css('display', 'none');


	$.getJSON("detail.json?memberNo=" + memberNo, function(ajaxResult) {
		var status = ajaxResult.status;

		if (status != "success") {
			alert(ajaxResult.data);
			return;
		}
		var student = ajaxResult.data;

		$('#email').val(student.email);
		$('#name').val(student.name);
		$('#tel').val(student.tel);

		if (student.working) {
			$('#working').attr('checked', 'checked');
		} else {
			$('#not-working').attr('checked', 'checked');
		}
		$('#grade').val(student.grade);
		$('#school-name').val(student.schoolName);
		$('#photo-img').attr('src', '../upload/' + student.photoPath);
	});

	$('#delete-btn').click(function() {
		$.getJSON('delete.json?memberNo=' + memberNo, function(ajaxResult) {
			if (ajaxResult.status != 'success') {
				alert(ajaxResult.data);
				return;
			}
			location.href = 'main.html';
		});
	});

	
	
	
	$('#update-btn').click(function() {
		var param = {
				memberNo: memberNo,
				name: $('#name').val(),
				tel: $('#tel').val(),
				email: $('#email').val(),
				password: $('#password').val(),
				working: $('#working').is(':checked'),
				grade: $('#grade').val(),
				schoolName: $('#school-name').val()
		};
		
		console.log($('#working').is(':checked'));
		
		$.post('update.json', param, function(ajaxResult) {
			if (ajaxResult.status != 'success') {
				alert(ajaxResult.data);
				return;
			}
			location.href = 'main.html';
		}, 'json');
	});

} //prepareViewForm()



function prepareNewForm() {
	$('.view-form').css('display', 'none');


	$('#add-btn').click(function() {
		var param = {
				name: $('#name').val(),
				tel: $('#tel').val(),
				email: $('#email').val(),
				password: $('#password').val(),
				working: $('#working').is(':checked'),
				grade: $('#grade').val(),
				schoolName: $('#school-name').val()
		};

		$.post('add.json', param, function(ajaxResult) {
			if (ajaxResult.status != 'success') {
				alert(ajaxResult.data);
				return;
			}
			location.href = 'main.html';
		}, 'json');
	});
}




$('#list-btn').click(function() {
	location.href = 'main.html';
});