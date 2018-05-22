/*******************************************************************************
 * 行政区
 ******************************************************************************/

/**
 * 控件的入口。 【 省，市，区（县）的下拉列表的name值】
 * @param id
 * @param defaultName  默认省份名称
 * @param proName
 * @param cityName
 * @param areaName
 */
function loadChina(id, defaultNamePro,defaultNameCity,defaultNameArea, proName, cityName, areaName) {
	if (!proName)
		proName = '';
	if (!cityName)
		cityName = '';
	if (!areaName)
		areaName = '';
	if (!id)
		id = "sel";
	$.post("xml/china.xml",function(xml) {
			$("#" + id).append("<select id='_province' name='" + proName + "'><option selected='selected' value=''>--选择省/直辖市--</option></select>");
			$("#" + id).append("<select id='_city' name='"	+ cityName	+ "'><option  value=''>--选择市--</option></select>");
			$("#" + id).append("<select id='_area' name='" + areaName	+ "'><option  value=''>--选择区/县--</option></select>");
			loadProvince(id, xml, defaultNamePro,defaultNameCity,defaultNameArea);
	}, "xml");
}
/**
 * 加载省
 * @param id
 * @param xml
 * @param defaultName
 */
function loadProvince(id, xml, defaultNamePro,defaultNameCity,defaultNameArea) {

	$("province", xml).each(
		function() {
			var prov = $(this);
			var obj = $("#_province");
			var op = $("<option value='" + prov.attr("name") + "'>"
					+ prov.attr("name") + "</option>");
			$("#_province").append(op);
			$("#_province").bind("change", function() {
				if (prov.attr("name") == obj.val()) {
					resetArea();
					loadCity(null, prov, defaultNamePro,defaultNameCity,defaultNameArea);
				}else if(obj.val() == ''){
					resetCity();
					resetArea();
				}
			});
			if (defaultNamePro == prov.attr("name")) {// 加载默认省份
				op.attr("selected", "selected");
				loadCity(null, prov, defaultNamePro,defaultNameCity,defaultNameArea);
			}
	});
}

/**
 * 加载市
 * @param id
 * @param xml
 */

function resetCity(){
	$("#_city").empty();
	$("#_city").append($("<option value=''>--选择市--</option>"));
}
function loadCity(id, xml, defaultNamePro,defaultNameCity,defaultNameArea) {
	resetCity();
	$("city", xml).each(
		function() {
			var city = $(this);
			var obj = $("#_city");
			var op_c = $("<option value='" + city.attr("name") + "'>"
					+ city.attr("name") + "</option>");
			
			$("#_city").bind("change", function() { 
				if(obj.val() == ''){
					resetArea();
				}else if(city.attr("name") == obj.val()){
					resetArea();
					loadArea(null, city, defaultNamePro,defaultNameCity,defaultNameArea);
				}
			});
			if (defaultNameCity == city.attr("name")) {// 加载默认
				op_c.attr("selected", "selected");
				loadArea(null, city, defaultNamePro,defaultNameCity,defaultNameArea);
			}
			$("#_city").append(op_c);
	});
	
	$("#_city").show();
	$("#_area").show(); 
	// 当有1,2级结构时
	
	if ($("#_city").children().length == 1	&& $("#_city").children()[0].value == '') {
		$("#_city").hide();
		$("#_area").hide();
	}else if($("#_city").children().length == 2 && $("#_city").children()[0].value == '' && $("#_city").children()[1].value == ''){
		$("#_city").hide();
		loadArea(id, xml, defaultNamePro,defaultNameCity,defaultNameArea);
	}

}

/**
 * 清空区/县
 */
function resetArea() {
	$("#_area").empty();
	$("#_area").append($("<option value=''>--选择区/县--</option>"));
}
/**
 * 加载区/县
 * @param id
 * @param xml
 */
function loadArea(id, xml, defaultNamePro,defaultNameCity,defaultNameArea) {
	resetArea();
	$("region", xml).each(
		function() {
			var reg = $(this);
			var op_r = $("<option value='" + reg.attr("name") + "'>"
					+ reg.attr("name") + "</option>");
			
			if (defaultNameArea == reg.attr("name")) {// 加载默认
				op_r.attr("selected","selected"); 
			}
			$("#_area").append(op_r);
	});
	
	if ($("#_area").children().length == 1
			&& $("#_area").children()[0].value == '') {
		$("#_area").hide();
	} else {
		$("#_area").show();
	}

}