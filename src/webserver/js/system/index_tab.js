/** TAB展现 */
var tabs;

var accordion;

/** 连接状态栏按钮 */
var button_connStatus;  

/** 退出登录按钮 */
var button_logout;  

$(document).ready(function() {
  
  /** TAB显示 */
  tabs = $( "#tabs" ).tabs();

  // close icon: removing the tab on click
  tabs.delegate( "span.ui-icon-close", "click", function() {
    var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
    $( "#" + panelId ).remove();
    tabs.tabs( "refresh" );
  });

  tabs.bind( "keyup", function( event ) {
    if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
      var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
      $( "#" + panelId ).remove();
      tabs.tabs( "refresh" );
    }
  });
  
  /*tabs.on( "tabsactivate", function( event, ui ) {
	  doOnSwitchCustomerTab(ui.newPanel);
  });*/

  tabs({    
                onSelect: function(){   
                    openTab();                                         
                }   
            });   
  
  
  /** accordion显示 */
  accordion = $("#accordion" ).accordion({
      heightStyle: "content"
  });
  
  /** 连接状态显示 */
  button_connStatus = $("#connStatus").button().click(function(event) {        
	  event.preventDefault();      
  });
  button_connStatus.button( "option", "disabled", true ); 
  
  /** 退出登录按钮显示 */
  button_logout = $("#logoutButton").button().click(function(event) {       
	  if (confirm("您确定退出登录吗？")) {
		  var sCtx = $("#ctx").val();
		  window.location.href = sCtx + "/logout.jsp";
  	  }
  });
  
})


function addAccordionHeader(sHeaderId, sContent) {
	/*alert("sybuildchen3");
	$("#accordion").append("<h3 id='" + sHeaderId + "'>" + sHeader + "</h3><div>" + sContent + "</div>");
	alert("sybuildchen4");
	accordion.accordion("refresh");	
	*/

	if ($("." + sHeaderId).length != 0) {
		$("." + sHeaderId).empty();
	}	
	var oldlist = $(".userlist").html();
	var newuser = "<div class=" + sHeaderId + ">" + sContent + "</div>";
	$(".userlist").html(newuser + oldlist);      					
}

function addCustomerTab(sTabId, sTitle, sContent, sKh_khbh, sKh_openid) {
    /*var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>";
    var li = $( tabTemplate.replace( /#\{href\}/g, "#" + sTabId ).replace( /#\{label\}/g, sTitle ) );
    var tabContentHtml = sContent;
    tabs.find(".ui-tabs-nav").append(li);
    tabs.append( "<div id='" + sTabId + "' khbh='" + sKh_khbh + "' openid='" + sKh_openid + "'><p>" + tabContentHtml + "</p></div>" );
    tabs.tabs("refresh");
    */
    var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>";
    var li = $( tabTemplate.replace( /#\{href\}/g, "#" + sTabId ).replace( /#\{label\}/g, sTitle ) );
    var tabContentHtml = sContent;

    $('#tabs').tabs('add',{
		title: sTitle,
		khbh: sKh_khbh,
		openid: sKh_openid,
		content: "<div id='" + sTabId + "' khbh='" + sKh_khbh + "' openid='" + sKh_openid + "'><p>" + tabContentHtml + "</p></div>",
		closable: true
	});
}





function toggleSysMenu() {
    var a = this.getDom$().find(".operaterBox");
    c.globalEventSetting({globalIntercept:!a.isShow(), interceptDom$:a});
    a.html(b.tmpl("chat_operaterBoxPanel", c.model("account").getUserInfo()));
    b.browser.msie ? a.toggle() : a.fadeToggle("fast")
  }
  
function logout() {
    this.confirm(c.getRes("text_logout_confirm"), {ok:function() {
      f.onbeforeunload = null;
      c.util.logout(0)
    }});
    this.toggleSysMenu()
  }
  
