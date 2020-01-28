/**
 * @use 유튜브 비디오 링크 용으로 제작
 * @author hjjang
 * 2019.10.31
 */

nhn.husky.SE2_VideoUrl = jindo.$Class({
    name : "SE2_VideoUrl",

    $init : function(elAppContainer){

        this._assignHTMLObjects(elAppContainer);
    },

    _assignHTMLObjects : function(elAppContainer){
        this.oDropdownLayer = cssquery.getSingle("DIV.husky_seditor_ui_VideoUrl_layer", elAppContainer);
        //div 레이어안에 있는 input button을 cssquery로 찾는 부분.
        this.oInputUrlTextBox = cssquery.getSingle(".se_text_url", elAppContainer);
        this.oInputUrlButtonBox = cssquery.getSingle(".se_button_url", elAppContainer);
    },

    $ON_MSG_APP_READY : function(){
        this.oApp.exec("REGISTER_UI_EVENT",["VideoUrl", "click", "SE_TOGGLE_VIDEOURL_LAYER"]);
        //input button에 click 이벤트를 할당.

        this.oApp.registerBrowserEvent(this.oInputUrlButtonBox, 'click', 'ADD_VIDEO_URL');
    },

    $ON_SE_TOGGLE_VIDEOURL_LAYER : function(){

        this.oApp.exec("TOGGLE_TOOLBAR_ACTIVE_LAYER", [this.oDropdownLayer]);
    },

    $ON_ADD_VIDEO_URL : function(){

        var videoFrm =""+
            "<p><iframe width='560' height='315' src="+this.oInputUrlTextBox.value+" frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen=''>"+
            "</iframe></p>";
        this.oApp.exec("PASTE_HTML", [videoFrm]);
    }
});