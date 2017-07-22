package com.shui.tagclass;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TableTag extends TagSupport{
	private List items;
	private int col;
	private String var;
	
	private int num=0;
	private List result;
	private int length=0;
	
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	
	@Override
	public int doStartTag() throws JspException {
		num=0;
		length=items.size();
		pageContext.setAttribute(var,split());
		num+=col;
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doAfterBody() throws JspException {
		if(num<length){
			pageContext.setAttribute(var, split());
			num+=col;
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}
	}
	
	private List split(){
		result=items.subList(num, (num+col)<length?num+col:length);
		return result;
	}
}
