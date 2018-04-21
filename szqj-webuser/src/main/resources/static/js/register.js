/**
 * 
 */
$(function(){
	/*处理ie低版本占位符bug*/
	$('input').placeholder({customClass:'my-placeholder'});
	validate('loginForm');
	$('#mobile').keyup(keyupItem);
	$('#password').keyup(keyupItem);
	$('#repeatpassword').keyup(keyupItem);
	$('#verifycode').keyup(keyupItem);
    //	
	$(".agree_box").on('click','#check_box,label',function(){
		$("#check_box").toggleClass('active').parent('div').find('font').html('');
	})
	/*回车键*/
	$('body').keyup(function(e){
	    	var code = e.keyCode;
	    	var flag = $('#register_container').is(':visible');
	    	if(code == 13 && flag){
	    		doRegister();
	    	}
	})
	var timeId = {
		mobile:'mobileTimeId',
		password:'passwordTimeId',
		repeatpassword:'repeatpasswordTimeId',
		verifycode:'verifycodeTimeId'
    }
	//失去焦点
	$('#mobile').blur(function(){
		if(timeId.mobile){
			clearTimeout(timeId.mobile);
			timeId.mobile = null;
		}
		timeId.mobile = setTimeout(function(){
			blurItem('#mobile');
		},300);
	});
	//获取焦点
	$('#mobile').focus(function(){
		if(timeId.mobile){
			clearTimeout(timeId.mobile);
			timeId.mobile = null;
		}
		focusItem('#mobile');
	});
	$('#password').blur(function(){
		if(timeId.password){
			clearTimeout(timeId.password);
			timeId.password = null;
		}
		var password = $(this).val().trim();
		var repeatpassword = $('#repeatpassword').val().trim();
		if(repeatpassword){
			if(password == repeatpassword ){
				$('#repeatpassword').parents('.register').removeClass('error');
				$('#repeatpassword').parents(".register_list").find("font").html('<img src="'+CTX+'/resources/css/images/u344.png">');
			}else{
				$('#repeatpassword').parents(".register_list").find("font").html('');
			}
		}
		timeId.password = setTimeout(function(){
			blurItem('#password');
		},300);
	});
	$('#password').focus(function(){
		if(timeId.password){
			clearTimeout(timeId.password);
			timeId.password = null;
		}
		focusItem('#password');
	});
	$('#repeatpassword').blur(function(){
		if(timeId.repeatpassword){
			clearTimeout(timeId.repeatpassword);
			timeId.repeatpassword = null;
		}
		timeId.repeatpassword = setTimeout(function(){
			blurItem('#repeatpassword');
		},300);
	});
	$('#repeatpassword').focus(function(){
		if(timeId.repeatpassword){
			clearTimeout(timeId.repeatpassword);
			timeId.repeatpassword = null;
		}
		focusItem('#repeatpassword');
	});
	$('#verifycode').blur(function(event){
		if(timeId.verifycode){
			clearTimeout(timeId.verifycode);
			timeId.verifycode = null;
		}
		var obj = event.target;
		timeId.verifycode = setTimeout(function(){
			$(obj).siblings('.delete_icon').hide();
			$(obj).removeClass("item-focus");
		},300);
	});
	$('#verifycode').focus(function(){
		if(timeId.verifycode){
			clearTimeout(timeId.verifycode);
			timeId.verifycode = null;
		}
		focusItem('#verifycode');
	});
});
function validate(id){
	var form = $("#" + id);
	var rtn = form.validate({
		onfocusout:function(eles){
			$(eles).valid();
		},
		rules: {
			mobile:{
				required: true,
				mobile:true,
				remote:  {
					url: CTX + '/user/mobile_verify.ht',
					type: "post",
					dataType: "json",
					async : false,
					cache: false,
					data: {
					    "uuid":function(){
					          return $("#uuid").val();
				          }
					}
				}
			},
			verifycode: {
				required: true,
				verifycode:true
			},
			password:{
				required: true,
				pass:true,
				rangelength:[6,16]
				
			},
			repeatpassword:{
				required: true,
				pass:true,
				equalTo:"#password"
				
			}
        },
        messages:{
        	mobile:{
        		required:'<img src="'+CTX+'/resources/css/images/u338.png"/>请输入常用手机号',
        		mobile:'<img src="'+CTX+'/resources/css/images/u338.png"/>手机格式不正确,请重新输入',
        		remote:'<img src="'+CTX+'/resources/css/images/u338.png"/>此手机号已被绑定,<a href="javascript:Login();">登录</a>或选择其他的账号'
        	},
        	verifycode:{
				required:'<img src="'+CTX+'/resources/css/images/u338.png"/>请输入验证码',
				verifycode:'<img src="'+CTX+'/resources/css/images/u338.png"/>验证码不正确'
			},
        	password:{
				required:'<img src="'+CTX+'/resources/css/images/u338.png"/>请输入密码',
				pass:'<img src="'+CTX+'/resources/css/images/u338.png"/>密码格式不正确',
				rangelength:'<img src="'+CTX+'/resources/css/images/u338.png"/>请输入一个6到16之间的字符串'
			},
			repeatpassword:{
				required:'<img src="'+CTX+'/resources/css/images/u338.png"/>请输入密码',
				pass:'<img src="'+CTX+'/resources/css/images/u338.png"/>密码格式不正确',
				equalTo:'<img src="'+CTX+'/resources/css/images/u338.png"/>请再次输入相同的值'
			}
        },
        onkeyup: false,
        debug: true,
        errorPlacement: function(error, element){
        	var placement = $(element.parents(".register_list").find("font"));
        	error.appendTo( placement ); 
        	$(element.parents(".register_list").find(".register")).addClass('error');
        }
	});
}

