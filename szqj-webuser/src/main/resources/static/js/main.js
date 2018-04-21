;(function () {
	'use strict';
	var i = 0;
	$('.animate-box').waypoint( function( direction ) {
		console.log("----");
		if( direction === 'down' && !$(this.element).hasClass('animated-fast') ) {
			i++;
			$(this.element).addClass('item-animate');
			setTimeout(function(){
				$('body .animate-box.item-animate').each(function(k){
					console.log("=========="+k);
					var el = $(this);
					setTimeout( function () {
						var effect = el.data('animate-effect');
						el.addClass(effect + ' animated-fast');
						el.removeClass('item-animate');
					},  k * 200, 'easeInOutExpo' );
				});
			}, 100);
		}

		console.log("=====");
	} , { offset: '85%' } );
}());

