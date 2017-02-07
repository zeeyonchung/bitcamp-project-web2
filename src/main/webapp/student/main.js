  
  //학생 목록 가져와서 tr 태그 붙이기
  $.getJSON("list.json", function(ajaxResult) {
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  return;
	  }
	  var list = ajaxResult.data;
	  var tbody = $('#list-table > tbody');
	  
	  
	  var template = Handlebars.compile($('#trTemplate').html());
	  
	  tbody.html(template({list: list}));
	  
	  
	  $('.name-link').click(function(event) {
		  event.preventDefault();
		  location.href = 'view.html?memberNo=' + $(this).attr("data-no");
	  });
  });
  
  
  $('#new-btn').click(function(event) {
	  event.preventDefault();
	  location.href = 'view.html';
  });