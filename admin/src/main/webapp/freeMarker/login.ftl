<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"content="text/html; charset=UTF-8">
    <title>${pageName}</title>
</head>
<body>
<div>
    <input id="user_id" value="${userId}">
    <input id="name_id" value="${userName}">
    <button id="edit" onclick="edit()" style="color: rebeccapurple">编辑</button>
</div>
<script type="text/javascript">
    function edit() {
        alert("点击事件")
    }
</script>
</body>
</html>