function checkXYBox() {
	var check_box=$("#check_box").hasClass('active');
	if(check_box)
		$("#check_box").parents(".register_list").find("font").html('');
	else
		$("#check_box").parents(".register_list").find("font").html('<label class="error"><img src="'+CTX+'/resources/css/images/u338.png"/>请勾选协议</label>');
	return check_box;
}
function doRegister() {
	var checkRet = true;
	if(!$("#loginForm").valid())
		checkRet = false;
	if(!checkXYBox())
		checkRet = false;
	if(!checkRet)
		return;
	layer.alert('正在注册请稍候。。。', {icon: 16,time: 100000,shade : [0.7 , '#000' , true],skin:'my-skin-msg',title:'', closeBtn: 0});
	$.ajax({
		type:"POST", 
		url:CTX+"/register/do.ht",
		dataType: "json",
		cache: false,
		data:{"mobile":$('#mobile').val(),
			"password":encrypt($('#password').val()),
			"verifycode":$('#verifycode').val(),
			"uuid":$('#uuid').val()
		},
		success: function(ret){
			var msg = '';
			layer.closeAll();
			if(ret && ret.result == 1) {
				if(location.href.getQuery('from')=='app') {
					getIosAnd();
				}
				msg = '成功';
				$('#register_container').hide();
				$('#register_success').show();
				$('.tips').show();
				
			} else if(ret && ret.message && ret.result < 0) {
				layer.alert(ret.message);
				msg = ret.message;
			} else {
				layer.alert("注册失败，请刷新重试");
				msg = "注册失败，请刷新重试";
			}
			if(_htlog != 'undefined')
				_htlog.track(null, 'register', 'doregister', msg, $("#mobile").val(), null);
		},
		error: function(e){
			if(_htlog != 'undefined')
				_htlog.track(null, 'register', 'doregister', '网络异常，请刷新重试', $("#mobile").val(), null);
			layer.closeAll();
			layer.alert("网络异常，请刷新重试");
		}});
}

function getIosAnd(){
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if(isAndroid){
            window.htyw.actionFromJsWithParam('register_success');
        }else if(isiOS){
            window.webkit.messageHandlers.AppModel.postMessage('register_success');
        }
    };
