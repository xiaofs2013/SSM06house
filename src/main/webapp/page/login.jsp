<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户登录</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script language="JavaScript" type="text/javascript" src="../admin/js/jquery-1.8.3.js"></script>


    <script language="JavaScript">
        var timeobj;
        $(function () {
            $("#sendButton").click(function () {
                $.post("sendCode", {"tel": $("#inputTel").val()}, function (data) {
                    alert(data.result)
                    if (data.result > 0) {
                        timeobj = setInterval("goback()", 1000); //设置定时器  1秒触发一次goback方法
                        alert("发送验证码成功！")

                    } else {
                        alert("发送失败")
                    }

                }, "json")

            })

        })


        //显示倒计时
        var time = 60;

        function goback() {
            time--;
            if (time == 0) {
                $("#sendButton").val("重新发送");
                time = 60;
                clearInterval(timeobj); //消除定时
            } else {
                $("#sendButton").val(time);
                $("#sendButton").disabled = true;
            }
        }


    </script>


</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DIV class=box>
            <H4>用户登录</H4>
            <FORM id=user method=post name=user action="loginAction">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD colSpan=2>${info}</TD>
                        </TR>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD><!-- <input type="text" class="text" name="name" /> --><INPUT
                                    id=user_name class=text type=text name=name></TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD><!-- <input type="password" class="text" name="password" /> --><INPUT
                                    id=user_password class=text type=password name=password></TD>
                        </TR><!--
						<tr>
							<td class="field">验 证 码：</td>
							<td><input type="text" class="text verycode" name="veryCode" /></td>
						</tr>
						--></TBODY>
                    </TABLE>
                    <DIV class=buttons><INPUT value=登陆 type=submit> <INPUT onclick='document.location="regs.jsp"'
                                                                           value=注册 type=button>
                    </DIV>
                </DIV>
            </FORM>


            <form action="login2" method="post">
                请输入电话号码： <input type="text" name="tel" id="inputTel">----<input type="button" value="验证"
                                                                                name="dd" id="sendButton">
                请输入验证码：<input type="text" name="inputCode">
                <INPUT value=登陆 type=submit>

            </form>


        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>


</HTML>
