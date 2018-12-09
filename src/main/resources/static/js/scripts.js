
$('.answer-write input[type=submit]').click(function(e){
  e.preventDefault();
  console.log("addAnswer");

  var queryString = $(".answer-write").serialize();
  console.log(queryString);

  var url = $('.answer-write').attr('action');
  console.log(url);

  $.ajax({
    type : 'post',
    url : url,
    data : queryString,
    dataType : 'json',
    success : function(data, status){
      console.log(data);
    },
    error : function() {
    	
    }
  });
});
