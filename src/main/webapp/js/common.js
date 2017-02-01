window.addEventListener("load", function() {
	//header.html 가져오기
	get("../header.html", function(result) {
		get("../auth/loginUser.json", function(jsonText) {
			var ajaxResult = JSON.parse(jsonText);
			document.querySelector("#header").innerHTML = result;
			
			if (ajaxResult.status == "fail") {
				document.querySelector("#logon-div").style.display = "none";
				
				document.querySelector('#login-btn').onclick = function(event) {
					event.preventDefault();
					location.href = "../auth/main.html";
				};
				
				return;
			}
			
			document.querySelector("#logoff-div").style.display = "none";
			document.querySelector("#logon-div img").src = 
				"../upload/" + ajaxResult.data.photoPath;
			document.querySelector("#logon-div span").textContent =
				ajaxResult.data.name;
			
			document.querySelector('#logout-btn').onclick = function(event) {
				event.preventDefault();
				get('../auth/logout.json', function(jsonText) {
					location.href = "../auth/main.html";
				});
			};
		});
	});
	
	
	//sidebar.html 가져오기
	get("../sidebar.html", function(result) {
		document.querySelector('#sidebar').innerHTML = result;
	});
	
	
	//footer.html 가져오기
	get("../footer.html", function(result) {
		document.querySelector('#footer').innerHTML = result;
	});
});