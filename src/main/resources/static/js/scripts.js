
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
    success : onSuccess,
    error : onError,
  });
});


function onError() {
	
}

function onSuccess(data, status) {
	console.log(data);
	var answerTemplate = $('#answerTemplate').html();
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.id, data.id);
    
	$('.qna-comment-slipp-articles').prepend(template);
    $('textarea[name=contents]').val("");
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};






