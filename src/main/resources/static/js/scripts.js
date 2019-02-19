$('.answer-write input[type=submit]').click(function(e) {
	e.preventDefault();
	var queryString = $(".answer-write").serialize();
	var url = $('.answer-write').attr('action');
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		success : function(data, status) {
			var answerTemplate = $('#answerTemplate').html();
			var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id, data.id);
			$('.qna-comment-slipp-articles').prepend(template);
			$('textarea[name=contents]').val("");
		},
		error : function(xhr, status) {
			console.error(xhr);
		}
	});
});

$('.qna-comment-slipp-articles').on('click', '.link-delete-article', function(e) {
	e.preventDefault();
	var deleteArticle = $(this);
	var url = deleteArticle.attr('href');
	console.log(url);
	$.ajax({
		type : "delete",
		url : url,
		dataType : "json",
		success : function(data, status) {
			if(data.valid){
				deleteArticle.closest("article").remove();
			}
		},
		error : function(xhr, status) {
			console.error(xhr);
		}
	});
});


String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};




