var timeId = null;
function blurItem(obj) {
	$(obj).siblings('.delete_icon').hide();
	$(obj).removeClass("item-focus");
	$(obj).parents(".register_list").find(".register").addClass('error');
	var result=$("#loginForm").validate().element($(obj));
	if(result){
		$(obj).parents(".register_list").find("font").html('<img src="'+CTX+'/resources/css/images/u344.png">');
		$(obj).parents(".register_list").find(".register").removeClass('error');
	}
}

function focusItem(obj ) {
	var val = $(obj).val();
	if(val){
		$(obj).siblings('.delete_icon').show();
	}else{
		$(obj).siblings('.delete_icon').hide();
	}
	$(obj).addClass("item-focus");
	$(obj).parents(".register_list").find("font").html('');
	$(obj).parents(".register_list").find(".register").removeClass('error');
}

function Login(){
	var systemId = window.location.href.getQuery('systemId') || '100';
	var returnUrl=window.location.href;
	var params=new Array();
	params.push({name:"systemId",value:systemId});
	Post(CTX+"/user/doLogin.ht",params);
}
function next() {
	var systemId = window.location.href.getQuery('systemId') || '100';
	var returnUrl=window.location.href;
	var params=new Array();
	params.push({name:"systemId",value:systemId});
	params.push({name:"next",value:"1"});
	Post(CTX+"/user/doLogin.ht",params);
}
var loginDone = false;
function Post(url,params){
	if(loginDone)
		return;
	loginDone = true;
	//创建一个form
	 var temp_form = document.createElement("form");
	 //指定form的跳转action 
	 temp_form.action=url;
	 temp_form.method = "post";
	 temp_form.style.display = "none";
	 //添加参数
	 for(var item in params){
		 //创建若干文本域
		 var opt = document.createElement("input");
		     opt.setAttribute("type", "text");
		     opt.name=params[item].name;
		     opt.value=params[item].value;
		     temp_form.appendChild(opt);
	 }
	 document.body.appendChild(temp_form);
	//提交数据
     temp_form.submit();
}
//发送短信验证码
function sendCode(){
		if(!$("#loginForm").validate().element($("#loginForm").find('input[name="mobile"]'))) {
			var mobile=$("#mobile").val();
			/*$("#mobile").focus();*/
			return;
		}

		var mobile = $("#mobile").val();
		var uuid = $("#uuid").val();
		var sendObj = $('input#verifyBtn');
		start_sms_button(sendObj);
		$.ajax({
			type : 'POST',
			url : CTX + "/user/smscode_service.ht",
			cache: false,
			data : {mobile : mobile, uuid : uuid, flag : 1},
			success : function(dataMap) {
				if(dataMap && dataMap.result && dataMap.result == 1) {
					/*layer.alert('验证码下发成功，注意查收');*/
					if(_htlog != 'undefined')
						_htlog.track(null, 'register', 'send_sms', '成功', $("#mobile").val(), null);
				} else {
					var msg = "验证码下发失败";
					if(dataMap && dataMap.msg)
						msg = dataMap.msg;
					layer.alert(msg);
					if(_htlog != 'undefined')
						_htlog.track(null, 'register', 'send_sms', msg, $("#mobile").val(), null);
				}
				
			},
			error : function(dataMap){
				layer.alert("网络异常，请刷新重试");
				if(_htlog != 'undefined')
					_htlog.track(null, 'register', 'send_sms', '网络异常，请刷新重试', $("#mobile").val(), null);
			}
		});
}
function start_sms_button(obj){
    var count = 1 ;
    var sum = 60;
    obj.attr('disabled',true); 
    var i = setInterval(function(){
      if(count > sum){
        obj.attr('disabled',false);
        obj.val('发送验证码').css({'color':'#333','cursor':'pointer'});
        clearInterval(i);
      }else{
        obj.val('重发剩余'+parseInt(sum - count)+'秒').css({'color':'#999','cursor':'not-allowed'});
      }
      count++;
    },1000);
  }