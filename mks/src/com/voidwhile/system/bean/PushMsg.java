package com.voidwhile.system.bean;


public class PushMsg {

	private String sender;//发送者帐号
	private String receiver;//接收者账号，多个接收者以逗号隔开，如果是个人，最大上限100人/次，如果是群组，仅支持1个。
	private int msgType;//消息类型，1：文本消息，2：语音消息，3：视频消息，4：图片消息，5：位置消息，6：文件
	private int pushType;//推送类型，1：个人，2：群组，默认为1
	private String msgContent;//文本内容，最大长度2048字节，文本和附件二选一，不能都为空
	private String msgDomin;//扩展字段
	private String msgFileName;//文件名，最大长度128字节
	private String msgFileUrl;//文件相对路径
	
	public PushMsg(String sender, String receiver, int msgType, int pushType,
			String msgContent, String msgDomin, String msgFileName,
			String msgFileUrl) {
		this.sender = sender;
		this.receiver = receiver;
		this.msgType = msgType;
		this.pushType = pushType;
		this.msgContent = msgContent;
		this.msgDomin = msgDomin;
		this.msgFileName = msgFileName;
		this.msgFileUrl = msgFileUrl;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getPushType() {
		return pushType;
	}
	public void setPushType(int pushType) {
		this.pushType = pushType;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgDomin() {
		return msgDomin;
	}
	public void setMsgDomin(String msgDomin) {
		this.msgDomin = msgDomin;
	}
	public String getMsgFileName() {
		return msgFileName;
	}
	public void setMsgFileName(String msgFileName) {
		this.msgFileName = msgFileName;
	}
	public String getMsgFileUrl() {
		return msgFileUrl;
	}
	public void setMsgFileUrl(String msgFileUrl) {
		this.msgFileUrl = msgFileUrl;
	}
	
}
