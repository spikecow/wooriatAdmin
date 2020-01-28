$(document).ready(function(){
    function doPopupopen(imgsrc,name) {

        // window. open("popup?img="+imgsrc+"&name="+name, "popup01", "width=300, height=360");
        window.open("popup?img="+imgsrc+"&name="+name, "", "width=900, height=360");
    }

    $(".locationPopup").click(function() {

        //doPopupopen($(this).attr('data-img'),$(this).attr('data-name'));
        //console.log($(this).offset().top);

        if($(this).attr("data-type") == "B"){
            $("#preview").css({"top":$(this).offset().top - 50});
        }
        else{
            $("#preview").css({"top":$(this).offset().top - $(".modal-dialog").offset().top});
        }

        $("#preview img").attr("src", "/common/image/preview/pc/" + $(this).attr("data-img") + "/" + $(this).attr("data-name") + ".png");
        $("#preview").fadeIn("slow");
    });
});
