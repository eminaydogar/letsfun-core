package com.easoft.letsfun.util;

import java.util.ArrayList;
import java.util.List;

import com.easoft.letsfun.cache.CacheManager;
import com.easoft.letsfun.cache.ObjectValueTypeConstant;
import com.easoft.letsfun.common.api.bean.AddressBean;
import com.easoft.letsfun.common.api.bean.ObjectValueBean;
import com.easoft.letsfun.entity.resultset.ObjectValue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtil {

	private CacheUtil() {

	}

	public static AddressBean findAddresses(String address) {
		AddressBean addressBean = new AddressBean();
		if (address != null) {
			String[] addressList = address.split(";");
			if (addressList != null) {
				for (String add : addressList) {
					try {
						Long addresId = Long.valueOf(add);
						ObjectValue objValue = CacheManager.getItemById(addresId);
						if (objValue != null) {
							if (objValue.getObjectType().equals(ObjectValueTypeConstant.CITY)) {
								addressBean.setCityCode(objValue.getId());
								addressBean.setCityName(objValue.getObjectName());
							} else if (objValue.getObjectType().equals(ObjectValueTypeConstant.DISTRICT)) {
								addressBean.setDistrictCode(objValue.getId());
								addressBean.setDistrictName(objValue.getObjectName());
							}
						}
					} catch (Exception e) {
						log.error("findAddresses error: ", e);
					}
				}
			}
		}

		return addressBean;
	}

	public static List<ObjectValueBean> findObjectValueList(String features) {
		List<ObjectValueBean> objValueBeanList = null;

		if (features != null) {
			String[] featureList = features.split(";");
			if (featureList != null) {
				objValueBeanList = new ArrayList<>();
				for (String feature : featureList) {
					try {
						Long featureId = Long.valueOf(feature);
						ObjectValue objValue = CacheManager.getItemById(featureId);
						if (objValue != null) {
							ObjectValueBean objValueBean = new ObjectValueBean();
							objValueBean.setId(objValue.getId());
							objValueBean.setCode(objValue.getObjectCode());
							objValueBean.setName(objValue.getObjectName());
							objValueBeanList.add(objValueBean);
						}
					} catch (Exception e) {
						log.error("findAddresses error: ", e);
					}
				}

			}
		}

		return objValueBeanList;
	}

	public static ObjectValueBean findObjectValue(String id) {

		ObjectValueBean objValueBean = null;

		try {
			Long convertedId = Long.valueOf(id);
			ObjectValue objValue = CacheManager.getItemById(convertedId);
			if (objValue != null) {
				objValueBean = new ObjectValueBean();
				objValueBean.setId(objValue.getId());
				objValueBean.setCode(objValue.getObjectCode());
				objValueBean.setName(objValue.getObjectName());
			}
		} catch (Exception e) {
			log.error("findAddresses error: ", e);
		}

		return objValueBean;
	}

}
