	document.querySelector('#login-btn').onclick = function() {
		if (document.querySelector('#save-email').checked) {
      setCookie('email', document.querySelector('#email').value, 30);
    } else {
    	setCookie('email', '', 0);
    }
		
		var param = {
			email: document.querySelector('#email').value,
			password: document.querySelector('#password').value
		};
		
		var userTypeList = document.querySelectorAll('input[name=user-type]');
		for (var i = 0; i < userTypeList.length; i++) {
			if (userTypeList[i].checked) {
				param.userType = userTypeList[i].value;
				break;
			}
		}
		
		post('login.json', param, function(jsonText) {
			var ajaxResult = JSON.parse(jsonText);
			
			if (ajaxResult.status == "success") {
				location.href = "../student/main.html";
				return;
			}
			
			alert(ajaxResult.data);
		});
	}
	
	document.querySelector('#email').value = getCookie('email').replace(/"/g, '');
	
	