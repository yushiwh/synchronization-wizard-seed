/**

 @Name：layuiAdmin iframe版登录页
 @Author：wuqing
 @License：LPPL

 */

layui.define(["layer","jquery"], function(exports){
    var obj={
      login:function(_name,_password){
        layer.msg(_name+'_'+_password+':login');
      //  document.location.href="index.html";
        document.location.href="index";
      }
    }
    //对外输出
    exports('login',obj);
  });
