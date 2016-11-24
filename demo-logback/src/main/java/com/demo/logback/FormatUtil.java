/**
 * ProjectName:  CodeGenerate
 * FileName:  FormatName.java
 * PackageName:  com.xb.code.generate.database.util
 * Copyright (c) 2013, CYOU All Rights Reserved.
*/
package com.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lianjia.common.log.RlaLogUtil;
import com.spiderworts.example.n1.dubbo.RequestIdHolder;
import com.spiderworts.example.n1.dubbo.RequestIdUtil;
import com.spiderworts.log.n1.customer.CustomerRlaLogCode;

/**
 * ClassName:  FormatName. <br/>
 * Description:  TODO(功能描述) <br/>
 * @author xblibo
 * 2013-3-3 下午6:45:37 <br/>
 * @version 1.0
 */
public class FormatUtil {
	
	/**
	 * 去除下划线，并大写下划线右边首字母(第一个字母小写)
	 */
	public static String formatField(String field){
		field = field.toLowerCase();
	    String[] strs = field.split("_");
	    field = strs[0];
	    for(int i = 1;i<strs.length;i++){
	    	String tempStr = strs[i].toLowerCase();
	        tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());
	        field = field + tempStr;
	    }
	    
	    return field;
	  }
	
	public static String formatBField(String field){
		field = formatField(field);
		field = field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
	    return field;
	  }
	/**
	 * 去除下划线，并大写下划线右边首字母(第一个字母小写)
	 */
	public static String formatClassName(String tableName){
		tableName = tableName.toLowerCase();
		if (tableName.startsWith("t_")) {
			tableName = tableName.replaceFirst("t_", "");
		}
		return formatField(tableName);
	}
	
	/**
	 * 去除下划线，并大写下划线右边首字母(第一个字母大写)
	 */
	public static String formatBClassName(String tableName){
		tableName = formatResource(tableName);
		tableName = formatField(tableName);
		tableName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length());
		return tableName;
	}
	
	/**
	 * 去除下划线，并大写下划线右边首字母(第一个字母大写)
	 */
	public static String formatResource(String tableName){
		tableName = tableName.toLowerCase();
		if (tableName.startsWith("t_")) { 
			tableName = tableName.replaceFirst("t_", "");
		}
		return tableName;
	}
	
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FormatUtil.class);
		System.out.println(formatResource("t_maDAnagDer_id_name"));
		System.out.println(formatBField("t_maDAnagDer_id_name"));
		System.out.println("T_managert_tsdf".toLowerCase());
		RequestIdHolder.set(RequestIdUtil.getReqId(RequestIdUtil.DUBBO));
		RlaLogUtil.log(logger, CustomerRlaLogCode.info);
	}
}

