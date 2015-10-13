/*!
 * -- Material CSS
 * -- Scripts for animation support
 * -- Requires ZeptoJS/jQuery and VelocityJS
**/

var Zepto = Zepto || undefined;
var jQuery = jQuery || undefined;
var Velocity = Velocity || undefined;
( (Zepto==undefined && jQuery==undefined) || Velocity==undefined ) ? ( window.console && console.warn("ZeptoJS/jQuery and VelocityJS are not loaded!") ) : (function(){
	
	"use strict";

	// global animation config
	var anim = { duration: 200, easing: 'easeOutQuint' };
	var body = $('body');

	$(function(){

		// panels
		$("[panel-target]").each(function(i){
			var self = $(this);
			var panel = $('#' + self.attr("panel-target"));
			var main = $('#' + self.attr("panel-target") + ' > [main]');
			var drawer = $('#' + self.attr("panel-target") + ' > [drawer]');
			self.click(function(e){
				if(panel.attr('opened') != null){
					panel.removeAttr("opened");
					drawer.velocity({ left: '-305px' }, anim);
					body.css({overflow: 'auto'});
				}else{
					panel.attr("opened", true);
					//main.velocity({ opacity: .5 }, anim);
					//panel.css({ backgroundColor: "#000" });
					drawer.velocity({ left: 0 }, anim);
					body.css({overflow: 'hidden'});
				}
				// stop bubbling to prevent closing itself
				e.preventDefault();
				e.stopPropagation();
			});
			main.click(function(e){
				if(panel.attr('opened') == null) return;
				panel.removeAttr("opened");
				drawer.velocity({ left: '-305px' }, anim);
				body.css({overflow: 'auto'});
			});
		});
		
		// pages
		$("[pages] > [page]").each(function(i){
			// hide the pages
			var self = $(this);
			if(self.attr("default") != null){
				self.removeAttr("default").attr('active', true);
				$('[page-target="' + self.attr('page-id') + '"]').attr('active', true);
			}
		});
		
		$("[page-target]").each(function(i){
			var self = $(this);
			var target = $('[page][page-id="' + self.attr('page-target') + '"]');
			var targetContainer = target.parent();
			var title = target.attr('page-title');
			self.click(function(e){
				var current = targetContainer.find('[active]');
				current.removeAttr("active");
				target.attr("active", true);
				// set title
				if(title != ''){
					$('[toolbar] > [title]').html(title);
				}
				// set self as active
				self.parent().parent().find('[active]').removeAttr('active');
				self.attr('active', true);
				// hide panel
				$('[panel]').removeAttr("opened");
				$('[panel] > [drawer]').velocity({ left: '-305px' }, anim);
				body.css({overflow: 'auto'});
				// scroll to top
				$(window).scrollTop(0);
				e.preventDefault();
			});
		
		});
		
		// ripples
		var rippleCheck = function(){
			$('[ripple], [material] button, [button], [fab]').each(function(i){
				var self = $(this);
				var $this = this;
				// check if it has already been injected
				if(self.attr('rippleReady') != null) return;
				// check if this has a ripple or a noripple attribute
				if(self.attr('noripple') != null) return;
				if(self.attr('ripple') == null) self.attr('ripple', true);
				// flag
				self.attr('rippleReady', true);
				// listen for click/tap events
				self.on('touchstart mousedown', function(e){
					// for dynamically changed elements
					if(self.attr('noripple') != null) return;
					// thanks to http://stackoverflow.com/a/16284281
					var toXY = function(e){
						var out = {x:0, y:0};
						if(e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel'){
							var touch = e.touches[0] || e.changedTouches[0];
							out.x = touch.clientX;
							out.y = touch.clientY;
							var rect = $this.getBoundingClientRect();
							out.x -= rect.left;
							out.y -= rect.top;
						//} else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover'|| e.type=='mouseout' || e.type=='mouseenter' || e.type=='mouseleave') {
						}else{
							out.x = e.pageX - self.offset().left;
							out.y = e.pageY - self.offset().top;
						}
						return out;
					};
					var p = toXY(e);
					// parse the position
					var x = p.x;
					var y = p.y;
					x = parseInt(x);
					y = parseInt(y);
					// set the max dimension to the diagonal length or to 150px
					var w = self.width();
					var h = self.height();
					var d = Math.min(Math.sqrt(w * w + h * h), 150) * 1.1 + 5;
					// add circle
					var svg = $('<svg _ripple-container><circle _ripple cx="'+x+'" cy="'+y+'" r="0"></circle></svg>');
					var circle = svg.find('circle');
					// add rectangle
					var svg2 = $('<svg _ripple-container><rect width="'+w+'" height="'+h+'"></rect></svg>');
					var rect = svg2.find('rect');
					rect.css({opacity: 0, fill: 'white'});
					// get color fill if there is any
					if(self.attr('ripple-color') != null){
						// is it a native color?
						// thanks to http://stackoverflow.com/questions/6386090/validating-css-color-names
						var color = self.attr('ripple-color');
						var validColor = function(color){
							var rgb = $('<div style="color:#123455">');     // Use a non standard dummy colour to ease checking for edge cases
							var dummy_rgb = "rgb(18, 52, 85)";
							rgb.css("color", color);
							try{rgb.attr('fg-' + color, true)}catch(e){};
							body.append(rgb);
							// check if it is a material color
							var csscolor = rgb.css('color');
							var newcolor = window.getComputedStyle(rgb[0], null).getPropertyValue('color');
							rgb.remove();
							//console.log('css: ' + csscolor + ' | newcolor: ' + newcolor);
							if(csscolor == dummy_rgb && newcolor == dummy_rgb && color != ':#123455' && color.replace(/ /g,"") != dummy_rgb.replace(/ /g,"")){
								return false;
							}else{
								// return the newcolor as the material
								// color is different from the css natives
								return newcolor;
							}
						}
						var color = validColor(color);
						if(color !== false){
							circle.css({fill: color});
							rect.css({fill: color});
						}
					}
					self.append(svg);
					self.append(svg2);
					// animate
					rect.velocity({
						opacity: .2
					}, {
						delay: 100,
						easing: "linear",
						duration: 400,
						complete: function(){
							rect.velocity({
								opacity: 0
							}, {
								easing: "linear",
								duration: 400,
								complete: function(){
									svg2.remove();
								}
							});
						}
					});
					circle.velocity({
						r : d,
						opacity: .5
					}, {
						easing: "linear",
						duration: 300,
						complete: function(){
							circle.velocity({
								r: d * 2,
								opacity: 0
							}, {
								easing: "linear",
								duration: 300,
								complete: function(){
									svg.remove();
								}
							});
						}
					});
				});
			
			});
		
		}
		rippleCheck();
		// constantly check for new elements
		setInterval(rippleCheck, 5000);
		// add to global
		window.ripple = rippleCheck;

	});
})();