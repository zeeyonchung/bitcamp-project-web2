  
  //학생 목록 가져와서 tr 태그 붙이기
  $.getJSON("list.json", function(ajaxResult) {
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  return;
	  }
	  var list = ajaxResult.data;
	  var tbody = $('#list-table > tbody');
	  
	  for (var student of list) {
		  $("<tr>").html("<td>" + 
			student.memberNo + "</td><td><a class='name-link' href='#' data-no='" + 
			student.memberNo +"'>" + 
			student.name + "</a></td><td>" + 
			student.tel + "</td><td>" + 
			student.working + "</td><td>" +
			student.grade + "</td><td>" +
			student.schoolName + "</td>").appendTo(tbody);
	  }
	  
	  $('.name-link').click(function(event) {
		  event.preventDefault();
		  location.href = 'view.html?memberNo=' + $(this).attr("data-no");
	  });
	  
  });
  
  
  $('#new-btn').click(function(event) {
	  event.preventDefault();
	  location.href = 'view.html';
  });