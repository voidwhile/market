package com.voidwhile.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voidwhile.core.utils.PropertyUtils;
/**
 * 
    * @ClassName: SysBaiduMapUtil
    * @Description: 百度鹰眼轨迹服务工具类
    * @author hekang
    * @date 2016年11月2日
    *
 */
public class SysBaiduMapUtil {
	private static String ENTITY_BASE_URL = PropertyUtils.getPropertyValue("config.properties", "map_entity_base_url");
	private static String TRACK_BASE_URL = PropertyUtils.getPropertyValue("config.properties", "map_track_base_url");
	private static String AK = PropertyUtils.getPropertyValue("config.properties", "map_ak");
	private static String SERVICE_ID = PropertyUtils.getPropertyValue("config.properties", "map_service_id");
	private static String ENCODE = "UTF-8";
	
	private static String FENCE_LIST_URL = PropertyUtils.getPropertyValue("config.properties", "map_fence_list_url");
	private static String FENCE_STATUS_URL = PropertyUtils.getPropertyValue("config.properties", "map_fence_status_url");
	private static String FENCE_UPDATE_URL = PropertyUtils.getPropertyValue("config.properties", "map_fence_update_url");
	private static String FENCE_DELETE_URL = PropertyUtils.getPropertyValue("config.properties", "map_fence_delete_url");
	private static String FENCE_HISTORYALARM_URL = PropertyUtils.getPropertyValue("config.properties", "map_fence_historyalarm_url");
	private static String ROUTEMATRIX_DRIVING_URL = PropertyUtils.getPropertyValue("config.properties","map_routematrix_driving_url");
	/**
	 * 
	    * @Title: addEntity
	    * @Description: 百度鹰眼轨迹管理平台添加数据
	    * @param  license    参数(车牌号)
	    * @return void    返回类型
	    * @throws
	    * @author like
	 */
	public static void addEntity(String license,String driver){
		Map<String,String> params = new HashMap<String, String>();
		params.put("service_id", SERVICE_ID);
		params.put("ak", AK);
		params.put("entity_name", license);
		params.put("driver", driver);
		String url = ENTITY_BASE_URL + "/add";
		try {
			String result = HttpClientUtil.httpPost(url, params, ENCODE);
			//System.out.println(result);
			JSONObject json = new JSONObject(result);
			if("3005".equals(json.get("status"))){
				System.out.println(json.get("message"));
			}else{
				System.out.println(json.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	    * @Title: delEntity
	    * @Description: 百度鹰眼轨迹管理平台删除数据
	    * @param license    参数（车牌号）
	    * @return void    返回类型
	    * @throws
	    * @author hekang
	 */
	public static void delEntity(String license){
		Map<String,String> params = new HashMap<String, String>();
		params.put("service_id", SERVICE_ID);
		params.put("ak", AK);
		params.put("entity_name", license);
		String url = ENTITY_BASE_URL + "/delete";
		try {
			String result = HttpClientUtil.httpPost(url, params, ENCODE);
			//System.out.println(result);
			JSONObject json = new JSONObject(result);
			if(json.getInt("status") == 0){
				System.out.println(json.get("message"));
			}else{
				System.out.println(json.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	    * @Title: updateEntity
	    * @Description: 百度鹰眼轨迹管理平台更新数据
	    * @param  license    参数(车牌号)
	    * @return void    返回类型
	    * @throws
	 */
	public static void updateEntity(String license,String driver){
		Map<String,String> params = new HashMap<String, String>();
		params.put("service_id", SERVICE_ID);
		params.put("ak", AK);
		params.put("entity_name", license);
		params.put("driver", driver);
		System.out.println(license+"==============="+driver);
		String url = ENTITY_BASE_URL + "/update";
		try {
			String result = HttpClientUtil.httpPost(url, params, ENCODE);
			//System.out.println(result);
			JSONObject json = new JSONObject(result);
			if("0".equals(json.get("status"))){
				System.out.println(json.get("message"));
			}else{
				System.out.println(json.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	    * @Title: getAllEntity
	    * @Description: 百度鹰眼轨迹管理平台 查询该AK应用下的所有数据
	    * @return void  返回类型
	    * @throws
	 */
	public static JSONArray getAllEntity(){
		JSONArray result = null;
		String url = ENTITY_BASE_URL + "/list?ak="+AK+"&service_id="+SERVICE_ID;
		try {
			String json = HttpClientUtil.httpGet(url, ENCODE);
			System.out.println(json);
			JSONObject obj = new JSONObject(json);
			if(obj.getInt("status") == 0){
				result =  (JSONArray) obj.get("entities");
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @Title: getSingleEntity
	 * @Description: 百度鹰眼轨迹管理平台 查询该AK应用下的对应名称的数据
	 * @return void  返回类型
	 * @throws
	 */
	public static JSONArray getSingleEntity(String entityName){
		JSONArray result = null;
		String url = ENTITY_BASE_URL + "/list?ak="+AK+"&service_id="+SERVICE_ID+"&entity_names="+entityName;
		try {
			String json = HttpClientUtil.httpGet(url, ENCODE);
			System.out.println(json);
			JSONObject obj = new JSONObject(json);
			if(obj.getInt("status") == 0){
				result =  (JSONArray) obj.get("entities");
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param key	字段名称	string(45)
	 * @param desc	字段描述	string(0-128)
	 * @param is_search	是否为检索字段	int(0-1）
	 */
	public static void addColumn(String key,String desc,int is_search){
		Map<String,String> params = new HashMap<String, String>();
		params.put("service_id", SERVICE_ID);
		params.put("ak", AK);
		params.put("column_key", key);
		params.put("column_desc", desc);
		params.put("is_search", String.valueOf(is_search));
		String url = ENTITY_BASE_URL + "/addcolumn";
		try {
			String result = HttpClientUtil.httpPost(url, params, ENCODE);
			//System.out.println(result);
			JSONObject json = new JSONObject(result);
			if("7001".equals(json.get("status"))){
				System.out.println(json.get("message"));
			}else{
				System.out.println(json.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------------------点轨迹--------------------------------------------------
	
	/**
	 * 
	    * @Title: addPoint
	    * @Description: 添加轨迹点
	    * @param  license	车牌号
	    * @param  lat	纬度
	    * @param  lon	经度
	    * @param  type	坐标类型 
	    * @param  time  定位时设备的时间 
	    * @return void  返回类型
	    * @throws
	 */
	public static void addPoint(String license, String lon,String lat,String time,String speed){
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", AK);
		params.put("service_id", SERVICE_ID);
		params.put("longitude", lon);
		params.put("latitude", lat);
		params.put("coord_type", "2");
		params.put("loc_time", time);
		params.put("speed", speed);
		params.put("entity_name", license);
		String url = TRACK_BASE_URL + "/addpoint";
		try {
			HttpClientUtil.httpPost(url, params, ENCODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	    * @Title: toGetHistory
	    * @Description: 查询历史轨迹
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	public static void toGetHistory(String starttime,String endtime,String entityname){
		String process_option = "need_denoise=1,need_vacuate=1,need_mapmatch=1,transport_mode=1";
		String url = TRACK_BASE_URL+"/gethistory?ak="+AK+"&service_id="+SERVICE_ID
				+ "&start_time="+starttime+"&end_time="+endtime+"&entity_name="+"豫A888888"
				+ "&is_processed=1"+"&process_option="+process_option;
		try {
			String result = HttpClientUtil.httpGet(url, ENCODE);
			System.out.println(result);
			JSONObject json = new JSONObject(result);
			if("0".equals(json.get("status"))){
				System.out.println(json.get("message"));
			}else{
				System.out.println(json.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//--------------------------------地图围栏--------------------start-------------------------
	/**
	 * @DESC 获取所有围栏列表信息
	 * @return
	 */
	public static JSONArray getFenceList(String creator,String ids){
		JSONArray result = null;
		String url = FENCE_LIST_URL + "?ak="+AK+"&service_id="+SERVICE_ID+"&creator="+creator+"&fence_ids="+ids;
		try {
			String  json = HttpClientUtil.httpGet(url, ENCODE);
			//System.out.println(json);
			
			JSONObject obj = new JSONObject(json);
			if(obj.getInt("status") == 0){
				result =  (JSONArray) obj.get("fences");
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @DESC 监控单个车辆在围栏内还是围栏外
	 * @param id 地理围栏唯一标示
	 * @param persons 围栏监控对象列表(多个对象用逗号分隔。表示查询那些监控对象的状态。不填时，查询所有监控对象的状态)
	 * @return
	 */
	public static JSONArray getFenceStatus(String id,String persons){
		JSONArray result = null;
		String url = FENCE_STATUS_URL + "?ak="+AK+"&service_id="+SERVICE_ID + "&fence_id="+ id + "&monitored_persons=" + persons;
		try {
			String  json = HttpClientUtil.httpGet(url, ENCODE);
			//System.out.println(json);
			JSONObject obj = new JSONObject(json);
			if(obj.getInt("status") == 0){
				result =  (JSONArray) obj.get("monitored_person_statuses");
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @DESC 根据fence_id删除围栏
	 * @param id
	 */
	public static void deleteFence(String id){
		Map<String,String> params = new HashMap<String, String>();
		params.put("ak", AK);
		params.put("service_id", SERVICE_ID);
		params.put("fence_id", id);
		try {
			String json = HttpClientUtil.httpPost(FENCE_DELETE_URL, params, ENCODE);
			JSONObject obj = new JSONObject(json);
			if("0".equals(obj.get("status"))){
				System.out.println(obj.get("message"));
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @DESC 查询围栏的监控对象的历时报警信息。只提供7天以内（包含7天）的数据查询，7天以外的数据不提供查询服务。
	 * @param id
	 * @param persons
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static JSONArray getHistoryAlarm(String id,String persons,String beginTime,String endTime){
		JSONArray result = null;
		String url = FENCE_HISTORYALARM_URL +  "?ak="+AK+"&service_id="+SERVICE_ID + "&fence_id="+ id + "&"
				+ "monitored_persons=" + persons + "&begin_time=" + beginTime + "&end_time=" +endTime;
		try {
			String  json = HttpClientUtil.httpGet(url, ENCODE);
			//System.out.println(json);
			JSONObject obj = new JSONObject(json);
			if(obj.getInt("status") == 0){
				result =  (JSONArray) obj.get("monitored_person_alarms");
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @DESC 更新一个围栏实体的详细信息。围栏属性信息中各个可选字段如果不填，则不更新相关属性值。
	 */
	public static void updateFence(Map<String, String> params){
		try {
			String json = HttpClientUtil.httpPost(FENCE_DELETE_URL, params, ENCODE);
			JSONObject obj = new JSONObject(json);
			if("0".equals(obj.get("status"))){
				System.out.println(obj.get("message"));
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//---------------------------------地图围栏-----------------------end----------------------------------
	public static void main(String[] args) {
		String time=" 2016-11-07 11:00:15 ";//注：改正后这里前后也加了空格  
		System.out.println(toUnixTime(time));
//		String starttime = toUnixTime("");//开始时间
//		String endtime = toUnixTime("");//结束时间
//		String entityname = ""; //车牌号
//		SysBaiduMapUtil.addPoint("豫B8973AK", "113.672223", "34.815105", "1", toUnixTime(time));
		//SysBaiduMapUtil.toGetHistory(starttime, endtime, entityname);
		//SysBaiduMapUtil.addPoint("豫A7L36K", "113.591665", "34.75313", toUnixTime(time));
		getAllEntity();
	}
	
	/**
	 * 
	    * @Title: toUnixTime
	    * @Description: java日期 转换为 UNIX时间戳
	    * @param @param local
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String toUnixTime(String local){
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String unix = "";
	    try {
	        unix = df.parse(local).getTime()/1000 + "";
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return unix;
	}
	
	
	/**
	 * 获取两点驾车距离
	 * @param map
	 * @return
	 */
	public static double getDrivingRoute(String startLat,String startLng,String endLat,String endLng){
		JSONObject route = null;
		String url = ROUTEMATRIX_DRIVING_URL +  "?ak="+AK+"&origins="+startLat+","+startLng+"&destinations="+endLat+","+endLng;
		System.out.println("ROUTEMATRIX_DRIVING_URL:"+url);
		try {
			String  json = HttpClientUtil.httpGet(url, ENCODE);
			JSONObject obj = new JSONObject(json);
			//System.out.println("-----------"+obj.getInt("status"));
			if(obj.getInt("status") == 0){
				JSONArray result =  (JSONArray) obj.get("result");
				if (result!=null&&result.length()>0) {
					route = result.getJSONObject(0);
				}
			}else{
				System.out.println(obj.get("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return route.getJSONObject("distance").getDouble("value");
	}

}
