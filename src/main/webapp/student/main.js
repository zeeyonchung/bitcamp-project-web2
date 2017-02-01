  
  //학생 목록 가져와서 tr 태그 붙이기
  get("list.json", function(result) {
	  //result JSON 문자열을 자바스크립트 객체로 만든다
	  var ajaxResult = JSON.parse(result);
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  return;
	  }
	  var list = ajaxResult.data;
	  var tbody = document.querySelector('#list-table > tbody');
	  
	  for (var student of list) {
		  var tr = document.createElement("tr");
		  tr.innerHTML = "<tr><td>" + 
		    student.memberNo + "</td><td><a class='name-link' href='#' data-no='" + 
		    student.memberNo +"'>" + 
		    student.name + "</a></td><td>" + 
		    student.tel + "</td><td>" + 
		    student.working + "</td><td>" +
		    student.grade + "</td><td>" +
		    student.schoolName + "</td></tr>";
		  tbody.appendChild(tr);
	  }
	  var al = document.querySelectorAll('.name-link');
	  for (var a of al) {
		  a.onclick = function(event) {
			  event.preventDefault();
			  location.href = 'view.html?memberNo=' + this.getAttribute("data-no");
		  }
	  }
  });
  
  
  document.querySelector('#new-btn').onclick = function(event) {
	  event.preventDefault();
	  location.href = 'view.html';
  };