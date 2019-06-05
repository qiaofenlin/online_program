/**
 * Created by ubuntu on 17-1-22.
 */
$(document).ready(function () {
    $(".navs-slider a:eq(0)").click(function () {
        $(this).addClass("active").siblings("a").removeClass("active");
        $(".navs-slider-bar").css({left:"0px"});
        $(".view-signup").css("display","block").siblings(".view-signin").css("display","none");
    })
    $(".navs-slider a:eq(1)").click(function () {
        $(this).addClass("active").siblings("a").removeClass("active");
        $(".navs-slider-bar").css({left:"4em"});
        $(".view-signin").css("display","block").siblings(".view-signup").css("display","none");
    })




})
