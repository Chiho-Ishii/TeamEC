package com.internousdev.oregon.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.oregon.dao.UserInfoDAO;
import com.internousdev.oregon.util.CommonUtility;
import com.internousdev.oregon.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware {
	private String userId;
	private String password;
	private String newPassword;
	private String reConfirmationPassword;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> newPasswordErrorMessageList;
	private List<String> reConfirmationNewPasswordErrorMessageList;
	private String passwordIncorrectErrorMessage;
	private String newPasswordIncorrectErrorMessage;
	private String concealedPassword;
	private Map<String,Object> session;

	public String execute(){
		String result=ERROR;

		InputChecker inputChecker=new InputChecker();
		session.put("userIdForResetPassword",userId);

		userIdErrorMessageList=inputChecker.doCheck("ユーザーID",userId,1,8, true, false,false,true,false,false);
		passwordErrorMessageList=inputChecker.doCheck("現在のパスワード", password,1,16,true,false,false,true,false,false);
		newPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード", newPassword,1,16,true,false,false,true,false,false);
		reConfirmationNewPasswordErrorMessageList=inputChecker.doCheck("新しいパスワード(再確認)",reConfirmationPassword,1,16,true,false,false,true,false,false);

		if(userIdErrorMessageList.size()>0 ||
		   passwordErrorMessageList.size()>0 ||
		   newPasswordErrorMessageList.size()>0 ||
		   reConfirmationNewPasswordErrorMessageList.size()>0){
			return result;
		}

		UserInfoDAO userInfoDAO=new UserInfoDAO();
		if(!userInfoDAO.isExistsUserInfo(userId,password)){
			passwordIncorrectErrorMessage="ユーザーIDまたは現在のパスワードが異なります。";
			return result;
		}

		newPasswordIncorrectErrorMessage=inputChecker.doPasswordCheck(newPassword,reConfirmationPassword);

		if(newPasswordIncorrectErrorMessage==null){
			CommonUtility commonUtility=new CommonUtility();
			concealedPassword=commonUtility.concealPassword(newPassword);
			session.put("newPassword", newPassword);
			result=SUCCESS;
		}
		return result;
    }

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getNewPassword(){
		return newPassword;
	}

	public void setNewPassword(String newPassword){
		this.newPassword=newPassword;
	}

	public String getReConfirmationPassword(){
		return reConfirmationPassword;
	}

	public void setReConfirmationPassword(String reConfirmationPassword){
		this.reConfirmationPassword=reConfirmationPassword;
	}

	public List<String> getUserIdErrorMessageList(){
		return userIdErrorMessageList;
	}

	public void serUserIdErrorMessageList(List<String> userIdErrorMessageList){
		this.userIdErrorMessageList=userIdErrorMessageList;
	}

	public List<String>  getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList=passwordErrorMessageList;
	}

	public List<String> getNewPasswordErrorMessageList(){
		return newPasswordErrorMessageList;
	}

	public void setNewPasswordErrorMessageList(List<String> newPasswordErrorMessageList){
		this.newPasswordErrorMessageList=newPasswordErrorMessageList;
	}

	public List<String> getReConfirmationNewPasswordErrorMessageList(){
		return reConfirmationNewPasswordErrorMessageList;
	}

	public void setReConfirmationNewPasswordErrorMessageList(List<String> reConfirmationNewPasswordErrorMessageList){
		this.reConfirmationNewPasswordErrorMessageList=reConfirmationNewPasswordErrorMessageList;
	}

	public String getPasswordIncorrectErrorMessage(){
		return passwordIncorrectErrorMessage;
	}

	public void setPasswordIncorrectErrorMessage(String passwordIncorrectErrorMessage){
		this.passwordIncorrectErrorMessage=passwordIncorrectErrorMessage;
	}

	public String getNewPasswordIncorrectErrorMessage(){
		return newPasswordIncorrectErrorMessage;
	}

	public void setNewPasswordIncorrectErrorMessage(String newPasswordIncorrectErrorMessage){
		this.newPasswordIncorrectErrorMessage=newPasswordIncorrectErrorMessage;
	}

	public String getConcealedPassword(){
		return concealedPassword;
	}

	public void setConcealedPassword(String concealedPassword){
		this.concealedPassword=concealedPassword;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}
