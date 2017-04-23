package com.iclsi.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

	/**
	 * 查询
	 * @param request
	 * @param response
	 * @return
	 */
	public  String list(HttpServletRequest request,HttpServletResponse response){return null;}
	
	
	
	/**
	 * 页面初始化
	 * @param request
	 * @param response
	 * @return
	 */
	public  Map<String, Object>  init(HttpServletRequest request,HttpServletResponse response){return null;}
	
	
	/**
	 * 新增
	 * @param request
	 * @param response
	 * @return
	 */
	public  String insert(HttpServletRequest request,HttpServletResponse response){ return null;}
	
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @return
	 */
	public  Map<String, Object>  save(HttpServletRequest request,HttpServletResponse response){ return null;}
	
	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return
	 */
	public  String update(HttpServletRequest request,HttpServletResponse response){return null;}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	public  String delete(HttpServletRequest request,HttpServletResponse response){return null;}

	/**
	 * 获取全部是字符的参数
	 * @param request
	 * @return
	 */
	public Map<String, Object> getParamsObject(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> enumeration = request.getParameterNames();
		if( enumeration != null ){
			while ( enumeration.hasMoreElements() ) {
				String key = String.valueOf( enumeration.nextElement() );
				map.put(key,  request.getParameter(key) );
			}
		}
		return map;
	}
}
