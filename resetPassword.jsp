<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/verticalTable.css">
<link rel="stylesheet" href="./css/message.css">
<link rel="stylesheet" href="./css/style.css">

<title>パスワード再設定</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>パスワード再設定画面</h1>

    <s:if test="userIdErrorMessageList!= null && userIdErrorMessageList.size()>0">
    <div class="message-error">
       <s:iterator value="userIdErrorMessageList"><s:property /><br>
       </s:iterator>
    </div>
    </s:if>

   <s:if test="passwordErrorMessageList!= null && passwordErrorMessageList.size()>0">
    <div class="message-error">
       <s:iterator value="passwordErrorMessageList"><s:property/><br>
       </s:iterator>
   </div>
   </s:if>

   <s:if test="newPasswordErrorMessageList!= null && newPasswordErrorMessageList.size()>0">
    <div class="message-error">
       <s:iterator value="newPasswordErrorMessageList"><s:property/><br>
       </s:iterator>
   </div>
   </s:if>

   <s:if test="reConfirmationNewPasswordErrorMessageList!= null && reConfirmationNewPasswordErrorMessageList.size()>0">
    <div class="message-error">
       <s:iterator value="reConfirmationNewPasswordErrorMessageList"><s:property/><br>
       </s:iterator>
   </div>
   </s:if>

   <s:if test="passwordIncorrectErrorMessage!=null && !passwordIncorrectErrorMessage.isEmpty()">
   <div class="message-error">
      <s:property value="passwordIncorrectErrorMessage"/><br>
   </div>
   </s:if>

   <s:if test="newPasswordIncorrectErrorMessage!=null && !newPasswordIncorrectErrorMessage.isEmpty()">
   <div class="message-error">
      <s:property value="newPasswordIncorrectErrorMessage"/><br>
   </div>
   </s:if>

<s:form action="ResetPasswordConfirmAction">
    <table class="vertical-list-table">
      <tr>
          <th scope="row"><s:label value="ユーザーID"/></th>
          <td><s:textfield name="userId" value="%{#session.userIdForResetPassword}" placeholder="ユーザーID" class="txt"/></td>
      </tr>

      <tr>
          <th scope="row"><s:label value="現在のパスワード"/></th>
          <td><s:password name="password" value="" placeholder="現在のパスワード" class="txt"/></td>
      </tr>

      <tr>
          <th scope="row"><s:label value="新しいパスワード"/></th>
          <td><s:password name="newPassword" value="" placeholder="新しいパスワード" class="txt"/></td>
      </tr>

      <tr>
          <th scope="row"><s:label value="新しいパスワード(再確認)"/></th>
          <td><s:password name="reConfirmationPassword" value="" placeholder="新しいパスワード(再確認)" class="txt"/></td>
      </tr>
    </table>

    <div class="submit_btn_box">
          <s:submit value="確認" class="submit_btn"/>
    </div>
</s:form>

</div>
</body>
</html>