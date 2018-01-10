// jQuery for page scrolling feature - requires jQuery Easing plugin
$(function() {
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
});
function manipulateClass() {
        
    var re = document.getElementById('re');
    var aff = document.getElementById('aff');
    var am = document.getElementById('am');
    
    this.click.document.getElementById('re').addClass('.active');
   
    }

