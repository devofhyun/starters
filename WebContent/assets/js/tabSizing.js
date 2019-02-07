function verCul() {
	$('#myTab').addClass('flex-column');
	$('.nav-tabs .nav-link').css({
		'height': '40px',
		'line-height': '30px'
	});
}

function horCul() {
	$('#myTab').removeClass('flex-column');
	$('.nav-tabs .nav-link').css({
		'height': '70px',
		'line-height': '50px'
	});
}
