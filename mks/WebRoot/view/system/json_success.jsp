<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=gb2312" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<body>
<script type="text/javascript">
	var data =${data};
	alert(data.msg);
	if("success" == data.success){
		window.parent.refresh();
	}
	window.parent.closeDialog();
</script>
</body>
</html>

