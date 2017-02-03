$(function() {
	//header.html 가져오기
	$.get("../header.html", function(result) {
		$.getJSON("../auth/loginUser.json", function(ajaxResult) {
			$("#header").html(result);
			
			if (ajaxResult.status == "fail") {
				$("#logon-div").css('display', 'none');
				
				$('#login-btn').click(function(event) {
					event.preventDefault();
					location.href = "../auth/main.html";
				});
				
				return;
			}
			
			$("#logoff-div").css('display', 'none');
			$("#logon-div img").attr('src', "../upload/" + ajaxResult.data.photoPath);
			$("#logon-div span").text(ajaxResult.data.name);
			
			$('#logout-btn').click(function(event) {
				event.preventDefault();
				$.get('../auth/logout.json', function(jsonText) {
					location.href = "../auth/main.html";
				});
			});
		});
	});
	
	
	//sidebar.html 가져오기
	$.get("../sidebar.html", function(result) {
		$('#sidebar').html(result);
	});
	
	
	//footer.html 가져오기
	$.get("../footer.html", function(result) {
		$('#footer').html(result);
	});
});